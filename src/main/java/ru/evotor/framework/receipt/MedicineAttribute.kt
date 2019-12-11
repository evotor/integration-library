package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils

data class MedicineAttribute(
        /**
         * Идентификатор субъекта обращения
         * Применяется к каждой печатной группе в составе тега 1084
         * Значение дополнительного реквизита пользователя (тег 1086)
         */
        val subjectId: String

) : Parcelable, IBundlable {

    override fun writeToParcel(dest: Parcel, flag: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            /* version = 1*/
            parcel.writeString(subjectId)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_SUBJECT_ID, subjectId)
        }
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<MedicineAttribute> {
            override fun createFromParcel(parcel: Parcel): MedicineAttribute = create(parcel)
            override fun newArray(size: Int): Array<MedicineAttribute?> = arrayOfNulls(size)
        }

        /**
         * Текущая версия объекта MedicineAttribute.
         */
        private const val VERSION = 1

        private const val KEY_SUBJECT_ID = "SUBJECT_ID"

        fun fromBundle(bundle: Bundle?): MedicineAttribute? {
            return bundle?.let {
                MedicineAttribute(
                        it.getString(KEY_SUBJECT_ID)
                )
            }
        }

        private fun create(parcel: Parcel): MedicineAttribute {
            var subjectId: String? = null
            ParcelableUtils.readExpand(parcel, VERSION) { parcel, version ->
                if (version >= 1) {
                    subjectId = parcel.readString()
                }
            }
            subjectId.let {
                checkNotNull(it)
                return MedicineAttribute(it)
            }
        }
    }
}