package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable

data class Purchaser(
        val name: String,
        val documentNumber: String,
        val type: PurchaserType?
) : Parcelable, IBundlable {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_NAME, name)
            putString(KEY_DOCUMENT_NUMBER, documentNumber)
            putInt(KEY_TYPE, type?.ordinal ?: -1)
        }
    }

    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            if (parcel.readInt() == 0) null else PurchaserType.values()[parcel.readInt() % PurchaserType.values().size])

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(documentNumber)
        parcel.writeInt(if (type == null) 0 else 1)
        type?.let { parcel.writeInt(it.ordinal) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<Purchaser> {
            override fun createFromParcel(parcel: Parcel) = Purchaser(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Purchaser>(size)
        }

        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_DOCUMENT_NUMBER = "KEY_DOCUMENT_NUMBER"
        private const val KEY_TYPE = "KEY_TYPE"

        fun fromBundle(bundle: Bundle?): Purchaser? {
            return bundle?.let {
                Purchaser(it.getString(KEY_NAME), it.getString(KEY_DOCUMENT_NUMBER),
                        it.getInt(KEY_TYPE).let {
                            if (it == -1) {
                                null
                            } else {
                                PurchaserType.values()[it % PurchaserType.values().size]
                            }
                        }
                )
            }
        }
    }
}

enum class PurchaserType {
    INDIVIDUAL, // физлицо
    SOLE_PROPRIETOR, // ИП
    ENTITY // юрлицо
}