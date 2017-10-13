package ru.evotor.integrations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

/**
 * Чтобы получать пуши от своего сервера, необходимо унаследоваться от {@link PushNotificationReceiver},
 * зарегистрироваться с помощью registerAppToReceiveNotifications. После регистрации, через
 * {@link PushNotificationReceiver#onReceivePushNotification(android.content.Context, android.os.Bundle, long)}
 * можно получить индентификтор устройства, который уже используется сервером, для рассылки пуш уведомлений.
 */
public abstract class PushNotificationReceiver extends BroadcastReceiver {

    public static final String REGISTER_APP_ACTION = "REGISTER_APP_ACTION";
    public static final String REGISTER_APP_PACKAGE = "REGISTER_APP_PACKAGE";
    public static final String PUSH_DATA = "PUSH_DATA";
    public static final String PUSH_MESSAGE_ID = "PUSH_MESSAGE_ID";
    public static final String CORRELATION_ID = "CORRELATION_ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if(intent.hasExtra(CORRELATION_ID)){
                onReceiveCorrelationID(context, intent.getStringExtra(CORRELATION_ID));
            } else {
                onReceivePushNotification(context, intent.getBundleExtra(PUSH_DATA),
                        intent.getLongExtra(PUSH_MESSAGE_ID, 0L));
            }
        }
    }

    /**
     * Обработка пуш уведомления
     * @param context контекст приложения
     * @param data данные пуш уведомления в виде Bundle (JSON конвертируется в Bundle)
     * @param messageId индентификатор пуш уведомления
     */
    public abstract void onReceivePushNotification(Context context, Bundle data, long messageId);

    /**
     * Получение индентификатора, нужный для посылки пушей
     * @param context контекст приложения
     * @param correlationID данный индентификтор устройства необходимо передать серверу, для отправления пуш уведомлений
     */
    public abstract void onReceiveCorrelationID(Context context, String correlationID);

    /**
     * Регистрация приложения, для получения пушей.
     * Данная операция должна выполняться один раз.
     * После регистрации, вызовится {@link PushNotificationReceiver#onReceivePushNotification(android.content.Context, android.os.Bundle, long)}
     * @param context контекст приложения
     */
    public static void registerAppToReceiveNotifications(Context context) {
        Intent intent = new Intent();
        intent.setAction(REGISTER_APP_ACTION);
        intent.putExtra(REGISTER_APP_PACKAGE, context.getApplicationContext().getPackageName());
        context.sendBroadcast(intent);
    }
}