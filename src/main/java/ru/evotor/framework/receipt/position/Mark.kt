package ru.evotor.framework.receipt.position

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags

/**
 * Класс марки для маркированных товаров и алкоголя.
 * Марка записывается в реквизит "код товара" (тег 1162).
 *
 * Может иметь полное представление, где заполняется только @property fullMark,
 * ИЛИ
 * частичное представление по конкретным тэгам
 */
data class Mark(
        /**
         * Значение при полной марке
         */
        @FiscalRequisite(tag = FiscalTags.PRODUCT_CODE)
        val fullMark: String? = null,

        /**
         * Тег для кода товара
         * Может содержать в себе любые форматы марки
         */
        @FiscalRequisite(tag = FiscalTags.PRODUCT_CODE)
        val tagProductCode: String? = null

) : Parcelable, IBundlable {

    override fun writeToParcel(dest: Parcel, flags: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            parcel.writeString(fullMark)
            parcel.writeString(tagProductCode)
        }
    }

    override fun describeContents(): Int = 0

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_FULL_MARK, fullMark)
            putString(KEY_TAG_PRODUCT_CODE, tagProductCode)
        }
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Mark> {
            override fun createFromParcel(parcel: Parcel): Mark = create(parcel)
            override fun newArray(size: Int): Array<Mark?> = arrayOfNulls(size)
        }

        /**
         * Текущая версия объекта.
         */
        private const val VERSION = 1

        private const val KEY_FULL_MARK = "KEY_FULL_MARK"
        private const val KEY_TAG_PRODUCT_CODE = "KEY_TAG_PRODUCT_CODE"

        fun from(bundle: Bundle?): Mark? {
            return bundle?.let {
                Mark(fullMark = it.getString(KEY_FULL_MARK), tagProductCode = it.getString(KEY_TAG_PRODUCT_CODE))
            }
        }

        private fun create(dest: Parcel): Mark {
            var mark: Mark? = null

            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->
                if (version >= 1) {
                    mark = Mark(fullMark = parcel.readString(), tagProductCode = parcel.readString())
                }
            }
            checkNotNull(mark)
            return mark as Mark

        }
    }
}