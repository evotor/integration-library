package ru.evotor.framework.payment

import java.util.*

/**
 * Оплата
 */
sealed class Payment(
        /**
         * Uuid
         */
        val uuid: UUID,
        /**
         * Сумма
         */
        val sum: AmountOfRubles,
        /**
         * Использованный способ оплаты или null, если оплата была произведена не на терминале
         */
        val paymentMethod: PaymentMethod?
) {
    init {
        if (sum == AmountOfRubles(0))
            throw IllegalArgumentException("Sum must be more than zero!")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Payment) return false

        if (uuid != other.uuid) return false
        if (sum != other.sum) return false
        if (paymentMethod != other.paymentMethod) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + sum.hashCode()
        result = 31 * result + (paymentMethod?.hashCode() ?: 0)
        return result
    }
}

/**
 * Оплата наличными
 */
class CashPayment internal constructor(
        /**
         * Uuid
         */
        uuid: UUID,
        /**
         * Сумма
         */
        sum: AmountOfRubles,
        /**
         * Использованный способ оплаты или null, если оплата была произведена не на терминале
         */
        paymentMethod: PaymentMethod?
) : Payment(uuid, sum, paymentMethod) {
    constructor(
            /**
             * Uuid
             */
            uuid: UUID = UUID.randomUUID(),
            /**
             * Сумма
             */
            sum: AmountOfRubles
    ) : this(uuid, sum, null)
}

/**
 * Оплата безналичными
 */
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
         * Использованный способ оплаты или null, если оплата была произведена не на терминале
         */
        paymentMethod: PaymentMethod?,
        /**
         * Использованный пинпад или null, если оплата была произведена без пинпада
         */
        val pinpad: Pinpad?,
        /**
         * RRN (Идентификатор оплаты в банке, который про)
         */
        val rrn: String
) : Payment(uuid, sum, paymentMethod) {
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
    ) : this(uuid, sum, null, null, rrn)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CashlessPayment) return false
        if (!super.equals(other)) return false

        if (rrn != other.rrn) return false
        if (pinpad != other.pinpad) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + rrn.hashCode()
        result = 31 * result + (pinpad?.hashCode() ?: 0)
        return result
    }
}

/**
 * Предоплата - оплата предварительно внесенными деньгами
 */
class Prepayment internal constructor(
        /**
         * Uuid
         */
        uuid: UUID,
        /**
         * Сумма
         */
        sum: AmountOfRubles,
        /**
         * Использованный способ оплаты или null, если оплата была произведена не на терминале
         */
        paymentMethod: PaymentMethod?
) : Payment(uuid, sum, paymentMethod) {
    constructor(
            /**
             * Uuid
             */
            uuid: UUID = UUID.randomUUID(),
            /**
             * Сумма
             */
            sum: AmountOfRubles
    ) : this(uuid, sum, null)
}

/**
 * Постоплата - оплата деньгами, которые будут внесены позже (кредитными деньгами)
 */
class Postpayment internal constructor(
        /**
         * Uuid
         */
        uuid: UUID,
        /**
         * Сумма
         */
        sum: AmountOfRubles,
        /**
         * Использованный способ оплаты или null, если оплата была произведена не на терминале
         */
        paymentMethod: PaymentMethod?
) : Payment(uuid, sum, paymentMethod) {
    constructor(
            /**
             * Uuid
             */
            uuid: UUID = UUID.randomUUID(),
            /**
             * Сумма
             */
            sum: AmountOfRubles
    ) : this(uuid, sum, null)
}

/**
 * Оплата встречным предоставлением
 */
class PaymentByCounterOffer internal constructor(
        /**
         * Uuid
         */
        uuid: UUID,
        /**
         * Сумма
         */
        sum: AmountOfRubles,
        /**
         * Использованный способ оплаты или null, если оплата была произведена не на терминале
         */
        paymentMethod: PaymentMethod?
) : Payment(uuid, sum, paymentMethod) {
    constructor(
            /**
             * Uuid
             */
            uuid: UUID = UUID.randomUUID(),
            /**
             * Сумма
             */
            sum: AmountOfRubles
    ) : this(uuid, sum, null)
}