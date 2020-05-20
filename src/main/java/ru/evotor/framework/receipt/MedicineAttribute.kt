package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils
import ru.evotor.framework.Utils
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.receipt.position.PreferentialMedicine.PreferentialMedicineType
import java.util.*

/**
 * Дополнительные реквизиты пользователя, которые необходимы для формирования структурного тега 1084 (состоит из тегов 1085 и 1086)
 * Применяется к каждой печатной группе
 */
data class MedicineAttribute(
        /**
         * Идентификатор субъекта обращения
         * Значение дополнительного реквизита пользователя (тег 1086)
         */
        val subjectId: String,
        /**
         * Тип льготы рецепта
         * При наличии рецепта обязателен к заполнению
         * Необходим для формирования тега 1085
         */
        val preferentialMedicineType: PreferentialMedicineType? = null,
        /**
         * Номер документа, не более 200 символов;
         * Составная часть дополнительного реквизита пользователя (тег 1086)
         */
        val documentNumber: String? = null,
        /**
         * Дата документа в формате ГГММДД;
         * Составная часть дополнительного реквизита пользователя (тег 1086)
         */
        val documentDate: Date? = null,
        /**
         * номер серии льготного рецепта;
         * Составная часть дополнительного реквизита пользователя (тег 1086)
         */
        val serialNumber: String? = null

) : Parcelable, IBundlable {

    override fun writeToParcel(dest: Parcel, flag: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            parcel.writeString(subjectId)
            parcel.writeString(preferentialMedicineType?.name)
            parcel.writeString(documentNumber)
            parcel.writeLong(documentDate?.time ?: 0)
            parcel.writeString(serialNumber)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_SUBJECT_ID, subjectId)
            putString(KEY_PREFERENTIAL_DISCOUNT_TYPE, preferentialMedicineType?.name)
            putString(KEY_DOCUMENT_NUMBER, documentNumber)
            putLong(KEY_DOCUMENT_DATE, documentDate?.time ?: 0)
            putString(KEY_SERIAL_NUMBER, serialNumber)
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
        private const val VERSION = 2
        private const val KEY_SUBJECT_ID = "SUBJECT_ID"
        private const val KEY_PREFERENTIAL_DISCOUNT_TYPE = "PREFERENTIAL_DISCOUNT_TYPE"
        private const val KEY_DOCUMENT_NUMBER = "DOCUMENT_NUMBER"
        private const val KEY_DOCUMENT_DATE = "DOCUMENT_DATE"
        private const val KEY_SERIAL_NUMBER = "SERIAL_NUMBER"

        fun fromBundle(bundle: Bundle?): MedicineAttribute? {
            return bundle?.let {
                val subjectId = it.getString(KEY_SUBJECT_ID) ?: return@let null
                MedicineAttribute(
                        subjectId = subjectId,
                        preferentialMedicineType = it.getString(KEY_PREFERENTIAL_DISCOUNT_TYPE)?.let { s ->
                            PreferentialMedicineType.valueOf(s)
                        },
                        documentNumber = it.getString(KEY_DOCUMENT_NUMBER),
                        documentDate = Date(it.getLong(KEY_DOCUMENT_DATE)),
                        serialNumber = it.getString(KEY_SERIAL_NUMBER)
                )
            }
        }

        private fun create(dest: Parcel): MedicineAttribute {
            var medicineAttribute: MedicineAttribute? = null

            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->
                when (version) {
                    1 -> {
                        medicineAttribute = MedicineAttribute(parcel.readString())
                    }
                    else -> {
                        medicineAttribute = MedicineAttribute(
                                subjectId = parcel.readString(),
                                preferentialMedicineType = Utils.safeValueOf(PreferentialMedicineType::class.java, parcel.readString(),
                                        PreferentialMedicineType.NON_PREFERENTIAL_MEDICINE),
                                documentNumber = parcel.readString(),
                                documentDate = Date(parcel.readLong()),
                                serialNumber = parcel.readString()
                        )
                    }
                }
            }
            checkNotNull(medicineAttribute)
            return medicineAttribute as MedicineAttribute

        }
    }
}