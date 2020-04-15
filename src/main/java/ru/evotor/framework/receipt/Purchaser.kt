package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable

/**
 * Реквизиты покупателя, которые могут быть записаны в [печатную группу чека][ru.evotor.framework.receipt.PrintGroup].
 *
 * Реквизиты покупателя необходимо указывать при расчёте между организациями и (или) индивидуальными предпринимателями, наличными или банковской картой.
 *
 * @property name Наименование покупателя, например, название организации. Данные сохраняются в теге 1227 фискального документа.
 * @property documentNumber Номер документа покупателя, например, ИНН или номер паспорта иностранного гражданина. Данные сохраняются в теге 1228 фискального документа.
 * @property type Тип покупателя, например, физическое лицо. Не сохраняется в фискальном документе.
 */
data class Purchaser(
        val name: String,
        val documentNumber: String,
        val type: PurchaserType?
) : Parcelable, IBundlable {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_NAME, name)
            putString(KEY_DOCUMENT_NUMBER, documentNumber)
            putInt(KEY_TYPE, type?.ordinal ?: -1)
        }
    }

    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            if (parcel.readInt() == 0) null else PurchaserType.values()[parcel.readInt() % PurchaserType.values().size])

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(documentNumber)
        parcel.writeInt(if (type == null) 0 else 1)
        type?.let { parcel.writeInt(it.ordinal) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<Purchaser> {
            override fun createFromParcel(parcel: Parcel) = Purchaser(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Purchaser>(size)
        }

        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_DOCUMENT_NUMBER = "KEY_DOCUMENT_NUMBER"
        private const val KEY_TYPE = "KEY_TYPE"

        fun fromBundle(bundle: Bundle?): Purchaser? {
            return bundle?.let {
                Purchaser(it.getString(KEY_NAME), it.getString(KEY_DOCUMENT_NUMBER),
                        it.getInt(KEY_TYPE).let {
                            if (it == -1) {
                                null
                            } else {
                                PurchaserType.values()[it % PurchaserType.values().size]
                            }
                        }
                )
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