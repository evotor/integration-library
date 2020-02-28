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
        val correctionDescription: String,
        var paymentAddress: String? = null,
        var paymentPlace: String? = null
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
        private const val KEY_PAYMENT_ADDRESS = "PAYMENT_ADDRESS"
        private const val KEY_PAYMENT_PLACE = "PAYMENT_PLACE"

        fun from(bundle: Bundle?): CorrectionReceiptRegistrationRequestedEvent? = bundle?.let {
            CorrectionReceiptRegistrationRequestedEvent(
                    SettlementType.valueOf(it.getString(KEY_SETTLEMENT_TYPE)),
                    TaxationSystem.valueOf(it.getString(KEY_TAXATION_SYSTEM)),
                    getCorrectionType(it),
                    it.getString(KEY_BASIS_FOR_CORRECTION),
                    it.getString(KEY_PRESCRIPTION_NUMBER),
                    Date(it.getLong(KEY_CORRECTABLE_SETTLEMENT_DATE)),
                    BigDecimal(it.getString(KEY_AMOUNT_PAID)),
                    PaymentType.valueOf(it.getString(KEY_PAYMENT_TYPE)),
                    VatRate.valueOf(it.getString(KEY_VAT_RATE)),
                    it.getString(KEY_CORRECTION_DESCRIPTION),
                    it.getString(KEY_PAYMENT_ADDRESS),
                    it.getString(KEY_PAYMENT_PLACE)
            )
        }

        private fun getCorrectionType(bundle: Bundle): CorrectionType {
            val incorrectOrdinal = -1
            val ordinal = bundle.getInt(KEY_CORRECTION_TYPE, incorrectOrdinal)
            return if (ordinal != incorrectOrdinal) {
                CorrectionType.values()[ordinal]
            } else {
                CorrectionType.valueOf(bundle.getString(KEY_CORRECTION_TYPE))
            }
        }
    }

    override fun toBundle() = Bundle().apply {
        putString(KEY_SETTLEMENT_TYPE, settlementType.name)
        putString(KEY_TAXATION_SYSTEM, taxationSystem.name)
        putString(KEY_CORRECTION_TYPE, correctionType.name)
        putString(KEY_BASIS_FOR_CORRECTION, basisForCorrection)
        putString(KEY_PRESCRIPTION_NUMBER, prescriptionNumber)
        putLong(KEY_CORRECTABLE_SETTLEMENT_DATE, correctableSettlementDate.time)
        putString(KEY_AMOUNT_PAID, amountPaid.toString())
        putString(KEY_PAYMENT_TYPE, paymentType.name)
        putString(KEY_VAT_RATE, vatRate.name)
        putString(KEY_CORRECTION_DESCRIPTION, correctionDescription)
        putString(KEY_PAYMENT_ADDRESS, paymentAddress)
        putString(KEY_PAYMENT_PLACE, paymentPlace)
    }
}