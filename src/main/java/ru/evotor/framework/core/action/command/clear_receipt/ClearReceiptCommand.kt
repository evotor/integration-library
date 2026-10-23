package ru.evotor.framework.core.action.command.clear_receipt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ActivityStarter
import ru.evotor.framework.core.ICanStartActivity
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl

/**
 * Команда очистки чека
 * @param receiptUuid - UUID чека
 */
class ClearReceiptCommand(val receiptUuid: String) : IBundlable {
    companion object {
        private const val KEY_RECEIPT_UUID = "receiptUuid"

        const val NAME = "evo.v2.receipt.clear"

        fun from(bundle: Bundle?) = bundle?.getString(KEY_RECEIPT_UUID, null)?.let(::ClearReceiptCommand)
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_RECEIPT_UUID, receiptUuid)
        }
    }

    fun process(
        activity: Activity,
        callback: IntegrationManagerCallback?
    ) {
        process(activity, ActivityStarter(activity, false), callback)
    }

    fun process(
        context: Context,
        activityStarter: ICanStartActivity,
        callback: IntegrationManagerCallback?
    ) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, context.applicationContext)
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(context.applicationContext)
            .call(
                NAME,
                componentNameList[0],
                this,
                activityStarter,
                callback,
                Handler(Looper.getMainLooper())
            )
    }
}
