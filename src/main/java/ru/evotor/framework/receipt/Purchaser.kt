package ru.evotor.framework.receipt

import android.os.Parcel
import android.os.Parcelable

data class Purchaser(
        val name: String,
        val inn: String
) : Parcelable {

    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(inn)
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