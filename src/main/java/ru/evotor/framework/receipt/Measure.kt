package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags

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

        private const val VERSION = 1
        const val MEASURE_NO_CODE = -1
        private const val KEY_MEASURE_NAME = "measureName"
        private const val KEY_MEASURE_PRECISION = "measurePrecision"
        private const val KEY_MEASURE_CODE = "measureCode"

        val default = Measure(
                name = "шт",
                precision = 0,
                code = 0
        )

        @JvmField
        val CREATOR = object : Parcelable.Creator<Measure> {
            override fun createFromParcel(parcel: Parcel): Measure = create(parcel)

            override fun newArray(size: Int): Array<Measure?> = arrayOfNulls(size)
        }

        private fun create(dest: Parcel): Measure {
            var measure: Measure? = null
            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->
                if (version >= 1) {
                    measure = Measure(
                            name = parcel.readString(),
                            precision = parcel.readInt(),
                            code = parcel.readValue(Int::class.java.classLoader) as? Int
                    )
                }
            }
            checkNotNull(measure)
            return measure as Measure
        }
    }
}
