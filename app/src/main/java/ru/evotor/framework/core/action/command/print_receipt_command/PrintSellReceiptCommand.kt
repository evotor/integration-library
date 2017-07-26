package ru.evotor.framework.core.action.command.print_receipt_command

import android.app.Activity
import android.content.ComponentName
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import java.util.Objects

import ru.evotor.IBundlable
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl
import ru.evotor.framework.core.action.datamapper.ReceiptMapper
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra
import ru.evotor.framework.receipt.Receipt


class PrintSellReceiptCommand(val receipt: Receipt, val extra: SetExtra?) : IBundlable {

    fun process(activity: Activity, callback: IntegrationManagerCallback) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, activity.applicationContext)
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(activity.applicationContext)
                .call(PrintSellReceiptCommand.NAME,
                        componentNameList[0],
                        this,
                        activity,
                        callback,
                        Handler(Looper.getMainLooper())
                )
    }

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putBundle(KEY_RECEIPT, ReceiptMapper.toBundle(receipt))
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra?.toBundle())
        return bundle
    }

    companion object {

        const val NAME = "evo.v2.receipt.sell.printReceipt"
        private const val KEY_RECEIPT = "receipt"
        private const val KEY_RECEIPT_EXTRA = "extra"

        fun create(bundle: Bundle?): PrintSellReceiptCommand? {
            if (bundle == null) {
                return null
            }
            return PrintSellReceiptCommand(
                    ReceiptMapper.from(bundle.getBundle(KEY_RECEIPT))!!,
                    SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
            )
        }
    }

}
