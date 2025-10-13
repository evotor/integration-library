package ru.evotor.framework.core;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.OperationCanceledException;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import ru.evotor.IBundlable;
import ru.evotor.framework.Utils;


public class IntegrationManagerImpl implements IntegrationManager {

    private static final ConcurrentHashMap<ComponentName, Pair<IntegrationManagerServiceConnection, IIntegrationManager>> connectionPool = new ConcurrentHashMap<>();

    private static final String TAG = "IntegrationManager";

    private Context context;
    private final Handler mainHandler;

    public IntegrationManagerImpl(Context context) {
        this.context = context.getApplicationContext();
        this.mainHandler = new Handler(context.getMainLooper());
    }

    @Override
    public IntegrationManagerFuture call(
            final String action,
            ComponentName componentName,
            IBundlable data,
            final Activity activity,
            IntegrationManagerCallback callback,
            Handler handler
    ) {
        return call(
                action,
                componentName,
                data == null ? null : data.toBundle(),
                null,
                new ActivityStarter(activity, false),
                callback,
                handler
        );
    }

    @Override
    public IntegrationManagerFuture call(
            String action,
            ComponentName componentName,
            IBundlable data,
            ICanStartActivity activityStarter,
            IntegrationManagerCallback callback,
            Handler handler
    ) {
        return call(action,
                componentName,
                data == null ? null : data.toBundle(),
                null,
                activityStarter,
                callback,
                handler
        );
    }

    @Override
    public IntegrationManagerFuture call(
            String action,
            ComponentName componentName,
            Bundle data,
            Map<String, Integer> packageSpecificTimeouts,
            ICanStartActivity activityStarter,
            IntegrationManagerCallback callback,
            Handler handler
    ) {
        final ImsTask future = new ImsTask(
                activityStarter,
                handler,
                callback,
                action,
                componentName,
                data,
                packageSpecificTimeouts
        );

        new Thread() {
            @Override
            public void run() {
                super.run();
                future.start();
            }
        }.start();

        return future;
    }

    private void ensureNotOnMainThread() {
        final Looper looper = Looper.myLooper();
        if (looper != null && looper == context.getMainLooper()) {
            final IllegalStateException exception = new IllegalStateException(
                    "calling this from your main thread can lead to deadlock");
            Log.e(TAG, "calling this from your main thread can lead to deadlock and/or ANRs",
                    exception);
            throw exception;
        }
    }

    private void postToHandler(Handler handler, final IntegrationManagerCallback callback,
                               final IntegrationManagerFuture future) {
        handler = handler == null ? mainHandler : handler;
        handler.post(() -> callback.run(future));
    }

    private static AtomicInteger threadNum = new AtomicInteger(1);

    private class ImsTask extends FutureTask<IntegrationManagerFuture.Result> implements IntegrationManagerFuture {
        final Handler mHandler;
        IntegrationManagerCallback mCallback;
        final ICanStartActivity mActivityStarter;
        final String mAction;
        final ComponentName mComponentName;
        final Bundle mData;
        final Map<String, Integer> mPackageSpecificTimeouts;

        int num = 0;

        public ImsTask(
                final Activity activity,
                Handler handler,
                IntegrationManagerCallback callback,
                final String action,
                ComponentName componentName,
                Bundle data,
                Map<String, Integer> packageSpecificTimeouts
        ) {
            this(
                    activity == null ? null : new ActivityStarter(activity, false),
                    handler,
                    callback,
                    action,
                    componentName,
                    data,
                    packageSpecificTimeouts
            );
        }

        public ImsTask(
                ICanStartActivity activityStarter,
                Handler handler,
                IntegrationManagerCallback callback,
                String action,
                ComponentName componentName,
                Bundle data,
                Map<String, Integer> packageSpecificTimeouts
        ) {
            super(() -> {
                throw new IllegalStateException("this should never be called");
            });

            Utils.log("ImsTask "  + this);

            this.num = threadNum.incrementAndGet();
            mHandler = handler;
            mCallback = callback;
            mActivityStarter = activityStarter;
            mAction = action;
            mComponentName = componentName;
            mData = data;
            mPackageSpecificTimeouts = packageSpecificTimeouts;
        }

        public final IntegrationManagerFuture start() {
            try {
                doWork(new Response());
            } catch (Exception e) {
                setException(e);
            }
            return this;
        }

        @Override
        protected void set(Result result) {
            // TODO: somehow a null is being set as the result of the Future. Log this
            // case to help debug where this is occurring. When this bug is fixed this
            // condition statement should be removed.
            if (result == null) {
                Log.e(TAG, "the bundle must not be null", new Exception());
            }
            super.set(result);
        }

        private void doWork(Response response) throws RemoteException {
            Log.d(TAG, "doWork " + num);

            IIntegrationManager service = getService(response.getComponentName(), mPackageSpecificTimeouts);
            if (service == null) {
                response.skip();
                Log.d(TAG, "doWork return " + num);
                return;
            }

            Log.d(TAG, "doWork call " + num);
            service.call(response, mAction, mData);
        }

        @Override
        public String toString() {
            return "ImsTask{" +
                    "num=" + num +
                    ", mHandler=" + mHandler +
                    ", mActivityStarter=" + mActivityStarter +
                    ", mAction='" + mAction + '\'' +
                    ", mComponentName=" + mComponentName +
                    ", mData=" + mData +
                    ", mPackageSpecificTimeouts=" + mPackageSpecificTimeouts +
                    '}';
        }

        private IIntegrationManager getService(
                ComponentName componentName,
                Map<String, Integer> packageSpecificTimeouts
        ) {
            IIntegrationManager manager = getFromPool(componentName);
            if (manager != null) {
                return manager;
            }

            Log.d(TAG, "getService wait " + num);
            synchronized (connectionPool) {
                Log.d(TAG, "getService run " + num);
                manager = getFromPool(componentName);
                if (manager != null) {
                    Log.d(TAG, "getService return " + num);
                    return manager;
                }

                connect(componentName, packageSpecificTimeouts);
                IIntegrationManager iIntegrationManager = getFromPool(componentName);
                Log.d(TAG, "getService return manager " + num);
                return iIntegrationManager;
            }
        }

        private IIntegrationManager getFromPool(ComponentName componentName) {
            Log.d(TAG, "getFromPool return start function " + num);

            Pair<IntegrationManagerServiceConnection, IIntegrationManager> pair = connectionPool.get(componentName);
            if (pair == null) {
                Log.d(TAG, "getFromPool return null function " + num);
                return null;
            }

            IntegrationManagerServiceConnection connection = pair.first;
            if (!connection.disconnected) {
                Log.d(TAG, "getFromPool return pair " + num + " pair = " + pair.second);
                return pair.second;
            }

            Log.d(TAG, "getFromPool wait " + num);
            synchronized (connectionPool) {
                Log.d(TAG, "getFromPool run " + num);
                pair = connectionPool.get(componentName);
                if (pair == null) {
                    return null;
                }
                connection = pair.first;
                if (!connection.disconnected) {
                    Log.d(TAG, "getFromPool return pair sync " + num + " pair = " + pair.second);
                    return pair.second;
                }

                context.unbindService(connection);
                connectionPool.remove(componentName);
                Log.d(TAG, "getFromPool return null sync" + num);
                return null;
            }
        }

        private void connect(
                final ComponentName componentName,
                Map<String, Integer> packageSpecificTimeouts
        ) {
            ensureNotOnMainThread();
            Intent intent = new Intent();
            intent.setComponent(componentName);
            final CountDownLatch connectLatch = new CountDownLatch(1);
            IntegrationManagerServiceConnection connection = new IntegrationManagerServiceConnection(connectLatch);
            boolean binded = context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
            if (binded) {
                try {
                    String packageName = componentName.getPackageName();
                    int primitivePackageTimeout = 5;

                    if (packageSpecificTimeouts != null) {
                        Integer packageTimeout = packageSpecificTimeouts.get(packageName);
                        if (packageTimeout != null) {
                            primitivePackageTimeout = packageTimeout;
                        }
                    }

                    Log.d(TAG, "connect time out " + num + "time = " + primitivePackageTimeout);
                    connectLatch.await(primitivePackageTimeout, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!connectionPool.containsKey(componentName)) {
                    context.unbindService(connection);
                }
            }
        }

        private Result internalGetResult(Long timeout, TimeUnit unit)
                throws OperationCanceledException, IntegrationException {
            if (!isDone()) {
                ensureNotOnMainThread();
            }
            try {
                if (timeout == null) {
                    return get();
                } else {
                    return get(timeout, unit);
                }
            } catch (CancellationException e) {
                throw new OperationCanceledException();
            } catch (TimeoutException | InterruptedException e) {
                // fall through and cancel
            } catch (ExecutionException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof IntegrationException) {
                    throw (IntegrationException) cause;
                } else {
                    throw new IntegrationException(cause);
                }
            } finally {
                cancel(true /* interrupt if running */);
            }
            throw new OperationCanceledException();
        }

        @Override
        public Result getResult()
                throws OperationCanceledException, IntegrationException {
            return internalGetResult(null, null);
        }

        @Override
        protected void done() {
            Log.d(TAG, "done");

            if (mCallback != null) {
                postToHandler(mHandler, mCallback, this);
                mCallback = null;
            }
        }

        /**
         * Handles the responses from the IntegrationManager
         */
        private class Response extends IIntegrationManagerResponse.Stub {

            @Override
            public void onResult(Bundle bundle) {
                Intent intent = bundle.getParcelable(KEY_INTENT);
                Bundle options = bundle.getParcelable(KEY_OPTIONS);
                if (intent != null) {
                    if (mActivityStarter != null) {
                        // since the user provided an Activity we will silently start intents
                        // that we see
                        try {
                            mActivityStarter.startActivity(intent, options);
                        } catch (Throwable error) {
                            setException(error);
                        }
                    } else {
                        skip();
                    }
                    // leave the Future running to wait for the real response to this request
                } else if (bundle.getBoolean("retry")) {
                    try {
                        doWork(this);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        setException(e);
                    }
                } else if (bundle.getBoolean(KEY_SKIP)) {
                    skip();
                } else {
                    set(new Result(bundle.getBundle(KEY_DATA)));
                }
            }

            @Override
            public void onError(int code, String message, Bundle data) {
                Log.e(TAG, "onError(code = " + code + ", message = " + message + ")");

                set(new Result(new Error(code, message, data)));
            }

            void skip() {
                set(new Result((Bundle) null));
            }

            public ComponentName getComponentName() {
                return mComponentName;
            }
        }

    }

    public static List<ComponentName> convertImplicitIntentToExplicitIntent(String action, Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(new Intent(action), 0);
        List<ComponentName> intentList = new ArrayList<>();

        if (resolveInfoList == null || resolveInfoList.isEmpty()) {
            return null;
        }
        for (ResolveInfo serviceInfo : resolveInfoList) {
            ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
            intentList.add(component);
        }

        return intentList;
    }

    private static class IntegrationManagerServiceConnection implements ServiceConnection {
        private final CountDownLatch connectLatch;
        private volatile boolean disconnected = false;

        private IntegrationManagerServiceConnection(CountDownLatch connectLatch) {
            this.connectLatch = connectLatch;
        }

        public void onServiceConnected(ComponentName name, IBinder binder) {
            connectionPool.put(name, new Pair<>(this, IIntegrationManager.Stub.asInterface(binder)));
            connectLatch.countDown();
        }

        public void onServiceDisconnected(ComponentName name) {
            connectionPool.remove(name);
            connectLatch.countDown();
            disconnected = true;
        }
    }
}