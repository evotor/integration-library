package ru.evotor.framework.receipt

import android.os.Parcel
import android.os.Parcelable
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
        val name: String,
        /**
         * Точность
         */
        val precision: Int,
        /**
         * Код
         */
        @FiscalRequisite(tag = FiscalTags.MEASURE_CODE)
        val code: Int

) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            parcel.writeString(name)
            parcel.writeInt(precision)
            parcel.writeInt(code)
        }
    }

    override fun describeContents(): Int = 0

    companion object {

        private const val VERSION = 1
        internal const val UNKNOWN_MEASURE_CODE = 255

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
                            code = parcel.readInt()
                    )
                }
            }
            checkNotNull(measure)
            return measure as Measure
        }
    }
}