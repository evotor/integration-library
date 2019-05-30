package ru.evotor.framework.core.action.event.receipt.changes.position

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.changes.IChange

/**
 * Удаляет позицию из чека.
 * @param positionUuid – идентификатор позиции, которую требуется удалить.
 */
data class PositionRemove(
        private val positionUuid: String
) : IPositionChange {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(
                    KEY_POSITION_UUID,
                    positionUuid
            )
        }
    }

    override fun getPositionUuid(): String? {
        return positionUuid
    }

    override fun getType(): IChange.Type {
        return IChange.Type.POSITION_REMOVE
    }

    companion object {
        const val KEY_POSITION_UUID = "positionUuid"

        @JvmStatic
        fun from(bundle: Bundle?): PositionRemove? {
            bundle ?: return null

            return PositionRemove(
                    bundle.getString(KEY_POSITION_UUID) ?: return null
            )
        }
    }
}