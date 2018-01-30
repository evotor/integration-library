package ru.evotor.framework.core.action.event.receipt.print_extra

import android.os.Bundle
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.Utils
import ru.evotor.framework.core.action.datamapper.ChangesMapper
import ru.evotor.framework.core.action.event.receipt.changes.receipt.print_extra.SetPrintExtra

class PrintExtraRequiredEventResult(
        val printExtras: List<SetPrintExtra>?
) : IBundlable {

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putParcelableArray(KEY_PRINT_EXTRA,
                if (printExtras != null) {
                    val setPrintGroupsParcelableArray = mutableListOf<Parcelable>()
                    printExtras.map {
                        ChangesMapper.toBundle(it)
                    }.forEach {
                        setPrintGroupsParcelableArray.add(it)
                    }
                    setPrintGroupsParcelableArray.toTypedArray()
                } else {
                    null
                })
        return bundle
    }

    companion object {
        private val KEY_PRINT_EXTRA = "printExtras"

        fun create(bundle: Bundle?): PrintExtraRequiredEventResult? {
            if (bundle == null) {
                return null
            }
            return PrintExtraRequiredEventResult(
                    Utils.filterByClass(
                            ChangesMapper.create(bundle.getParcelableArray(KEY_PRINT_EXTRA)),
                            SetPrintExtra::class.java
                    )
            )
        }
    }
}