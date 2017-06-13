package ru.evotor.framework.core.action.event.receipt.changes

import ru.evotor.IBundlable

/**
 * Created by a.kuznetsov on 23/05/2017.
 */
interface IChange : IBundlable {
    fun getType(): Type

    enum class Type {
        POSITION_ADD,
        POSITION_REMOVE,
        POSITION_EDIT,
        SET_EXTRA,
        UNKNOWN
    }
}