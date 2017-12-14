package ru.evotor.integrations

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * Чтобы получать push-уведомления от своего сервера, необходимо унаследоваться от [PushNotificationReceiver]
 */
abstract class PushNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let {
            onReceivePushNotification(context, intent.getBundleExtra(PUSH_DATA),
                    intent.getLongExtra(PUSH_MESSAGE_ID, 0L))
        }
    }

    /**
     * Обработка push-уведомления
     *
     * @param context   контекст приложения
     * @param data      данные push-уведомления в виде Bundle (JSON конвертируется в Bundle)
     * @param messageId индентификатор push-уведомления
     */
    abstract fun onReceivePushNotification(context: Context, data: Bundle, messageId: Long)

    companion object {
        private val PUSH_DATA = "PUSH_DATA"
        private val PUSH_MESSAGE_ID = "PUSH_MESSAGE_ID"
    }
}