package ru.evotor.framework.receipt

import ru.evotor.framework.calculator.MoneyCalculator
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
     * Скидка на чек. Без учета скидок на позиции
     */
    fun getDiscount(): BigDecimal {
        return printDocuments
                .fold(BigDecimal.ZERO, { acc, printDocument ->
                    MoneyCalculator.add(acc, printDocument.getDiscount())
                })
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
        PAYBACK,
        /**
         * Покупка
         */
        BUY,
        /**
         * Возврат покупки
         */
        BUYBACK
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
            val changes: Map<Payment, BigDecimal>,
            /**
             * Скидка на документ, распределенная на позиции
             * Ключ - uuid позиции
             * Значение - скидка (уже высчитанная из цены)
             *
             * Added on 13.02.2018
             */
            val discounts: Map<String, BigDecimal>?
    ) {

        /**
         * Сумма скидок для текущей группы
         */
        fun getDiscount(): BigDecimal {
            return positions
                    .fold(BigDecimal.ZERO, { acc, position ->
                        MoneyCalculator.add(acc, discounts?.get(position.uuid) ?: BigDecimal.ZERO)
                    })
        }
    }
}