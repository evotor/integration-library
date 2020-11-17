package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils
import java.util.*

data class MedicineAdditionalDetails(
        /**
         * Номер документа, не более 200 символов;
         * Составная часть дополнительного реквизита пользователя (тег 1086)
         */
        val documentNumber: String,
        /**
         * Дата документа в формате ГГММДД;
         * Составная часть дополнительного реквизита пользователя (тег 1086)
         */
        val documentDate: Date,
        /**
         * номер серии льготного рецепта;
         * Составная часть дополнительного реквизита пользователя (тег 1086)
         */
        val serialNumber: String

) : Parcelable, IBundlable {

    override fun writeToParcel(dest: Parcel, flag: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            parcel.writeString(documentNumber)
            parcel.writeLong(documentDate.time)
            parcel.writeString(serialNumber)
        }
    }

    override fun describeContents(): Int = 0

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_DOCUMENT_NUMBER, documentNumber)
            putLong(KEY_DOCUMENT_DATE, documentDate.time)
            putString(KEY_SERIAL_NUMBER, serialNumber)
        }
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<MedicineAdditionalDetails> {
            override fun createFromParcel(parcel: Parcel): MedicineAdditionalDetails = create(parcel)
            override fun newArray(size: Int): Array<MedicineAdditionalDetails?> = arrayOfNulls(size)
        }

        /**
         * Текущая версия объекта MedicineAttribute.
         */
        private const val VERSION = 1
        private const val KEY_DOCUMENT_NUMBER = "DOCUMENT_NUMBER"
        private const val KEY_DOCUMENT_DATE = "DOCUMENT_DATE"
        private const val KEY_SERIAL_NUMBER = "SERIAL_NUMBER"

        fun fromBundle(bundle: Bundle?): MedicineAdditionalDetails? {
            return bundle?.let {
                MedicineAdditionalDetails(
                        documentNumber = it.getString(KEY_DOCUMENT_NUMBER),
                        documentDate = Date(it.getLong(KEY_DOCUMENT_DATE)),
                        serialNumber = it.getString(KEY_SERIAL_NUMBER)
                )
            }
        }

        private fun create(dest: Parcel): MedicineAdditionalDetails {
            var medicineAdditionalDetails: MedicineAdditionalDetails? = null

            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->
                if (version >= 1) {
                    medicineAdditionalDetails = MedicineAdditionalDetails(
                            documentNumber = parcel.readString(),
                            documentDate = Date(parcel.readLong()),
                            serialNumber = parcel.readString()
                    )
                }
            }
            checkNotNull(medicineAdditionalDetails)
            return medicineAdditionalDetails as MedicineAdditionalDetails

        }
    }
}