package ru.evotor.framework.kkt.event

import android.os.Bundle

import ru.evotor.IBundlable
import ru.evotor.framework.getMoney
import java.math.BigDecimal

abstract class CashOperationEvent internal constructor(val documentUuid: String, val total: BigDecimal) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_DOCUMENT_UUID, documentUuid)
        result.putString(KEY_TOTAL, total.toPlainString())
        return result
    }

    companion object {

        private const val KEY_DOCUMENT_UUID = "documentUuid"

        private const val KEY_TOTAL = "total"

        internal fun getDocumentUuid(bundle: Bundle): String? = bundle.getString(KEY_DOCUMENT_UUID)

        internal fun getTotal(bundle: Bundle): BigDecimal? = bundle.getMoney(KEY_TOTAL)
    }
}
