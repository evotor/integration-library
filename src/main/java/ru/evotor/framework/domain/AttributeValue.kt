package ru.evotor.framework.domain

import android.os.Parcel
import android.os.Parcelable

private const val VERSION = 1

/**
 * Значение атрибута
 */
data class AttributeValue(
        /**
         * Версия Parcelable
         */
        val version: Int = VERSION,

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
        parcel.writeInt(version)
        // Determine position in parcel for writing data size
        int dataSizePosition = dest.dataPosition();
        // Use integer placeholder for data size
        dest.writeInt(0);
        //Determine position of data start
        int startDataPosition = dest.dataPosition();

        parcel.writeString(attributeUuid)
        parcel.writeString(attributeName)
        parcel.writeString(uuid)
        parcel.writeString(name)
        // Calculate data size
        int dataSize = dest.dataPosition() - startDataPosition;
        // Save position at the end of data
        int endOfDataPosition = dest.dataPosition();
        //Set position to start to write additional data size
        dest.setDataPosition(dataSizePosition);
        dest.writeInt(dataSize);
        // Go back to the end of parcel
        dest.setDataPosition(endOfDataPosition);
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmStatic
        fun readFromParcel(parcel : Parcel) : AttributeValue {
            val version = parcel.readInt()
            val dataSize = parcel.readInt()
            val attributeUuid = parcel.readString()
            val attributeName = parcel.readString()
            val uuid = parcel.readString()
            val name = parcel.readString()
            return AttributeValue(version, attributeUuid, attributeName, uuid, name);
        }

        @JvmField
        val CREATOR: Parcelable.Creator<AttributeValue> = object : Parcelable.Creator<AttributeValue> {
            override fun createFromParcel(parcel: Parcel): AttributeValue =
                    AttributeValue.readFromParcel(parcel)

            override fun newArray(size: Int): Array<AttributeValue?> = arrayOfNulls(size)
        }
    }
}
