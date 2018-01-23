package ru.evotor.framework.receipt

import java.math.BigDecimal
import java.util.*

/**
 * Чек
 */
data class Receipt
(
        /**
         * Заголовок чека
         */
        val header: Header,
        /**
         * Печатные формы чека
         */
        val printDocuments: List<PrintReceipt>
) {

    /**
     * Список всех позиций чека
     */
    fun getPositions(): List<Position> {
        return printDocuments
                .flatMap { it.positions }
                .toList()
    }

    /**
     * Список всех оплат чека
     */
    fun getPayments(): List<Payment> {
        return printDocuments
                .map { it.payments }
                .flatMap { it.keys }
                .distinct()
    }

    /**
     * Заголовок чека
     */
    data class Header(
            /**
             * Uuid чека
             */
            val uuid: String,
            /**
             * Номер чека. Может быть null для еще незакрытого чека
             */
            val number: String?,
            /**
             * Тип чека
             */
            val type: Type,
            /**
             * Дата регистрации чека.
             */
            val date: Date?,
            /**
             * Email для отправки чека по почте
             */
            var clientEmail: String?,

            /**
             * Phone для отправки чека по смс
             */
            var clientPhone: String?,

            /**
             * Extra
             */
            val extra: String?
    )

    /**
     * Тип чека
     */
    enum class Type {
        /**
         * Продажа
         */
        SELL,
        /**
         * Возврат
         */
        PAYBACK
    }

    /**
     * Печатная форма чека
     */
    data class PrintReceipt(
            /**
             * Печатная группа
             */
            val printGroup: PrintGroup?,
            /**
             * Позиции
             */
            val positions: List<Position>,
            /**
             * Оплаты
             */
            val payments: Map<Payment, BigDecimal>,
            /**
             * Сдача
             */
            val changes: Map<Payment, BigDecimal>
    )
}