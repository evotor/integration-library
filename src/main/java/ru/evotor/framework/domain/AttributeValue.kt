package ru.evotor.framework.domain

import android.os.Parcel
import android.os.Parcelable

const val VERSION = 1

/**
 * Значение атрибута
 */
data class AttributeValue(
        /**
         * Версия Parcelable
         */
        val version : Int = VERSION,
        /**
         * Уникальный идентификатор атрибута
         */
        val dictionaryUuid: String,

        /**
         * Имя атрибута (ex. 'Цвет')
         */
        val dictionaryName: String,

        /**
         * Уникальный идентификатор значения атрибута
         */
        val uuid: String,

        /**
         * Имя значения атрибута (ex. 'Черный')
         */
        val name: String

) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(version)
        parcel.writeString(dictionaryUuid)
        parcel.writeString(dictionaryName)
        parcel.writeString(uuid)
        parcel.writeString(name)
    }

    override fun describeContents(): Int = 0

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<AttributeValue> = object : Parcelable.Creator<AttributeValue> {
            override fun createFromParcel(parcel: Parcel): AttributeValue =
                    AttributeValue(parcel)

            override fun newArray(size: Int): Array<AttributeValue?> = arrayOfNulls(size)
        }
    }
}
