package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.safeGetEnumByName
import ru.evotor.framework.receipt.TaxNumber
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.framework.receipt.provider.ReceiptContract

internal object VatRateMapper {
    fun read(cursor: Cursor): VatRate? =
            cursor.safeGetEnumByName(ReceiptContract.Position.VAT_RATE, TaxNumber::class.java)?.let {
                when (it) {
                    TaxNumber.NO_VAT -> VatRate.WITHOUT_VAT
                    TaxNumber.VAT_0 -> VatRate.VAT_0
                    TaxNumber.VAT_10 -> VatRate.VAT_10
                    TaxNumber.VAT_10_110 -> VatRate.VAT_10_110
                    TaxNumber.VAT_18 -> VatRate.VAT_20
                    TaxNumber.VAT_18_118 -> VatRate.VAT_20_120
                }
            }
}