package ru.evotor.framework.payment

import android.os.Bundle
import ru.evotor.IBundlable

data class CashlessInfo(
    val uuid: String,
    val description: String,
    val method: Method
) : IBundlable {

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(KEY_UUID, uuid)
        bundle.putString(KEY_DESCRIPTION, description)
        bundle.putInt(KEY_METHOD_ORDINAL, method.ordinal)
        return bundle
    }

    enum class Method {
        QR,
        BIOMETRY,
        CARD,
        INTERNET_ACQUIRING,
        BANK_TRANSFER,
        UNKNOWN
    }

    companion object {

        private const val KEY_UUID = "uuid"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_METHOD_ORDINAL = "methodOrdinal"

        @JvmStatic
        fun fromBundle(bundle: Bundle?): CashlessInfo? {
            bundle ?: return null

            val methodOrdinal = bundle.getInt(KEY_METHOD_ORDINAL, -1)
            val method = if (methodOrdinal == -1 || methodOrdinal >= Method.values().size) {
                Method.UNKNOWN
            } else {
                Method.values()[methodOrdinal]
            }

            return CashlessInfo(
                uuid = bundle.getString(KEY_UUID) ?: throw IllegalArgumentException("uuid should not be null"),
                description = bundle.getString(KEY_DESCRIPTION) ?: throw IllegalArgumentException("description should not be null"),
                method = method
            )
        }
    }
}
