package ru.evotor.framework.core.action.event.receipt.changes.position

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.PositionMapper
import ru.evotor.framework.core.action.event.receipt.changes.IChange
import ru.evotor.framework.receipt.Position

/**
 * Добавляет позицию в чек.
 * @param position – позиция.
 */
data class PositionAdd(val position: Position) : IPositionChange {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putBundle(
                    PositionMapper.KEY_POSITION,
                    PositionMapper.toBundle(position)
            )
        }
    }

    override fun getPositionUuid(): String? {
        return position.uuid
    }

    override fun getType(): IChange.Type {
        return IChange.Type.POSITION_ADD
    }

    companion object {
        @JvmStatic
        fun from(bundle: Bundle?): PositionAdd? {
            bundle ?: return null

            return PositionAdd(
                    PositionMapper.from(
                            bundle.getBundle(PositionMapper.KEY_POSITION)
                    ) ?: return null
            )
        }
    }
}