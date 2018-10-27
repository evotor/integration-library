package ru.evotor.framework.inventory

import android.os.Parcel
import android.os.Parcelable

private const val VERSION = 1

/**
 * Атрибут
 */
data class AttributeWithValues(
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
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(VERSION)
        // Determine position in parcel for writing data size
        val dataSizePosition = parcel.dataPosition()
        // Use integer placeholder for data size
        parcel.writeInt(0)
        //Determine position of data start
        val startDataPosition = parcel.dataPosition()
        parcel.writeString(uuid)
        parcel.writeString(name)
        parcel.writeTypedList(attributeValues)
        // Calculate data size
        val dataSize = parcel.dataPosition() - startDataPosition
        // Save position at the end of data
        val endOfDataPosition = parcel.dataPosition()
        //Set position to start to write data size
        parcel.setDataPosition(dataSizePosition)
        parcel.writeInt(dataSize)
        // Go back to the end of parcel
        parcel.setDataPosition(endOfDataPosition)
    }

    override fun describeContents(): Int = 0

    companion object {
            
        @JvmStatic
        fun readFromParcel(parcel: Parcel) : AttributeWithValues {
            val version = parcel.readInt()
            val dataSize = parcel.readInt()
            val dataStartPosition = parcel.dataPosition()
            val uuid = parcel.readString()
            val name = parcel.readString()
            val attributeValues = parcel.createTypedArrayList(AttributeValue.CREATOR)
            parcel.setDataPosition(dataStartPosition + dataSize)
            return AttributeWithValues(uuid, name, attributeValues)
        }

        @JvmField
        val CREATOR: Parcelable.Creator<AttributeWithValues> = object : Parcelable.Creator<AttributeWithValues> {
            override fun createFromParcel(parcel: Parcel): AttributeWithValues = readFromParcel(parcel)

            override fun newArray(size: Int): Array<AttributeWithValues?> = arrayOfNulls(size)
        }
    }
}
