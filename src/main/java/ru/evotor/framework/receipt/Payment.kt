package ru.evotor.framework.receipt

import ru.evotor.framework.component.PaymentPerformer
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
         * Интеграционное приложение, осуществляющее оплату определенной платежной системой
         */
        val paymentPerformer: PaymentPerformer,
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
        val accountUserDescription: String?,
        /**
         * Идентификатор платежа в платежной системе (RRN для оплаты картой)
         */
        val identifier: String?
) {

    constructor(
            uuid: String,
            value: BigDecimal,
            paymentPerformer: PaymentPerformer,
            purposeIdentifier: String?,
            accountId: String?,
            accountUserDescription: String?
    ) : this(uuid, value, paymentPerformer, purposeIdentifier, accountId, accountUserDescription, null)

    override fun toString(): String {
        return "Payment(uuid='$uuid', value=$value, paymentPerformer=$paymentPerformer, purposeIdentifier=$purposeIdentifier, accountId=$accountId, accountUserDescription=$accountUserDescription, identifier=$identifier)"
    }
}