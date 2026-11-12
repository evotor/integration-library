package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.receipt.Receipt

object ReceiptStatusMapper {
    private const val KEY_STATUS = "STATUS"

    @JvmStatic
    fun from(bundle: Bundle?): Receipt.Status? {
        if (bundle == null) {
            return null
        }

        return if (bundle.containsKey(KEY_STATUS)) {
            Receipt.Status.values().getOrNull(bundle.getInt(KEY_STATUS)).let {
                it ?: return null
            }
        } else {
            null
        }
    }

    @JvmStatic
    fun Bundle.addStatus(status: Receipt.Status) {
        putInt(KEY_STATUS, status.ordinal)
    }
}
