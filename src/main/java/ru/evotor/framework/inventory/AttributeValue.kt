package ru.evotor.framework.inventory

import android.os.Parcel
import android.os.Parcelable

private const val VERSION = 1

/**
 * Значение атрибута
 */
data class AttributeValue(
        /**
         * Уникальный идентификатор атрибута
         */
        val attributeUuid: String,

        /**
         * Имя атрибута (ex. 'Цвет')
         */
        val attributeName: String,

        /**
         * Уникальный идентификатор значения атрибута
         */
        val uuid: String,

        /**
         * Имя значения атрибута (ex. 'Черный')
         */
        val name: String

) : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(VERSION)
        // Determine position in parcel for writing data size
        val dataSizePosition = parcel.dataPosition()
        // Use integer placeholder for data size
        parcel.writeInt(0)
        //Determine position of data start
        val startDataPosition = parcel.dataPosition()

        parcel.writeString(attributeUuid)
        parcel.writeString(attributeName)
        parcel.writeString(uuid)
        parcel.writeString(name)
        // Calculate data size
        val dataSize = parcel.dataPosition() - startDataPosition
        // Save position at the end of data
        val endOfDataPosition = parcel.dataPosition()
        //Set position to start to write additional data size
        parcel.setDataPosition(dataSizePosition)
        parcel.writeInt(dataSize)
        // Go back to the end of parcel
        parcel.setDataPosition(endOfDataPosition)
    }

    override fun describeContents(): Int = 0
    companion object {
        @JvmStatic
        fun readFromParcel(parcel : Parcel) : AttributeValue {
            val version = parcel.readInt()
            val dataSize = parcel.readInt()
            val dataStartPosition = parcel.dataPosition()
            val attributeUuid = parcel.readString()
            val attributeName = parcel.readString()
            val uuid = parcel.readString()
            val name = parcel.readString()
            parcel.setDataPosition(dataStartPosition + dataSize)
            return AttributeValue(attributeUuid, attributeName, uuid, name)
        }

        @JvmField
        val CREATOR: Parcelable.Creator<AttributeValue> = object : Parcelable.Creator<AttributeValue> {
            override fun createFromParcel(parcel: Parcel): AttributeValue =
                    readFromParcel(parcel)

            override fun newArray(size: Int): Array<AttributeValue?> = arrayOfNulls(size)
        }
    }
}
