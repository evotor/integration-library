package ru.evotor.framework.domain

import android.os.Parcel
import android.os.Parcelable

/**
 * Атрибут
 */
data class DomainAttribute(
        /**
         * Уникальный идентификатор атрибута
         */
        val uuid: String? = null,

        /**
         * Имя атрибута (ex. 'Цвет')
         */
        val name: String? = null,

        /**
         * Список значений атрибутов
         */
        var attributeValuesForAdapter: List<DomainAttributeValue>? = null

) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(DomainAttributeValue.CREATOR))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uuid)
        parcel.writeString(name)
        parcel.writeTypedList(attributeValuesForAdapter)
    }

    override fun describeContents(): Int = 0

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<DomainAttribute> = object : Parcelable.Creator<DomainAttribute> {
            override fun createFromParcel(parcel: Parcel): DomainAttribute = DomainAttribute(parcel)

            override fun newArray(size: Int): Array<DomainAttribute?> = arrayOfNulls(size)
        }
    }
}
