package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.core.IntegrationLibraryParsingException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Реквизиты покупателя, которые могут быть записаны в [печатную группу чека][ru.evotor.framework.receipt.PrintGroup].
 *
 * Реквизиты покупателя необходимо указывать при расчёте между организациями и (или) индивидуальными предпринимателями, наличными или банковской картой.
 *
 * @property name Наименование покупателя, например, название организации. Данные сохраняются в теге 1227 фискального документа.
 * @property innNumber Номер ИНН покупателя. Данные сохраняются в теге 1228 фискального документа.
 * @property birthDate Дата рождения покупателя. Данные сохраняются в теге 1243 фискального документа.
 * @property documentType Вид документа, удостоверяющего личность. Код вида документа сохраняется в теге 1245 фискального документа.
 * @property documentNumber Данные документа, удостоверяющего личность. Данные сохраняются в теге 1246 фискального документа.
 * @property type Тип покупателя, например, физическое лицо. Не сохраняется в фискальном документе.
 */
data class Purchaser(
        val name: String,
        val innNumber: String?,
        val birthDate: Date?,
        val documentType: DocumentType?,
        val documentNumber: String?,
        val type: PurchaserType
) : Parcelable, IBundlable {

    val version = 2

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_NAME, name)
            putString(KEY_INN_NUMBER, innNumber)
            putString(KEY_BIRTH_DATE, birthDate?.let { dateToString(it) })
            putInt(KEY_DOCUMENT_TYPE, documentType?.documentCode ?: -1)
            putString(KEY_DOCUMENT_NUMBER, innNumber ?: documentNumber)
            putString(KEY_DOCUMENT_NUMBER_V2, documentNumber)
            putInt(KEY_TYPE, type.ordinal)
            putInt(KEY_BUNDLE_VERSION, version)
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readString()
            ?: throw IntegrationLibraryParsingException(Purchaser::class.java),
        parcel.readString(),
        parcel.readString()?.let { stringToDate(it) },
        if(parcel.readInt() == 0) null else {
            val documentCode = parcel.readInt()
            DocumentType.values().firstOrNull { documentType -> documentType.documentCode == documentCode } },
        parcel.readString(),
        PurchaserType.values()[parcel.readInt() % PurchaserType.values().size]
    )
    

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(innNumber)
        parcel.writeString(birthDate?.let { dateToString(it) })
        parcel.writeInt(if (documentType == null) 0 else 1)
        documentType?.let { parcel.writeInt(documentType.documentCode) }
        parcel.writeString(documentNumber)
        parcel.writeInt(type.ordinal)
    }

    override fun describeContents(): Int = 0

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<Purchaser> {
            override fun createFromParcel(parcel: Parcel) = Purchaser(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Purchaser>(size)
        }

        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_INN_NUMBER = "KEY_INN_NUMBER"
        private const val KEY_BIRTH_DATE = "KEY_BIRTH_DATE"
        private const val KEY_DOCUMENT_TYPE = "KEY_DOCUMENT_TYPE"
        private const val KEY_DOCUMENT_NUMBER = "KEY_DOCUMENT_NUMBER"
        private const val KEY_DOCUMENT_NUMBER_V2 = "KEY_DOCUMENT_NUMBER_V2"
        private const val KEY_TYPE = "KEY_TYPE"
        private const val KEY_BUNDLE_VERSION = "KEY_BUNDLE_VERSION"
        private const val DATE_FORMAT = "dd.MM.yyyy"

        fun fromBundle(bundle: Bundle?): Purchaser? {
            return bundle?.let {
                val bundleVersion = it.getInt(KEY_BUNDLE_VERSION, 1)
                val name = it.getString(KEY_NAME) ?: return null
                val innNumber = if (bundleVersion >= 2) it.getString(KEY_INN_NUMBER)
                else it.getString(KEY_DOCUMENT_NUMBER)
                val birthDate = it.getString(KEY_BIRTH_DATE)
                val documentTypeCode = it.getInt(KEY_DOCUMENT_TYPE, -1)
                val documentType = if (documentTypeCode != -1) {
                    DocumentType.values().first { documentType -> documentType.documentCode == documentTypeCode }
                } else null
                val documentNumber = it.getString(KEY_DOCUMENT_NUMBER_V2)
                val purchaserTypeOrdinal = it.getInt(KEY_TYPE)
                val purchaserType = PurchaserType.values()[purchaserTypeOrdinal % PurchaserType.values().size]
                Purchaser(name, innNumber, birthDate?.let { stringToDate(birthDate) }, documentType, documentNumber, purchaserType)
            }
        }

        fun dateToString(date: Date?, dateFormat: String = DATE_FORMAT): String? {
            return date?.let { SimpleDateFormat(dateFormat, Locale.getDefault()).format(it) }
        }

        fun stringToDate(dateStr: String?, dateFormat: String = DATE_FORMAT): Date? {
            return try {
                dateStr?.let { SimpleDateFormat(dateFormat, Locale.getDefault()).parse(it) }
            } catch (e: ParseException) {
                null
            }
        }
    }
}

/**
 * Тип покупателя. Не сохраняется в фискальном документе.
 */
enum class PurchaserType {

    /**
     * Физическое лицо.
     */
    NATURAL_PERSON,

    /**
     * Индивидуальный предприниматель.
     */
    ENTREPRENEUR,

    /**
     * Юридическое лицо.
     */
    LEGAL_ENTITY
}

/**
 * Значения реквизита "код вида документа, удостоверяющего личность". Данные сохраняются в теге 1245 фискального документа.
 */
enum class DocumentType(val documentCode: Int) {

    /**
     * Паспорт гражданина РФ
     */
    PASSPORT_RF(21),

    /**
     * Паспорт гражданина РФ, дипломатический паспорт, служебный паспорт,
     * удостоверяющие личность гражданина Российской Федерации за пределами РФ.
     */
    PASSPORT_DIPLOMATIC(22),

    /**
     * Временное удостоверение личности гражданина Российской Федерации, выдаваемое на период оформления паспорта гражданина РФ.
     */
    TEMP_IDENTIFICATION(26),

    /**
     * Свидетельство о рождении гражданина РФ (для граждан Российской Федерации в возрасте до 14 лет)
     */
    BIRTH_CERTIFICATE(27),

    /**
     * Иные документы, признаваемые документами, удостоверяющими личность гражданина РФ в соответствии с законодательством РФ
     */
    OTHER_IDENTITY_DOC_RF(28),

    /**
     * Паспорт иностранного гражданина
     */
    PASSPORT_FOREIGN_CITIZEN(31),

    /**
     * Иные документы, признаваемые документами,
     * удостоверяющими личность иностранного гражданина в соответствии с законодательством РФ и международным договором РФ
     */
    OTHER_DOC_FOREIGN_CITIZEN_RECOGNIZED_RF(32),

    /**
     * Документ, выданный иностранным государством и признаваемый в соответствии с международным договором РФ в качестве документа,
     * удостоверяющего личность лица без гражданства.
     */
    DOC_BY_FOREIGN_STATE_TO_STATELESS_PERSON(33),

    /**
     * Вид на жительство (для лиц без гражданства)
     */
    RESIDENT_CARD(34),

    /**
     * Разрешение на временное проживание (для лиц без гражданства)
     */
    TEMP_RESIDENCE_PERMIT(35),

    /**
     * Свидетельство о рассмотрении ходатайства о признании лица без гражданства беженцем на территории РФ по существу
     */
    APP_REVIEW_CERTIFICATE_RECOGNITION_REFUGEE_PERSON_RF(36),

    /**
     * Удостоверение беженца
     */
    REFUGEE_IDENTIFICATION(37),

    /**
     * Иные документы, признаваемые документами,
     * удостоверяющими личность лиц без гражданства в соответствии с законодательством РФ и международным договором РФ
     */
    OTHER_IDENTITY_DOC_STATELESS_PERSONS(38),

    /**
     * Документ, удостоверяющий личность лица, не имеющего действительного документа,
     * удостоверяющего личность, на период рассмотрения заявления о признании гражданином РФ или о приеме в гражданство РФ
     */
    DOC_FOR_PERIOD_OF_CONSIDERATION_CITIZENSHIP_RF(40),
}
