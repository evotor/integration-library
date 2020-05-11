package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.receipt.position.PreferentialDiscount
import ru.evotor.framework.receipt.position.PreferentialDiscount.PreferentialDiscountType

/**
 * Дополнительные реквизиты ползователя, которые необходимы для формирования структурного тега 1084 (состоит из тегов 1085 и 1086)
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
         * Необходим для формирования тега 1085
         */
        val preferentialDiscountType: PreferentialDiscountType? = null,
        /**
         * Номер документа, не более 200 символов;
         * Значение дополнительного реквизита пользователя (тег 1086)
         */
        val documentNumber: String? = null,
        /**
         * Дата документа в формате ГГММДД;
         * Значение дополнительного реквизита пользователя (тег 1086)
         */
        val documentDate: String? = null,
        /**
         * номер серии льготного рецепта;
         * Значение дополнительного реквизита пользователя (тег 1086)
         */
        val serialNumber: String? = null

) : Parcelable, IBundlable {

    /**
     * Тег 1085 наименование дополнительного реквизита пользователя
     */
    @FiscalRequisite(tag = FiscalTags.ADDITIONAL_REQUISITE_TITLE)
    lateinit var additionalRequisiteTitle: String

    /**
     * Тег 1086 значение дополнительного реквизита пользователя
     */
    @FiscalRequisite(tag = FiscalTags.ADDITIONAL_REQUISITE_VALUE)
    lateinit var additionalRequisiteValue: String

    init {
        when (preferentialDiscountType) {
            PreferentialDiscountType.FULL_DISCOUNT -> {
                createTags(
                        tag1085 = MDLP_ATTRIBUTE + MDLP_FULL_PARTIAL,
                        subjectId = subjectId,
                        documentNumber = documentNumber,
                        documentDate = documentDate,
                        serialNumber = serialNumber
                )
            }
            PreferentialDiscountType.PARTIAL_DISCOUNT -> {
                createTags(
                        tag1085 = MDLP_ATTRIBUTE,
                        subjectId = subjectId,
                        documentNumber = documentNumber,
                        documentDate = documentDate,
                        serialNumber = serialNumber
                )
            }
            PreferentialDiscountType.NON_DISCOUNT -> {
                createTags(
                        tag1085 = MDLP_ATTRIBUTE,
                        subjectId = subjectId
                )
            }
            else -> {
                createTags(
                        tag1085 = MDLP_ATTRIBUTE,
                        subjectId = subjectId
                )
            }
        }
    }

    private fun createTags(
            tag1085: String,
            subjectId: String,
            documentNumber: String? = null,
            documentDate: String? = null,
            serialNumber: String? = null
    ) {
        additionalRequisiteTitle = tag1085
        additionalRequisiteValue = buildString {
            if (!serialNumber.isNullOrBlank()) {
                append("ps${serialNumber.replace("&", "&&")}&")
            }
            if (!documentNumber.isNullOrBlank()) {
                append("dn${documentNumber.replace("&", "&&")}&")
            }
            if (!documentDate.isNullOrBlank()) {
                append("dd${documentDate}&")
            }
            append("sid${subjectId}&")
        }

    }

    override fun writeToParcel(dest: Parcel, flag: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            parcel.writeString(subjectId)
            parcel.writeString(preferentialDiscountType?.name)
            parcel.writeString(documentNumber)
            parcel.writeString(documentDate)
            parcel.writeString(serialNumber)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_SUBJECT_ID, subjectId)
            putString(KEY_PREFERENTIAL_DISCOUNT_TYPE, preferentialDiscountType?.name)
            putString(KEY_DOCUMENT_NUMBER, documentNumber)
            putString(KEY_DOCUMENT_DATE, documentDate)
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

        /**
         * код ОКУД, на основе этого значения система МДЛП понимает, что рецепт 100% льготный, передается в составе тега 1085
         */
        private const val MDLP_FULL_PARTIAL = "3108805"

        /**
         * Постоянное значение, в составе тега 1085
         */
        private const val MDLP_ATTRIBUTE = "mdlp"

        fun fromBundle(bundle: Bundle?): MedicineAttribute? {
            return bundle?.let {
                val subjectId = it.getString(KEY_SUBJECT_ID) ?: return@let null
                MedicineAttribute(
                        subjectId = subjectId,
                        preferentialDiscountType = it.getString(KEY_PREFERENTIAL_DISCOUNT_TYPE)?.let { s ->
                            PreferentialDiscountType.valueOf(s)
                        },
                        documentNumber = it.getString(KEY_DOCUMENT_NUMBER),
                        documentDate = it.getString(KEY_DOCUMENT_DATE),
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
                    2 -> {
                        medicineAttribute = MedicineAttribute(
                                subjectId = parcel.readString(),
                                preferentialDiscountType = parcel.readString()?.let {
                                    PreferentialDiscountType.valueOf(it)
                                },
                                documentNumber = parcel.readString(),
                                documentDate = parcel.readString(),
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