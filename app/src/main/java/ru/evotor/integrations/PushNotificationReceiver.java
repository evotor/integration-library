package ru.evotor.integrations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public abstract class PushNotificationReceiver extends BroadcastReceiver {

    public static final String REGISTER_APP_ACTION = "REGISTER_APP_ACTION";
    public static final String CORRELATION_ID_APP_ACTION = "CORRELATION_ID_APP_ACTION";
    public static final String REGISTER_APP_PACKAGE = "REGISTER_APP_PACKAGE";
    public static final String PUSH_DATA = "PUSH_DATA";
    public static final String PUSH_RESULT_RECEIVER = "PUSH_RESULT_RECEIVER";
    public static final String PUSH_MESSAGE_ID = "PUSH_MESSAGE_ID";
    public static final String CORRELATION_ID = "CORRELATION_ID";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if(intent.hasExtra(CORRELATION_ID)){
                this.onReceiveCorrelationID(context, intent.getStringExtra(CORRELATION_ID));
            } else {
                ResultReceiver resultReceiver = null;
                if (intent.getParcelableExtra(PUSH_RESULT_RECEIVER) instanceof ResultReceiver) {
                    resultReceiver = intent.getParcelableExtra(PUSH_RESULT_RECEIVER);
                }
                this.onReceivePushNotification(
                        context,
                        intent.getBundleExtra(PUSH_DATA),
                        intent.getLongExtra(PUSH_MESSAGE_ID, 0L),
                        resultReceiver);
            }
        }
    }

    public abstract void onReceivePushNotification(Context context, Bundle data, long messageId, ResultReceiver resultReceiver);

    public abstract void onReceiveCorrelationID(Context context, String correlationID);

    public static void registerAppToReceiveNotifications(Context context) {
        Intent intent = new Intent();
        intent.setAction(REGISTER_APP_ACTION);
        intent.putExtra(REGISTER_APP_PACKAGE, context.getApplicationContext().getPackageName());
        context.sendBroadcast(intent);
    }
}