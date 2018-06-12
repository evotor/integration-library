package ru.evotor.framework.domain

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty

const val VERSION = 1

/**
 * Значение атрибута
 */
data class AttributeValue(
        /**
         * Версия Parcelable
         */
        @JsonProperty("version")
        val version : Int = VERSION,
        /**
         * Уникальный идентификатор атрибута
         */
        @JsonProperty("dictionaryUuid")
        val dictionaryUuid: String,

        /**
         * Имя атрибута (ex. 'Цвет')
         */
        @JsonProperty("dictionaryName")
        val dictionaryName: String,

        /**
         * Уникальный идентификатор значения атрибута
         */
        @JsonProperty("uuid")
        val uuid: String,

        /**
         * Имя значения атрибута (ex. 'Черный')
         */
        @JsonProperty("name")
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
