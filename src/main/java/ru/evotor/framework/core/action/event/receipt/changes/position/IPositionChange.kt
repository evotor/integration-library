package ru.evotor.framework.core.action.event.receipt.changes.position

import ru.evotor.framework.core.action.event.receipt.changes.IChange

/**
 *
 */
interface IPositionChange : IChange {
    /**
     * Возвращает идентификатор позиции.
     */
    fun getPositionUuid(): String?
}