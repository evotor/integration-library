package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.component.PaymentDelegator
import ru.evotor.framework.component.PaymentPerformer
import ru.evotor.framework.core.action.datamapper.PaymentDelegatorMapper
import ru.evotor.framework.core.action.datamapper.PaymentPerformerMapper

class CurrentReceiptDraftMovementToPaymentStageRequestedEvent internal constructor(
        val paymentDelegator: PaymentDelegator?,
        val paymentPerformer: PaymentPerformer?
) : IBundlable {
    override fun toBundle(): Bundle  = Bundle().apply {
        paymentDelegator?.let { putBundle(KEY_PAYMENT_DELEGATOR, PaymentDelegatorMapper.toBundle(it)) }
        paymentPerformer?.let { putBundle(KEY_PAYMENT_PERFORMER, PaymentPerformerMapper.toBundle(it)) }
    }

    companion object {
        fun from(bundle: Bundle?): CurrentReceiptDraftMovementToPaymentStageRequestedEvent? = bundle?.let {
            CurrentReceiptDraftMovementToPaymentStageRequestedEvent(
                    bundle.getBundle(KEY_PAYMENT_DELEGATOR)?.let {
                        PaymentDelegatorMapper.fromBundle(it)
                    },
                    bundle.getBundle(KEY_PAYMENT_PERFORMER)?.let {
                        PaymentPerformerMapper.fromBundle(it)
                    }
            )
        }

        private const val KEY_PAYMENT_PERFORMER = "paymentPerformer"
        private const val KEY_PAYMENT_DELEGATOR = "paymentDelegator"
    }
}