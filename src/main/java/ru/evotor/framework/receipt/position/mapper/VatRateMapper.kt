package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.receipt.TaxNumber
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.framework.receipt.provider.ReceiptContract

internal object VatRateMapper {
    fun read(cursor: Cursor): VatRate? = cursor.safeGetString(ReceiptContract.Position.VAT_RATE)?.let {
        when (it) {
            TaxNumber.NO_VAT.name -> VatRate.WITHOUT_VAT
            TaxNumber.VAT_0.name -> VatRate.VAT_0
            TaxNumber.VAT_10.name -> VatRate.VAT_10
            TaxNumber.VAT_10_110.name -> VatRate.VAT_10_110
            TaxNumber.VAT_18.name -> VatRate.VAT_20
            TaxNumber.VAT_18_118.name -> VatRate.VAT_20_118
            else -> null
        }
    }
}