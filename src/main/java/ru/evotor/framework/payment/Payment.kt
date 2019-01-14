package ru.evotor.framework.payment

import java.util.*

sealed class Payment constructor(
        /**
         * Uuid
         */
        val uuid: UUID = UUID.randomUUID(),
        /**
         * Сумма
         */
        val sum: AmountOfRubles,
        /**
         * Использованное платёжное средство
         */
        val paymentMean: PaymentMean,
        /**
         * Использованный способ оплаты
         */
        val paymentMethod: PaymentMethod?
) {
    constructor(
            /**
             * Uuid
             */
            uuid: UUID = UUID.randomUUID(),
            /**
             * Сумма
             */
            sum: AmountOfRubles,
            /**
             * Использованное платёжное средство
             */
            paymentMean: PaymentMean
    ) : this(uuid, sum, paymentMean, null)

    init {
        if (sum == AmountOfRubles(0))
            throw IllegalArgumentException("Sum must be more than zero!")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Payment) return false

        if (uuid != other.uuid) return false
        if (sum != other.sum) return false
        if (paymentMean != other.paymentMean) return false
        if (paymentMethod != other.paymentMethod) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + sum.hashCode()
        result = 31 * result + paymentMean.hashCode()
        result = 31 * result + (paymentMethod?.hashCode() ?: 0)
        return result
    }
}

class CashlessPayment internal constructor(
        /**
         * Uuid
         */
        uuid: UUID,
        /**
         * Сумма
         */
        sum: AmountOfRubles,
        /**
         * Использованный способ оплаты
         */
        paymentMethod: PaymentMethod?,
        /**
         * RRN (Идентификатор оплаты в банке)
         */
        val rrn: String,
        /**
         * Использованный пинпад
         */
        val pinpad: Int?
) : Payment(uuid, sum, PaymentMean.CASHLESS, paymentMethod) {
    constructor(
            /**
             * Uuid
             */
            uuid: UUID = UUID.randomUUID(),
            /**
             * Сумма
             */
            sum: AmountOfRubles,
            /**
             * RRN (Идентификатор оплаты в банке)
             */
            rrn: String
    ) : this(uuid, sum, null, rrn, null)

}