package ru.evotor.framework.domain

import android.os.Parcel
import android.os.Parcelable

const val VERSION = 1
/**
 * Атрибут
 */
data class AttributeWithValues(
        /**
         * Версия Parcelable
         */
        val version: Int = VERSION,
        /**
         * Уникальный идентификатор атрибута
         */
        val uuid: String,

        /**
         * Имя атрибута (ex. 'Цвет')
         */
        val name: String,

        /**
         * Список значений атрибутов
         */
        val attributeValues: List<AttributeValue>

) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(AttributeValue.CREATOR))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(version)
        parcel.writeString(uuid)
        parcel.writeString(name)
        parcel.writeTypedList(attributeValues)
    }

    override fun describeContents(): Int = 0

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Attribute> = object : Parcelable.Creator<Attribute> {
            override fun createFromParcel(parcel: Parcel): Attribute = Attribute(parcel)

            override fun newArray(size: Int): Array<Attribute?> = arrayOfNulls(size)
        }
    }
}
