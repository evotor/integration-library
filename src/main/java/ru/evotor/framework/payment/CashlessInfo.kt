package ru.evotor.framework.payment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.UuidValidationUtils
import ru.evotor.framework.ParcelableUtils
import ru.evotor.framework.Utils

data class CashlessInfo(
    val uuid: String,
    val description: String,
    val method: Method
) : IBundlable, Parcelable {

    init {
        UuidValidationUtils.checkUuid(uuid)
    }

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(KEY_UUID, uuid)
        bundle.putString(KEY_DESCRIPTION, description)
        bundle.putInt(KEY_METHOD_ORDINAL, method.ordinal)
        return bundle
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        ParcelableUtils.writeExpand(parcel, VERSION) { parcel ->
            parcel.writeString(uuid)
            parcel.writeString(description)
            parcel.writeString(method.name)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    // Новые значения добавлять только в конец
    enum class Method {
        UNKNOWN,
        QR,
        BIOMETRY,
        CARD,
        INTERNET_ACQUIRING,
        BANK_TRANSFER
    }

    companion object {

        private const val VERSION = 1

        private const val KEY_UUID = "uuid"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_METHOD_ORDINAL = "methodOrdinal"

        @JvmStatic
        fun fromBundle(bundle: Bundle?): CashlessInfo? {
            bundle ?: return null

            val methodOrdinal = bundle.getInt(KEY_METHOD_ORDINAL, -1)
            if (methodOrdinal < 0) {
                return null
            }
            val method = if (methodOrdinal >= Method.values().size) {
                Method.UNKNOWN
            }
                else {
                Method.values()[methodOrdinal]
            }

            return CashlessInfo(
                uuid = bundle.getString(KEY_UUID) ?: return null,
                description = bundle.getString(KEY_DESCRIPTION) ?: return null,
                method = method
            )
        }

        @JvmField
        val CREATOR = object : Parcelable.Creator<CashlessInfo> {
            override fun createFromParcel(parcel: Parcel): CashlessInfo {
                return create(parcel)
            }

            override fun newArray(size: Int): Array<CashlessInfo?> {
                return arrayOfNulls(size)
            }
        }

        private fun create(dest: Parcel): CashlessInfo {
            var cashlessInfo: CashlessInfo? = null
            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->
                // version 1
                val uuid = parcel.readString() ?: throw IllegalStateException("uuid should be null")
                val description = parcel.readString() ?: throw IllegalStateException("description should be null")
                val method = Utils.safeValueOf(Method::class.java, parcel.readString(), Method.UNKNOWN)

                cashlessInfo = CashlessInfo(
                    uuid = uuid,
                    description = description,
                    method = method
                )
            }
            checkNotNull(cashlessInfo)
            return cashlessInfo as CashlessInfo

        }
    }
}
