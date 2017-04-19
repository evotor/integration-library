package ru.evotor.framework.core;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.OperationCanceledException;
import android.os.RemoteException;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class IntegrationManagerImpl implements IntegrationManager {

    private static final String TAG = "IntegrationManager";

    private Context context;
    private final Handler mainHandler;

    private final ConcurrentHashMap<ComponentName, IIntegrationManager> connectionPool = new ConcurrentHashMap<>();

    public IntegrationManagerImpl(Context context) {
        this.context = context;
        this.mainHandler = new Handler(context.getMainLooper());
    }

    @Override
    public IntegrationManagerFuture call(final String action, final Bundle data, Activity activity, IntegrationManagerCallback callback, Handler handler) {
        final ImsTask future = new ImsTask(activity, handler, callback, action, data);
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
            if (context.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.FROYO) {
                throw exception;
            }
        }
    }

    private void postToHandler(Handler handler, final IntegrationManagerCallback callback,
                               final IntegrationManagerFuture future) {
        handler = handler == null ? mainHandler : handler;
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.run(future);
            }
        });
    }

    private class ImsTask extends FutureTask<IntegrationManagerFuture.Result> implements IntegrationManagerFuture {
        final Handler mHandler;
        final IntegrationManagerCallback mCallback;
        final Activity mActivity;
        final String mAction;
        final Bundle mData;

        public ImsTask(Activity activity, Handler handler, IntegrationManagerCallback callback, String action, Bundle data) {
            super(new Callable<Result>() {
                @Override
                public Result call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });

            mHandler = handler;
            mCallback = callback;
            mActivity = activity;
            mAction = action;
            mData = data;
        }

        public final IntegrationManagerFuture start() {
            try {
                List<ComponentName> componentNames = convertImplicitIntentToExplicitIntent(new Intent(mAction), context);

                if (componentNames == null || componentNames.isEmpty()) {
                    new Response(null, componentNames).skip();
                } else {
                    doWork(new Response(componentNames.get(0), componentNames.subList(1, componentNames.size())));
                }
            } catch (RemoteException e) {
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
            IIntegrationManager service = getService(response.getComponentName());
            if (service == null) {
                response.skip();
                return;
            }
            service.call(response, mAction, mData);
        }

        private IIntegrationManager getService(ComponentName componentName) {
            IIntegrationManager manager = connectionPool.get(componentName);
            if (manager != null) {
                return manager;
            }

            synchronized (connectionPool) {
                manager = connectionPool.get(componentName);
                if (manager != null) {
                    return manager;
                }

                connect(componentName);
                return connectionPool.get(componentName);
            }
        }

        private void connect(final ComponentName componentName) {
            ensureNotOnMainThread();
            Intent intent = new Intent();
            intent.setComponent(componentName);
            final CountDownLatch connectLatch = new CountDownLatch(1);
            ServiceConnection connection = new ServiceConnection() {
                public void onServiceConnected(ComponentName name, IBinder binder) {
                    connectionPool.put(name, IIntegrationManager.Stub.asInterface(binder));
                    connectLatch.countDown();
                }

                public void onServiceDisconnected(ComponentName name) {
                    connectionPool.remove(name);
                    context.unbindService(this);
                    connectLatch.countDown();
                }
            };
            boolean binded = context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
            if (binded) {
                try {
                    connectLatch.await(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!connectionPool.containsKey(componentName)) {
                    context.unbindService(connection);
                }
            }
        }

        private Result internalGetResult(Long timeout, TimeUnit unit)
                throws OperationCanceledException, IOException, IntegrationException {
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
            } catch (TimeoutException e) {
                // fall through and cancel
            } catch (InterruptedException e) {
                // fall through and cancel
            } catch (ExecutionException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof IOException) {
                    throw (IOException) cause;
                } else if (cause instanceof UnsupportedOperationException) {
                    throw new IntegrationException(cause);
                } else if (cause instanceof IntegrationException) {
                    throw (IntegrationException) cause;
                } else if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                } else if (cause instanceof Error) {
                    throw (Error) cause;
                } else {
                    throw new IllegalStateException(cause);
                }
            } finally {
                cancel(true /* interrupt if running */);
            }
            throw new OperationCanceledException();
        }

        @Override
        public Result getResult()
                throws OperationCanceledException, IOException, IntegrationException {
            return internalGetResult(null, null);
        }

        @Override
        protected void done() {
            if (mCallback != null) {
                postToHandler(mHandler, mCallback, this);
            }
        }

        /**
         * Handles the responses from the IntegrationManager
         */
        private class Response extends IIntegrationManagerResponse.Stub {
            private ComponentName componentName;
            private List<ComponentName> componentNames;

            Response(ComponentName componentName, List<ComponentName> componentNames) {
                this.componentName = componentName;
                this.componentNames = componentNames;
            }

            @Override
            public void onResult(Bundle bundle) {
                Intent intent = bundle.getParcelable(KEY_INTENT);
                if (intent != null && mActivity != null) {
                    // since the user provided an Activity we will silently start intents
                    // that we see
                    mActivity.startActivity(intent);
                    // leave the Future running to wait for the real response to this request
                } else if (bundle.getBoolean("retry")) {
                    try {
                        doWork(this);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                } else if (bundle.getBoolean(KEY_SKIP)) {
                    skip();
                } else {
                    set(new Result(Result.Type.INTERMEDIATE, bundle.getBundle(KEY_DATA)));
                }
            }

            @Override
            public void onError(int code, String message) {
                Log.e(TAG, "onError(code = " + code + ", message = " + message + ")");
                skip();
            }

            void skip() {
                if (componentNames == null || componentNames.isEmpty()) {
                    set(new Result(Result.Type.FINISH, null));
                } else {
                    try {
                        doWork(new Response(componentNames.get(0), componentNames.subList(1, componentNames.size())));
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            public ComponentName getComponentName() {
                return componentName;
            }
        }

    }

    private static List<ComponentName> convertImplicitIntentToExplicitIntent(Intent implicitIntent, Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);
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
}