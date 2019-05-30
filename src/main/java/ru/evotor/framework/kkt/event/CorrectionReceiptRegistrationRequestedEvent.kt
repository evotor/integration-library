package ru.evotor.framework.kkt.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.payment.PaymentType
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.TaxationSystem
import ru.evotor.framework.receipt.correction.CorrectionType
import ru.evotor.framework.receipt.position.VatRate
import java.math.BigDecimal
import java.util.*

class CorrectionReceiptRegistrationRequestedEvent internal constructor(
        val settlementType: SettlementType,
        val taxationSystem: TaxationSystem,
        val correctionType: CorrectionType,
        val basisForCorrection: String,
        val prescriptionNumber: String,
        val correctableSettlementDate: Date,
        val amountPaid: BigDecimal,
        val paymentType: PaymentType,
        val vatRate: VatRate,
        val correctionDescription: String
) : IBundlable {
    companion object {
        private const val KEY_TAXATION_SYSTEM = "TAXATION_SYSTEM"
        private const val KEY_SETTLEMENT_TYPE = "SETTLEMENT_TYPE"
        private const val KEY_AMOUNT_PAID = "MOUNT_PAID"
        private const val KEY_PAYMENT_TYPE = "PAYMENT_TYPE"
        private const val KEY_VAT_RATE = "VAT_RATE"
        private const val KEY_CORRECTION_TYPE = "CORRECTION_TYPE"
        private const val KEY_BASIS_FOR_CORRECTION = "BASIS_FOR_CORRECTION"
        private const val KEY_CORRECTION_DESCRIPTION = "CORRECTION_DESCRIPTION"
        private const val KEY_CORRECTABLE_SETTLEMENT_DATE = "CORRECTABLE_SETTLEMENT_DATE"
        private const val KEY_PRESCRIPTION_NUMBER = "PRESCRIPTION_NUMBER"

        fun from(bundle: Bundle?): CorrectionReceiptRegistrationRequestedEvent? = bundle?.let {
            CorrectionReceiptRegistrationRequestedEvent(
                    SettlementType.valueOf(it.getString(KEY_SETTLEMENT_TYPE)),
                    TaxationSystem.valueOf(it.getString(KEY_TAXATION_SYSTEM)),
                    CorrectionType.values()[it.getInt(KEY_CORRECTION_TYPE)],
                    it.getString(KEY_BASIS_FOR_CORRECTION),
                    it.getString(KEY_PRESCRIPTION_NUMBER),
                    Date(it.getLong(KEY_CORRECTABLE_SETTLEMENT_DATE)),
                    BigDecimal(it.getString(KEY_AMOUNT_PAID)),
                    PaymentType.valueOf(it.getString(KEY_PAYMENT_TYPE)),
                    VatRate.valueOf(it.getString(KEY_VAT_RATE)),
                    it.getString(KEY_CORRECTION_DESCRIPTION)
            )
        }
    }

    override fun toBundle() = Bundle().apply {
        this.putString(KEY_SETTLEMENT_TYPE, settlementType.name)
        this.putString(KEY_TAXATION_SYSTEM, taxationSystem.name)
        this.putString(KEY_CORRECTION_TYPE, correctionType.name)
        this.putString(KEY_BASIS_FOR_CORRECTION, basisForCorrection)
        this.putString(KEY_PRESCRIPTION_NUMBER, prescriptionNumber)
        this.putLong(KEY_CORRECTABLE_SETTLEMENT_DATE, correctableSettlementDate.time)
        this.putString(KEY_AMOUNT_PAID, amountPaid.toString())
        this.putString(KEY_PAYMENT_TYPE, paymentType.name)
        this.putString(KEY_VAT_RATE, vatRate.name)
        this.putString(KEY_CORRECTION_DESCRIPTION, correctionDescription)
    }
}