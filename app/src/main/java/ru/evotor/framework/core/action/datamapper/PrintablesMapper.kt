package ru.evotor.framework.core.action.datamapper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Parcelable
import ru.evotor.devices.commons.printer.printable.IPrintable
import ru.evotor.devices.commons.printer.printable.PrintableBarcode
import ru.evotor.devices.commons.printer.printable.PrintableImage
import ru.evotor.devices.commons.printer.printable.PrintableText
import ru.evotor.framework.safeValueOf
import java.io.ByteArrayOutputStream


object PrintablesMapper {

    private const val KEY_PRINTABLE_TYPE = "printableType"
    private const val KEY_PRINTABLE_ARRAY = "printablesArray"
    private const val KEY_PRINTABL_TEXT = "printableText"
    private const val KEY_PRINTABL_BARCODE_TYPE = "printableBarcodeType"
    private const val KEY_PRINTABL_IMAGE = "printableImage"


    fun toBundle(printables: Array<IPrintable>): Bundle =
            Bundle().apply {
                val printablesParcelable = arrayOfNulls<Parcelable>(printables.size)
                printables.indices.forEach {
                    printablesParcelable[it] = toBundle(printables.get(it))
                }
                putParcelableArray(KEY_PRINTABLE_ARRAY, printablesParcelable)
            }

    private fun toBundle(printable: IPrintable): Bundle =
            Bundle().let {
                when (printable) {
                    is PrintableText -> {
                        it.putString(KEY_PRINTABLE_TYPE, PrintableType.TEXT.name)
                        it.putString(KEY_PRINTABL_TEXT, printable.text)
                    }
                    is PrintableBarcode -> {
                        it.putString(KEY_PRINTABLE_TYPE, PrintableType.BARCODE.name)
                        it.putString(KEY_PRINTABL_TEXT, printable.barcodeValue)
                        it.putString(KEY_PRINTABL_BARCODE_TYPE, printable.barcodeType.name)
                    }
                    is PrintableImage -> {
                        it.putString(KEY_PRINTABLE_TYPE, PrintableType.IMAGE.name)
                        it.putByteArray(KEY_PRINTABL_IMAGE,
                                ByteArrayOutputStream().let {
                                    printable.bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                                    it.toByteArray()
                                }
                        )
                    }
                }
                it
            }

    fun fromBundle(bundle: Bundle): Array<IPrintable>? =
            arrayListOf<IPrintable>().let {
                list ->
                val bundleParcelables: Array<Parcelable> = bundle.getParcelableArray(KEY_PRINTABLE_ARRAY)
                bundleParcelables.forEach {
                    bundle ->
                    if (bundle is Bundle) {
                        singleFromBundle(bundle)?.let { list.add(it) }
                    }
                }
                list.toTypedArray()
            }

    private fun singleFromBundle(bundle: Bundle): IPrintable? =
            when (safeValueOf<PrintableType>(bundle.getString(KEY_PRINTABLE_TYPE), null)) {
                PrintableType.TEXT -> PrintableText(
                        bundle.getString(KEY_PRINTABL_TEXT)
                )
                PrintableType.BARCODE -> PrintableBarcode(
                        bundle.getString(KEY_PRINTABL_TEXT),
                        safeValueOf<PrintableBarcode.BarcodeType>(bundle.getString(KEY_PRINTABL_BARCODE_TYPE), PrintableBarcode.BarcodeType.CODE39)
                )
                PrintableType.IMAGE ->
                    bundle.getByteArray(KEY_PRINTABL_IMAGE).let {
                        PrintableImage(
                                BitmapFactory.decodeByteArray(it, 0, it.size)
                        )
                    }
                null -> null
            }

    enum class PrintableType {
        TEXT, BARCODE, IMAGE
    }
}