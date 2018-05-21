package ru.evotor.framework.core.action.event.receipt.changes.position

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.PrintGroupMapper
import ru.evotor.framework.core.action.event.receipt.changes.IChange
import ru.evotor.framework.receipt.PrintGroup

data class SetPrintGroup(val printGroup: PrintGroup?, val paymentPurposeIds: List<String>, val positionUuids: List<String>) : IChange {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putBundle(
                    KEY_PRINT_GROUP,
                    PrintGroupMapper.toBundle(printGroup)
            )
            putStringArrayList(
                    KEY_PAYMENT_PURPOSE_ID,
                    ArrayList(paymentPurposeIds)
            )
            putStringArrayList(
                    KEY_PAYMENT_POSITIONS_UUIDS,
                    ArrayList(positionUuids)
            )
        }
    }

    override fun getType() = IChange.Type.SET_PAYMENT_PURPOSE_PRINT_GROUP

    companion object {
        const val KEY_PRINT_GROUP = "printGroup"
        const val KEY_PAYMENT_PURPOSE_ID = "paymentPurposeIds"
        const val KEY_PAYMENT_POSITIONS_UUIDS = "positionUuids"

        @JvmStatic
        fun from(bundle: Bundle?): SetPrintGroup? {
            bundle ?: return null

            val printGroup = PrintGroupMapper.from(bundle.getBundle(KEY_PRINT_GROUP))

            val paymentPurposeIds = bundle.getStringArrayList(KEY_PAYMENT_PURPOSE_ID)
            val positionsUuids = bundle.getStringArrayList(KEY_PAYMENT_POSITIONS_UUIDS)

            if (paymentPurposeIds == null || positionsUuids == null || printGroup == null) {
                return null
            }

            return SetPrintGroup(printGroup, paymentPurposeIds, positionsUuids)
        }
    }
}