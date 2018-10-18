package ru.evotor.framework.inventory.event

import android.os.Bundle

import ru.evotor.IBundlable

abstract class ProductEvent internal constructor(val productUuid: String?) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_PRODUCT_UUID, productUuid)
        return result
    }

    companion object {

        private const val KEY_PRODUCT_UUID = "productUuid"

        internal fun getProductUuid(bundle: Bundle): String? = bundle.getString(KEY_PRODUCT_UUID)

    }

}
