package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable

data class MedicineAttribute(
        /**
         * Идентификатор субъекта обращения
         * Применяется к каждой печатной группе в составе тега 1084
         * Значение дополнительного реквизита пользователя (тег 1086)
         */
        val subjectId: String

) : Parcelable, IBundlable {

    private constructor(parcel: Parcel) : this(
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flag: Int) {
        parcel.writeString(subjectId)
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
            override fun createFromParcel(parcel: Parcel): MedicineAttribute = MedicineAttribute(parcel)
            override fun newArray(size: Int): Array<MedicineAttribute?> = arrayOfNulls(size)
        }

        private const val KEY_SUBJECT_ID = "SUBJECT_ID"

        fun fromBundle(bundle: Bundle?): MedicineAttribute? {
            return bundle?.let {
                MedicineAttribute(
                        it.getString(KEY_SUBJECT_ID)
                )
            }
        }
    }
}