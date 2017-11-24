package ru.evotor.integrations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Чтобы получать push-уведомления от своего сервера, необходимо унаследоваться от {@link PushNotificationReceiver},
 * зарегистрироваться с помощью {@link PushNotificationReceiver#onReceiveCorrelationId(Context, String)}.
 * После регистрации, через {@link PushNotificationReceiver#onReceivePushNotification(Context, Bundle, long)}
 * можно получить индентификтор устройства, который уже используется сервером, для рассылки пуш уведомлений.
 */
public abstract class PushNotificationReceiver extends BroadcastReceiver {

    private static final String REGISTER_APP_ACTION = "REGISTER_APP_ACTION";
    private static final String REGISTER_APP_PACKAGE = "REGISTER_APP_PACKAGE";
    private static final String PUSH_DATA = "PUSH_DATA";
    private static final String PUSH_MESSAGE_ID = "PUSH_MESSAGE_ID";
    private static final String CORRELATION_ID = "CORRELATION_ID";

    @Override
    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.hasExtra(CORRELATION_ID)) {
                onReceiveCorrelationId(context, intent.getStringExtra(CORRELATION_ID));
            } else {
                onReceivePushNotification(context, intent.getBundleExtra(PUSH_DATA),
                        intent.getLongExtra(PUSH_MESSAGE_ID, 0L));
            }
        }
    }

    /**
     * Обработка push-уведомление
     *
     * @param context   контекст приложения
     * @param data      данные push-уведомления в виде Bundle (JSON конвертируется в Bundle)
     * @param messageId индентификатор push-уведомления
     */
    public abstract void onReceivePushNotification(Context context, Bundle data, long messageId);

    /**
     * Получение идентификатора, необходимого для отправки push-уведомлений
     *
     * @param context       контекст приложения
     * @param correlationId данный индентификтор устройства необходимо передать серверу, для отправления пуш уведомлений
     */
    public abstract void onReceiveCorrelationId(Context context, String correlationId);

    /**
     * Регистрация приложения, для получения push-уведомлений.
     * Данная операция должна выполняться один раз.
     * После регистрации, вызовится {@link PushNotificationReceiver#onReceiveCorrelationId(Context, String)}
     *
     * @param context контекст приложения
     */
    public static void registerAppToReceiveNotifications(Context context) {
        Intent intent = new Intent();
        intent.setAction(REGISTER_APP_ACTION);
        intent.putExtra(REGISTER_APP_PACKAGE, context.getApplicationContext().getPackageName());
        context.sendBroadcast(intent);
    }
}