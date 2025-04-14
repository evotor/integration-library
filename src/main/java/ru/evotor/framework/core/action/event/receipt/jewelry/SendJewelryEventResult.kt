package ru.evotor.framework.core.action.event.receipt.jewelry

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.devices.commons.printer.printable.IPrintable
import ru.evotor.framework.core.action.datamapper.PrintablesMapper

/**
 * Результат обработки события [SendJewelryEvent].
 */
class SendJewelryEventResult(
    val printableReport: Array<IPrintable>?
) : IBundlable {

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        printableReport?.let {
            bundle.putBundle(KEY_PRINTABLE_REPORT, PrintablesMapper.toBundle(it))
        }
        return bundle
    }

    companion object {
        private const val KEY_PRINTABLE_REPORT = "printableReport"

        fun create(bundle: Bundle?): SendJewelryEventResult? = bundle?.let {
            val printableReport = PrintablesMapper.fromBundle(bundle.getBundle(KEY_PRINTABLE_REPORT))
            return SendJewelryEventResult(
                printableReport
            )
        }
    }
}
