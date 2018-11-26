package ru.evotor.framework.receipt

import java.util.*

/**
 * Способ формирования чека
 */
sealed class ReceiptFormationMethod(val uuid: UUID) {
    /**
     * Через приложение "Продажа"
     */
    object Sell : ReceiptFormationMethod(UUID.randomUUID())

    /**
     * Через приложение "Возврат"
     */
    object Payback : ReceiptFormationMethod(UUID.randomUUID())

    /**
     * Через приложерние "Покупка"
     */
    object Buy : ReceiptFormationMethod(UUID.randomUUID())

    /**
     * Через приложение "Возврат покупки"
     */
    object Buyback : ReceiptFormationMethod(UUID.randomUUID())

    /**
     * Через собственное приложение
     */
    class Custom(uuid: UUID) : ReceiptFormationMethod(uuid)
}