package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable

data class MedicineAttribute(

        /**
         * Наименование льготы.
         * Применяется к каждой печатной группе в составе тега 1084
         * Наименование дополнительного реквизита пользователя (тег 1085)
         */
        val exemptionName: String,

        /**
         * Значение льготы
         * Применяется к каждой печатной группе в составе тега 1084
         * Значение дополнительного реквизита пользователя (тег 1086)
         */
        val exemptionValue: String,

        /**
         * Доля в упаковке
         * Применяется к каждой позиции с типом "Маркируемые лекарства" ( MEDICINE_MARKED )
         * Дополнительный реквизит предмета расчета (тег 1191)
         */
        val proportionValue: String

) : Parcelable, IBundlable {

    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flag: Int) {
        parcel.writeString(exemptionName)
        parcel.writeString(exemptionValue)
        parcel.writeString(proportionValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_EXEMPTION_NAME, exemptionName)
            putString(KEY_EXEMPTION_VALUE, exemptionValue)
            putString(KEY_PROPORTION_VALUE, proportionValue)
        }
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<MedicineAttribute> {
            override fun createFromParcel(parcel: Parcel): MedicineAttribute = MedicineAttribute(parcel)
            override fun newArray(size: Int): Array<MedicineAttribute?> = arrayOfNulls(size)
        }

        private const val KEY_EXEMPTION_NAME = "EXEMPTION_NAME"
        private const val KEY_EXEMPTION_VALUE = "EXEMPTION_VALUE"
        private const val KEY_PROPORTION_VALUE = "PROPORTION_VALUE"

        fun fromBundle(bundle: Bundle?): MedicineAttribute? {
            return bundle?.let {
                MedicineAttribute(
                        it.getString(KEY_EXEMPTION_NAME),
                        it.getString(KEY_EXEMPTION_VALUE),
                        it.getString(KEY_PROPORTION_VALUE) )
            }
        }
    }


}