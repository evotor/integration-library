package ru.evotor.framework.receipt

import android.database.Cursor
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.optInt

/**
 * Единица измерения.
 */
data class Measure(

        /**
         * Наименование
         */
        var name: String,
        /**
         * Точность
         */
        var precision: Int,
        /**
         * Код
         */
        @FiscalRequisite(tag = FiscalTags.MEASURE_CODE)
        var code: Int?

) : Parcelable, IBundlable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(precision)
        parcel.writeValue(code)
    }

    override fun describeContents(): Int = 0

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_MEASURE_NAME, name)
            putInt(KEY_MEASURE_PRECISION, precision)
            putInt(KEY_MEASURE_CODE, code ?: MEASURE_NO_CODE)
        }
    }

    companion object {

        fun readFromCursor(cursor: Cursor): Measure? {
            return cursor.let {
                Measure(
                        it.getString(cursor.getColumnIndex(PositionTable.COLUMN_MEASURE_NAME)),
                        it.getInt(cursor.getColumnIndex(PositionTable.COLUMN_MEASURE_PRECISION)),
                        it.optInt(cursor.getColumnIndex(PositionTable.COLUMN_MEASURE_CODE))
                )
            }
        }

        const val MEASURE_NO_CODE = -1
        private const val KEY_MEASURE_NAME = "measureName"
        private const val KEY_MEASURE_PRECISION = "measurePrecision"
        private const val KEY_MEASURE_CODE = "measureCode"

        val default = Measure(
                name = "шт",
                precision = 0,
                code = 0
        )
        val CREATOR = object : Parcelable.Creator<Measure> {
            override fun createFromParcel(parcel: Parcel): Measure {
                return Measure(parcel)
            }

            override fun newArray(size: Int): Array<Measure?> {
                return arrayOfNulls(size)
            }
        }
    }
}
