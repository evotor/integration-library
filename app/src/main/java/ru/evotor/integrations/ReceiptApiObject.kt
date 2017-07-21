package ru.evotor.integrations

import android.net.Uri
import java.math.BigDecimal

/**
 * Created by nixan on 06.03.17.
 */
@Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
val CURRENT_RECEIPT_URI: Uri = Uri.parse("content://ru.evotor.evotorpos.receipt/sell")
@Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
val CURRENT_RECEIPT_ITEMS_URI: Uri = Uri.parse("content://ru.evotor.evotorpos.receipt/sellReceiptPositions")
@Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
val CURRENT_RECEIPT_PAYMENTS_URI: Uri = Uri.parse("content://ru.evotor.evotorpos.receipt/sellReceiptPayments")

@Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
class Receipt(val uuid: String) {

}

@Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
data class ReceiptApiObject(val uuid: String, val number: Long, val totalSum: BigDecimal, val discount: BigDecimal, val originalTotalSum: BigDecimal, val positionDiscountSum: BigDecimal, val positionsCount: Int)

@Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
data class ReceiptItemApiObject(val uuid: String, val type: String, val code: String, val measure: String, val price: BigDecimal, val priceWithDiscount: BigDecimal, val quantity: BigDecimal, val barcode: String, val mark: String)

@Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
data class ReceiptPaymentApiObject(val uuid: String, val amount: BigDecimal)

@Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
sealed class PrintGroupApiObject(val uuid: String) {
    @Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
    class FiscalReceipt(uuid: String) : PrintGroupApiObject(uuid)

    @Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
    class NonFiscalReceipt(uuid: String, val organizationName: String, val organizationVat: String) : PrintGroupApiObject(uuid)

    @Deprecated("Use ru.evotor.framework.receipt.ReceiptApi")
    class SimplifiedTaxationSystemReceipt(uuid: String, val organizationName: String, val organizationVat: String) : PrintGroupApiObject(uuid)

}