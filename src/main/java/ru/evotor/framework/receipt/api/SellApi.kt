package ru.evotor.framework.receipt.api

import android.app.Activity
import android.content.Context
import android.content.Intent
import ru.evotor.framework.receipt.ReceiptDraft
import ru.evotor.framework.receipt.position.Position

object SellApi {
    private const val ACTION_MAIN = "evotor.intent.action.edit.SELL"
    private const val ACTION_PAYMENT = "evotor.intent.action.payment.SELL"

    fun startReceiptsFormation(activity: Activity, positions: List<Position>): String

    fun getCurrentReceiptDrafts(context: Context): List<ReceiptDraft>

    fun createIntentForMainActivity() = Intent(ACTION_MAIN)

    fun createIntentForPaymentActivity() = Intent(ACTION_PAYMENT)
}