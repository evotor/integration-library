package ru.evotor.framework.receipt.position

import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.receipt.position.Mark.RawMark
import ru.evotor.framework.receipt.position.Mark.TagProductCode

/**
 * Класс марки для маркированных товаров и алкоголя.
 *
 * Для ФФД <= 1.1 марка записывается в реквизит "код товара" (тег 1162).
 *
 * Может иметь полное представление, где заполняется только [RawMark]
 * ИЛИ
 * частичное представление по конкретным тэгам, к примеру [TagProductCode]
 */
sealed class Mark : Parcelable {

    protected abstract fun writeFieldsToParcel(dest: Parcel, flags: Int)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // Determine position in parcel for writing data size
        val dataSizePosition = parcel.dataPosition()
        // Use integer placeholder for data size
        parcel.writeInt(0)
        //Determine position of data start
        val startDataPosition = parcel.dataPosition()

        writeFieldsToParcel(parcel, flags)

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

    /**
     * Значение при полной марке
     */
    class RawMark(
            @FiscalRequisite(tag = FiscalTags.PRODUCT_CODE)
            val value: String
    ) : Mark() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {
            dest.writeString(value)
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is RawMark) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): RawMark? {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                var value: String? = null
                when (version) {
                    1 -> {
                        value = parcel.readString()
                    }
                }
                parcel.setDataPosition(dataStartPosition + dataSize)

                if (value.isNullOrEmpty()) return null

                return RawMark(value)
            }

            @JvmField
            val CREATOR: Parcelable.Creator<RawMark?> = object : Parcelable.Creator<RawMark?> {
                override fun createFromParcel(parcel: Parcel): RawMark? = readFromParcel(parcel)

                override fun newArray(size: Int): Array<RawMark?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Тег для кода товара
     * Может содержать в себе любые форматы марки
     */
    class TagProductCode(
            @FiscalRequisite(tag = FiscalTags.PRODUCT_CODE)
            val value: String
    ) : Mark() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {
            dest.writeString(value)
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is TagProductCode) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): TagProductCode? {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                var value: String? = null
                when (version) {
                    1 -> {
                        value = parcel.readString()
                    }
                }
                parcel.setDataPosition(dataStartPosition + dataSize)

                if (value.isNullOrEmpty()) return null

                return TagProductCode(value)
            }

            @JvmField
            val CREATOR: Parcelable.Creator<TagProductCode?> = object : Parcelable.Creator<TagProductCode?> {
                override fun createFromParcel(parcel: Parcel): TagProductCode? = readFromParcel(parcel)

                override fun newArray(size: Int): Array<TagProductCode?> = arrayOfNulls(size)
            }
        }
    }

}