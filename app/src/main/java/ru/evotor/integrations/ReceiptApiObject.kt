package ru.evotor.integrations

import android.net.Uri
import java.math.BigDecimal

/**
 * Created by nixan on 06.03.17.
 */

val CURRENT_RECEIPT_URI: Uri = Uri.parse("content://ru.evotor.evotorpos.receipt/sell")
val CURRENT_RECEIPT_ITEMS_URI: Uri = Uri.parse("content://ru.evotor.evotorpos.receipt/sellReceiptPositions")
val CURRENT_RECEIPT_PAYMENTS_URI: Uri = Uri.parse("content://ru.evotor.evotorpos.receipt/sellReceiptPayments")

class Receipt(val uuid: String) {

}

data class ReceiptApiObject(val uuid: String, val number: Long, val totalSum: BigDecimal, val discount: BigDecimal, val originalTotalSum: BigDecimal, val positionDiscountSum: BigDecimal, val positionsCount: Int)

data class ReceiptItemApiObject(val uuid: String, val type: String, val code: String, val measure: String, val price: BigDecimal, val priceWithDiscount: BigDecimal, val quantity: BigDecimal, val barcode: String, val mark: String)

data class ReceiptPaymentApiObject(val uuid: String, val amount: BigDecimal)

sealed class PrintGroupApiObject(val uuid: String) {

    class FiscalReceipt(uuid: String) : PrintGroupApiObject(uuid)

    class NonFiscalReceipt(uuid: String, val organizationName: String, val organizationVat: String) : PrintGroupApiObject(uuid)

    class SimplifiedTaxationSystemReceipt(uuid: String, val organizationName: String, val organizationVat: String) : PrintGroupApiObject(uuid)

}