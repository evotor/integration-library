package ru.evotor.framework.core.action.event.receipt.changes.receipt.print_extra

import android.os.Bundle
import ru.evotor.devices.commons.printer.printable.IPrintable
import ru.evotor.framework.core.action.datamapper.PrintExtraPlaceMapper
import ru.evotor.framework.core.action.datamapper.PrintablesMapper
import ru.evotor.framework.core.action.event.receipt.changes.IChange
import ru.evotor.framework.receipt.print_extras.PrintExtraPlace

class SetPrintExtra(
        val printPlace: PrintExtraPlace,
        val printables: Array<IPrintable>
) : IChange {

    override fun getType() = IChange.Type.SET_PRINT_EXTRA

    override fun toBundle(): Bundle =
            Bundle().apply {
                putBundle(KEY_PRINT_PLACE, PrintExtraPlaceMapper.toBundle(printPlace))
                putBundle(KEY_PRINTABLES, PrintablesMapper.toBundle(printables))
            }

    companion object {

        const val KEY_PRINT_PLACE = "printPlace"
        const val KEY_PRINTABLES = "printables"

        @JvmStatic
        fun from(bundle: Bundle?): SetPrintExtra? {
            bundle ?: return null

            val printPlace = PrintExtraPlaceMapper.fromBundle(bundle.getBundle(KEY_PRINT_PLACE))
            val printables = PrintablesMapper.fromBundle(bundle.getBundle(KEY_PRINTABLES))

            if (printPlace == null || printables == null) {
                return null
            }

            return SetPrintExtra(printPlace, printables)
        }
    }

}
