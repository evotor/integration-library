package ru.evotor.framework.kkt.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.payment.PaymentType
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.TaxNumber
import ru.evotor.framework.receipt.TaxationSystem
import ru.evotor.framework.receipt.correction.CorrectionType
import java.math.BigDecimal
import java.util.*

class CorrectionReceiptRegistrationRequestedEvent internal constructor(
        val taxationSystem: TaxationSystem,
        val settlementType: SettlementType,
        val sum: BigDecimal,
        val paymentType: PaymentType,
        val taxNumber: TaxNumber,
        val correctionType: CorrectionType,
        val basisForCorrection: String,
        val correctionDescription: String?,
        val correctableSettlementDate: Date,
        val prescriptionNumber: String
) : IBundlable {
    companion object {
        private const val KEY_TAXATION_SYSTEM = "TAXATION_SYSTEM"
        private const val KEY_SETTLEMENT_TYPE = "SETTLEMENT_TYPE"
        private const val KEY_SUM = "SUM"
        private const val KEY_PAYMENT_TYPE = "PAYMENT_TYPE"
        private const val KEY_TAX_NUMBER = "TAX_NUMBER"
        private const val KEY_CORRECTION_TYPE = "CORRECTION_TYPE"
        private const val KEY_BASIS_FOR_CORRECTION = "BASIS_FOR_CORRECTION"
        private const val KEY_CORRECTION_DESCRIPTION = "CORRECTION_DESCRIPTION"
        private const val KEY_CORRECTABLE_SETTLEMENT_DATE = "CORRECTABLE_SETTLEMENT_DATE"
        private const val KEY_PRESCRIPTION_NUMBER = "PRESCRIPTION_NUMBER"

        fun from(bundle: Bundle?): CorrectionReceiptRegistrationRequestedEvent? = bundle?.let {
            CorrectionReceiptRegistrationRequestedEvent(
                    TaxationSystem.values()[it.getInt(KEY_TAXATION_SYSTEM)],
                    SettlementType.values()[it.getInt(KEY_SETTLEMENT_TYPE)],
                    BigDecimal(it.getString(KEY_SUM)),
                    PaymentType.values()[it.getInt(KEY_PAYMENT_TYPE)],
                    TaxNumber.values()[it.getInt(KEY_TAX_NUMBER)],
                    CorrectionType.values()[it.getInt(KEY_CORRECTION_TYPE)],
                    it.getString(KEY_BASIS_FOR_CORRECTION),
                    it.getString(KEY_CORRECTION_DESCRIPTION),
                    Date(it.getLong(KEY_CORRECTABLE_SETTLEMENT_DATE)),
                    it.getString(KEY_PRESCRIPTION_NUMBER)
            )
        }
    }

    override fun toBundle() = Bundle().apply {
        this.putInt(KEY_TAXATION_SYSTEM, taxationSystem.ordinal)
        this.putInt(KEY_SETTLEMENT_TYPE, settlementType.ordinal)
        this.putString(KEY_SUM, sum.toString())
        this.putInt(KEY_PAYMENT_TYPE, paymentType.ordinal)
        this.putInt(KEY_TAX_NUMBER, taxNumber.ordinal)
        this.putInt(KEY_CORRECTION_TYPE, correctionType.ordinal)
        this.putString(KEY_BASIS_FOR_CORRECTION, basisForCorrection)
        this.putString(KEY_CORRECTION_DESCRIPTION, correctionDescription)
        this.putLong(KEY_CORRECTABLE_SETTLEMENT_DATE, correctableSettlementDate.time)
        this.putString(KEY_PRESCRIPTION_NUMBER, prescriptionNumber)
    }
}