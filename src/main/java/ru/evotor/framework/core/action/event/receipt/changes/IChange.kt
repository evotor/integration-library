package ru.evotor.framework.core.action.event.receipt.changes

import ru.evotor.IBundlable

interface IChange : IBundlable {
    /**
     * Возвращает тип изменения чека.
     */
    fun getType(): Type

    /**
     * Возможные типы изменения чека.
     */
    enum class Type {
        /**
         * Добавление позиции в чек.
         */
        POSITION_ADD,
        /**
         * Удаление позиции из чека.
         */
        POSITION_REMOVE,
        /**
         * Изменение позиции чека.
         */
        POSITION_EDIT,
        /**
         * Запись дополнительных полей в чек.
         */
        SET_EXTRA,
        SET_POSITION_PRINT_GROUP,
        SET_PAYMENT_PURPOSE_PRINT_GROUP,
        /**
         * Добавление дополнительных полей для печати в чеке.
         */
        SET_PRINT_EXTRA,
        UNKNOWN
    }
}