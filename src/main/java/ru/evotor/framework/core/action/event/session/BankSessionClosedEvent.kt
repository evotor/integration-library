package ru.evotor.framework.core.action.event.session

import android.os.Bundle

data class BankSessionClosedEvent(
    val paymentSystemAccountId: Int,
    val slipLines: Array<String>
) : SessionEvent() {


    override fun toBundle(): Bundle {
        return Bundle().apply {
            this.putInt(KEY_PAYMENT_SYSTEM_ACCOUNT_ID, paymentSystemAccountId)
            this.putStringArray(KEY_SLIP_LINES, slipLines)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BankSessionClosedEvent

        if (paymentSystemAccountId != other.paymentSystemAccountId) return false
        if (!slipLines.contentEquals(other.slipLines)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = paymentSystemAccountId
        result = 31 * result + slipLines.contentHashCode()
        return result
    }

    companion object {
        const val BROADCAST_ACTION_BANK_SESSION_CLOSED = "evotor.intent.action.reports.BANK_SESSION_CLOSED"

        private const val KEY_PAYMENT_SYSTEM_ACCOUNT_ID = "paymentSystemAccountId"
        private const val KEY_SLIP_LINES = "slipLines"

        fun create(bundle: Bundle?): BankSessionClosedEvent? {
            return bundle?.let {
                BankSessionClosedEvent(
                    bundle.getInt(KEY_PAYMENT_SYSTEM_ACCOUNT_ID),
                    bundle.getStringArray(KEY_SLIP_LINES) ?: emptyArray()
                )
            }
        }
    }

}
