package ru.evotor.framework.receipt

import ru.evotor.framework.payment.PaymentSystem
import java.math.BigDecimal

/**
 * Оплата
 */
data class Payment(
        /**
         * Uuid
         */
        val uuid: String,
        /**
         * Сумма денег принятых от клиента
         */
        val value: BigDecimal,
        /**
         * Платежная система
         */
        val system: PaymentSystem?,
        /**
         * Идентификатор цели платежа
         */
        val purposeIdentifier: String?,
        /**
         * Идентификатор аккаунта
         */
        val accountId: String?,
        /**
         * Описание аккаунта
         */
        val accountUserDescription: String?


) {
    override fun toString(): String {
        return "Payment(uuid='$uuid', value=$value, system=$system, purposeIdentifier=$purposeIdentifier, accountId=$accountId, accountUserDescription=$accountUserDescription)"
    }
}