package ru.evotor.framework.core.action.command.print_receipt_command

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra
import ru.evotor.framework.receipt.Payment
import ru.evotor.framework.receipt.Position
import ru.evotor.framework.receipt.PrintGroup
import ru.evotor.framework.receipt.Receipt
import ru.evotor.framework.sumByBigDecimal
import java.math.BigDecimal
import java.util.*

class PrintCorrectionIncomeReceiptCommand(
    val printReceipts: List<Receipt.PrintReceipt>,
    val extra: SetExtra?,
    val clientPhone: String? = null,
    val clientEmail: String? = null,
    val receiptDiscount: BigDecimal? = BigDecimal.ZERO,
    val paymentAddress: String?,
    val paymentPlace: String?,
    val userUuid: String?
): IBundlable {
    companion object {
        const val NAME = "evo.v2.receipt.correction.outcome.printReceipt"
        private const val KEY_PRINT_RECEIPTS = "printReceipts"
        private const val KEY_RECEIPT_EXTRA = "extra"
        private const val KEY_CLIENT_EMAIL = "clientEmail"
        private const val KEY_CLIENT_PHONE = "clientPhone"
        private const val KEY_RECEIPT_DISCOUNT = "receiptDiscount"
        private const val KEY_PAYMENT_ADDRESS = "paymentAddress"
        private const val KEY_PAYMENT_PLACE = "paymentPlace"
        private const val KEY_USER_UUID = "userUuid"
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {

        }
    }
}