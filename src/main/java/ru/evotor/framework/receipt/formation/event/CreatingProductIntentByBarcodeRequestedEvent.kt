package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.IBundlable

class CreatingProductIntentByBarcodeRequestedEvent(val barcode: String) : IBundlable  {

    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_BARCODE_EXTRA, barcode)
    }

    companion object {
        const val KEY_BARCODE_EXTRA = "key_barcode_extra"

        fun from(bundle: Bundle?): CreatingProductIntentByBarcodeRequestedEvent? = bundle?.let {
            CreatingProductIntentByBarcodeRequestedEvent(it.getString(KEY_BARCODE_EXTRA) ?: return null)
        }
    }

    data class Result(val iWantEditProducts: Boolean) : IBundlable {

        override fun toBundle(): Bundle = Bundle().apply {
            putBoolean(KEY_EXTRA_RESULT, iWantEditProducts)
        }

        companion object {
            const val KEY_EXTRA_RESULT = "extra_result"

            fun from(bundle: Bundle?): Result? = bundle?.let {
                Result(it.getBoolean(KEY_EXTRA_RESULT, false))
            }
        }
    }

}