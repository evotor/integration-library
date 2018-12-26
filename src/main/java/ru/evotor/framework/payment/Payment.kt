package ru.evotor.framework.payment

import java.math.BigDecimal

data class Payment(
        /**
         * Uuid
         */
        val uuid: String,
        /**
         * Сумма
         */
        val sum: BigDecimal,
        /**
         * Использованное платёжное средство
         */
        val paymentMean: PaymentMean,
        /**
         * Использованный способ оплаты
         */
        val paymentMethod: PaymentMethod
)