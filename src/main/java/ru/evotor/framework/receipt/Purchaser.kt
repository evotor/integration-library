package ru.evotor.framework.receipt

import android.os.Parcel
import android.os.Parcelable

data class Purchaser(
        val name: String,
        val documentNumber: String,
        val type: PurchaserType?
) : Parcelable {

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
    }
}

enum class PurchaserType {
    INDIVIDUAL, // физлицо
    SOLE_PROPRIETOR, // ИП
    ENTITY // юрлицо
}