package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.IBundlable

class CreateProductByBarcodeEvent(val barcode: String) : IBundlable {

    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_BARCODE_EXTRA, barcode)
    }

    companion object {
        const val KEY_BARCODE_EXTRA = "key_barcode_extra"

        fun from(bundle: Bundle?): CreateProductByBarcodeEvent? = bundle?.let {
            CreateProductByBarcodeEvent(it.getString(KEY_BARCODE_EXTRA)
                    ?: return null)
        }
    }

}

