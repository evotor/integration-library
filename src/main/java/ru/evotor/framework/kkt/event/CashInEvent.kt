package ru.evotor.framework.kkt.event

import android.os.Bundle
import java.math.BigDecimal

class CashInEvent(documentUuid: String, total: BigDecimal) : CashOperationEvent(documentUuid, total) {
    companion object {
        fun from(bundle: Bundle?): CashInEvent? = bundle?.let {
            CashInEvent(
                    getDocumentUuid(bundle) ?: return null,
                    getTotal(bundle) ?: return null
            )
        }
    }
}