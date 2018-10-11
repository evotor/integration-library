package ru.evotor.framework.kkt.event

import android.os.Bundle
import java.math.BigDecimal

class CashOutEvent(documentUuid: String, total: BigDecimal) : CashOperationEvent(documentUuid, total) {
    companion object {
        fun from(bundle: Bundle?): CashOutEvent? = bundle?.let {
            CashOutEvent(
                    getDocumentUuid(bundle) ?: return null,
                    getTotal(bundle) ?: return null
            )
        }
    }
}