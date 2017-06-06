package ru.evotor.framework.core.action.event.receipt.changes

import android.os.Bundle

/**
 * Created by a.kuznetsov on 23/05/2017.
 */
interface IChange {
    fun toBundle(): Bundle
    fun getType(): Type

    enum class Type {
        POSITION_ADD,
        POSITION_REMOVE,
        POSITION_EDIT,
        SET_EXTRA,
        UNKNOWN
    }
}