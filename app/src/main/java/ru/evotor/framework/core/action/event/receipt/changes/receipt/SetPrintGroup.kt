package ru.evotor.framework.core.action.event.receipt.changes.position

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.PositionMapper
import ru.evotor.framework.core.action.datamapper.PrintGroupMapper
import ru.evotor.framework.core.action.event.receipt.changes.IChange
import ru.evotor.framework.receipt.Position
import ru.evotor.framework.receipt.PrintGroup

data class SetPrintGroup(val position: Position, val printGroup: PrintGroup?) : IChange {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putBundle(
                    KEY_POSITION,
                    PositionMapper.toBundle(position)
            )
            putBundle(
                    KEY_PRINT_GROUP,
                    PrintGroupMapper.toBundle(printGroup)
            )
        }
    }

    override fun getType(): IChange.Type {
        return IChange.Type.SET_EXTRA
    }

    companion object {
        const val KEY_POSITION = "position"
        const val KEY_PRINT_GROUP = "printGroup"

        @JvmStatic
        fun from(bundle: Bundle?): SetPrintGroup? {
            bundle ?: return null

            val position = PositionMapper.from(bundle.getBundle(KEY_POSITION))
            val printGroup = PrintGroupMapper.from(bundle.getBundle(KEY_PRINT_GROUP))

            if (position == null || printGroup == null) {
                return null
            }

            return SetPrintGroup(position, printGroup)
        }
    }
}