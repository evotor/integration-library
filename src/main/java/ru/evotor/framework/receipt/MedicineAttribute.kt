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

/**
 * Дополнительные реквизиты пользователя, которые необходимы для формирования структурного тега 1084
 * (состоит из тегов 1085 и 1086)
 * Применяется к каждой печатной группе
 */
data class MedicineAttribute(
        /**
         * Часть композитного тега: идентификатор субъекта обращения
         * Значение дополнительного реквизита пользователя (тег 1086)
         */
        @FiscalRequisite(tag = FiscalTags.MEDICINE_ADDITIONAL_REQUISITE_VALUE)
        val subjectId: String,
        /**
         * Тип льготы рецепта
         * При наличии рецепта обязателен к заполнению
         * Необходим для формирования тега 1085
         */
        @FiscalRequisite(tag = FiscalTags.MEDICINE_ADDITIONAL_REQUISITE_TITLE)
        val preferentialMedicineType: PreferentialMedicineType? = null,

        /**
         * Часть композитного тега: дополнительные реквизиты пользователя (тег 1086)
         * обязательны для рецепта с полной, либо частичной льготой
         */
        @FiscalRequisite(tag = FiscalTags.MEDICINE_ADDITIONAL_REQUISITE_VALUE)
        val medicineAdditionalDetails: MedicineAdditionalDetails? = null

) : Parcelable, IBundlable {

    override fun writeToParcel(dest: Parcel, flag: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            parcel.writeString(subjectId)
            parcel.writeString(preferentialMedicineType?.name)
            parcel.writeParcelable(medicineAdditionalDetails, flag)
        }
    }

    override fun describeContents(): Int = 0

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_SUBJECT_ID, subjectId)
            putString(KEY_PREFERENTIAL_MEDICINE_TYPE, preferentialMedicineType?.name)
            putBundle(KEY_MEDICINE_ADDITIONAL_DETAILS, medicineAdditionalDetails?.toBundle())
        }
    }

    companion object {

        /**
         * Текущая версия объекта MedicineAttribute.
         */
        private const val VERSION = 3
        private const val KEY_SUBJECT_ID = "SUBJECT_ID"
        private const val KEY_PREFERENTIAL_MEDICINE_TYPE = "PREFERENTIAL_MEDICINE_TYPE"
        private const val KEY_MEDICINE_ADDITIONAL_DETAILS = "MEDICINE_ADDITIONAL_DETAILS"

        fun fromBundle(bundle: Bundle?): MedicineAttribute? {
            return bundle?.let {
                val subjectId = it.getString(KEY_SUBJECT_ID) ?: return@let null
                MedicineAttribute(
                        subjectId = subjectId,
                        preferentialMedicineType = Utils.safeValueOf(PreferentialMedicineType::class.java,
                                it.getString(KEY_PREFERENTIAL_MEDICINE_TYPE), null),
                        medicineAdditionalDetails = MedicineAdditionalDetails.fromBundle(it.getBundle(KEY_MEDICINE_ADDITIONAL_DETAILS))
                )
            }
        }

        @JvmField
        val CREATOR = object : Parcelable.Creator<MedicineAttribute> {
            override fun createFromParcel(parcel: Parcel): MedicineAttribute = create(parcel)
            override fun newArray(size: Int): Array<MedicineAttribute?> = arrayOfNulls(size)
        }

        private fun create(dest: Parcel): MedicineAttribute {
            var medicineAttribute: MedicineAttribute? = null

            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->

                var subjectId: String? = null
                var preferentialMedicineType: PreferentialMedicineType? = null
                var medicineAdditionalDetails: MedicineAdditionalDetails? = null

                if (version >= 1) {
                    subjectId = parcel.readString()
                }
                if (version >= 2) {
                    preferentialMedicineType = Utils.safeValueOf(PreferentialMedicineType::class.java, parcel.readString(),
                            null)
                    medicineAdditionalDetails = parcel.readParcelable(MedicineAdditionalDetails::class.java.classLoader)
                }
                if (version >= 3) {
                    // Во второй версии поменялся тип, здесь оставлено для обратной совместимости
                    // так как некоторые приложения могли использовать 3ю версию.
                    // Должна быть пустой
                }

                checkNotNull(subjectId)
                medicineAttribute = MedicineAttribute(
                        subjectId = subjectId,
                        preferentialMedicineType = preferentialMedicineType,
                        medicineAdditionalDetails = medicineAdditionalDetails
                )
            }
            checkNotNull(medicineAttribute)
            return medicineAttribute as MedicineAttribute

        }
    }
}