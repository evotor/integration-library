package ru.evotor.framework.core.action.event.receipt.changes

import ru.evotor.IBundlable

interface IChange : IBundlable {
    fun getType(): Type

    enum class Type {
        POSITION_ADD,
        POSITION_REMOVE,
        POSITION_EDIT,
        SET_EXTRA,
        SET_POSITION_PRINT_GROUP,
        SET_PAYMENT_PURPOSE_PRINT_GROUP,
        SET_PRINT_EXTRA,
        UNKNOWN
    }
}