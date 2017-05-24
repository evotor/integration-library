package ru.evotor.framework.core.action.event.receipt.changes.position

import ru.evotor.framework.core.action.event.receipt.changes.IChange

/**
 * Created by a.kuznetsov on 23/05/2017.
 */
interface IPositionChange : IChange {
    fun getPositionUuid(): String?
}