package ru.evotor.framework.receipt.api

import android.content.Context
import android.content.Intent
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.action.command.open_receipt_command.OpenSellReceiptCommand
import ru.evotor.framework.core.action.event.receipt.changes.position.PositionAdd
import ru.evotor.framework.receipt.ReceiptDraft
import ru.evotor.framework.receipt.position.Position
import java.util.*

object SellApi {
    private const val ACTION_MAIN = "evotor.intent.action.edit.SELL"
    private const val ACTION_PAYMENT = "evotor.intent.action.payment.SELL"

 //   fun startReceiptsFormation(context: Context, positions: List<Position> = emptyList())

 //   fun getCurrentReceiptDrafts(context: Context): List<ReceiptDraft>

//    fun replaceReceiptDraft(context: Context, oldReceiptDraftUuid: UUID, newReceiptDraft: ReceiptDraft?)

 //   fun deleteReceiptDraft(context: Context, receiptDraftUuid: UUID)

    fun createIntentForMainActivity() = Intent(ACTION_MAIN)

    fun createIntentForPaymentActivity() = Intent(ACTION_PAYMENT)
}