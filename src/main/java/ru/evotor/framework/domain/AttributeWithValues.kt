package ru.evotor.framework.domain

import android.os.Parcel
import android.os.Parcelable

private const val VERSION = 1

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
        // Determine position in parcel for writing data size
        int dataSizePosition = dest.dataPosition();
        // Use integer placeholder for data size
        dest.writeInt(0);
        //Determine position of data start
        int startDataPosition = dest.dataPosition();
        parcel.writeString(uuid)
        parcel.writeString(name)
        parcel.writeTypedList(attributeValues)
        // Calculate data size
        int dataSize = dest.dataPosition() - startDataPosition;
        // Save position at the end of data
        int endOfDataPosition = dest.dataPosition();
        //Set position to start to write data size
        dest.setDataPosition(dataSizePosition);
        dest.writeInt(dataSize);
        // Go back to the end of parcel
        dest.setDataPosition(endOfDataPosition);
    }

    override fun describeContents(): Int = 0

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<AttributeWithValues> = object : Parcelable.Creator<AttributeWithValues> {
            override fun createFromParcel(parcel: Parcel): AttributeWithValues = AttributeWithValues(parcel)

            override fun newArray(size: Int): Array<AttributeWithValues?> = arrayOfNulls(size)
        }
    }
}
