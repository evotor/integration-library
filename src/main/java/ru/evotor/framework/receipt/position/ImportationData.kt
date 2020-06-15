package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags

/**
 * Данные об импорте продукции
 * Применяются к позиции чека.
 */
data class ImportationData(
        /**
         * Код страны происхождения товара
         * Тег 1230
         */
        @FiscalRequisite(tag = FiscalTags.COUNTRY_ORIGIN_CODE)
        val countryOriginCode: String,
        /**
         * Номер таможенной декларации
         * Тег 1231
         */
        @FiscalRequisite(tag = FiscalTags.CUSTOM_DECLARATION_NUMBER)
        val customsDeclarationNumber: String
) : IBundlable {

    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_COUNTRY_ORIGIN_CODE, countryOriginCode)
        putString(KEY_CUSTOM_DECLARATION_NUMBER, customsDeclarationNumber)
    }

    companion object {
        private const val KEY_COUNTRY_ORIGIN_CODE = "COUNTRY_ORIGIN_CODE"
        private const val KEY_CUSTOM_DECLARATION_NUMBER = "CUSTOM_DECLARATION_NUMBER"

        @JvmStatic
        fun from(bundle: Bundle?): ImportationData? {
            return bundle?.let {
                ImportationData(
                        it.getString(KEY_COUNTRY_ORIGIN_CODE),
                        it.getString(KEY_CUSTOM_DECLARATION_NUMBER)
                )
            }
        }
    }

}