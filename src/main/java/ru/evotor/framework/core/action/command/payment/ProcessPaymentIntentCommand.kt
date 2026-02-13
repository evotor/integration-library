package ru.evotor.framework.core.action.command.payment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ActivityStarter
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl

class ProcessPaymentIntentCommand(
        val receiptUuid: String
) : IBundlable {

    fun process(context: Context, callback: IntegrationManagerCallback) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, context.applicationContext)
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(context.applicationContext)
                .call(
                        NAME,
                        componentNameList[0],
                        this,
                        ActivityStarter(context),
                        callback,
                        Handler(Looper.getMainLooper())
                )
    }

    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_RECEIPT_UUID, receiptUuid)
    }

    companion object {
        const val NAME_PERMISSION = "ru.evotor.permission.receipt.USE_PAYMENT_INTENT_MODE"
        const val NAME = "evo.v2.receipt.processPaymentIntent"

        private const val KEY_RECEIPT_UUID = "receiptUuid"

        @JvmStatic
        fun create(bundle: Bundle?): ProcessPaymentIntentCommand? = bundle?.let {
            val receiptUuid = it.getString(KEY_RECEIPT_UUID, null) ?: return null
            ProcessPaymentIntentCommand(receiptUuid)
        }
    }
}


