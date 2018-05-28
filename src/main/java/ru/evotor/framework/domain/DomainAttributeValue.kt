package ru.evotor.framework.domain

import android.os.Parcel
import android.os.Parcelable

/**
 * Значение атрибута
 */
data class DomainAttributeValue(
        /**
         * Уникальный идентификатор атрибута
         */
        val dictionaryUuid: String? = null,

        /**
         * Имя атрибута (ex. 'Цвет')
         */
        val dictionaryName: String? = null,

        /**
         * Уникальный идентификатор значения атрибута
         */
        val uuid: String? = null,

        /**
         * Имя значения атрибута (ex. 'Черный')
         */
        val name: String? = null

) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dictionaryUuid)
        parcel.writeString(dictionaryName)
        parcel.writeString(uuid)
        parcel.writeString(name)
    }

    override fun describeContents(): Int = 0

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<DomainAttributeValue> = object : Parcelable.Creator<DomainAttributeValue> {
            override fun createFromParcel(parcel: Parcel): DomainAttributeValue =
                    DomainAttributeValue(parcel)

            override fun newArray(size: Int): Array<DomainAttributeValue?> = arrayOfNulls(size)
        }
    }
}
