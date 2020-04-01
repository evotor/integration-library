package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags

/**
 * Дополнительные атрибуты для продажи Юр. лицу или ИП.
 * Применяются к позиции чека продажи.
 */
data class LegalPersonAttributes(
        /**
         * Акциз
         * Тег 1229
         */
        @FiscalRequisite(tag = FiscalTags.EXCISE)
        val excise: String? = null,
        /**
         * Код страны происхождения товара
         * Тег 1230
         */
        @FiscalRequisite(tag = FiscalTags.COUNTRY_ORIGIN_CODE)
        val countryOriginCode: String? = null,
        /**
         * Номер таможенной декларации
         * Тег 1231
         */
        @FiscalRequisite(tag = FiscalTags.CUSTOM_DECLARATION_NUMBER)
        val customsDeclarationNumber: String? = null
) : IBundlable {

    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_EXCISE, excise)
        putString(KEY_COUNTRY_ORIGIN_CODE, countryOriginCode)
        putString(KEY_CUSTOM_DECLARATION_NUMBER, customsDeclarationNumber)
    }

    companion object  {
        private const val KEY_EXCISE = "EXCISE"
        private const val KEY_COUNTRY_ORIGIN_CODE = "COUNTRY_ORIGIN_CODE"
        private const val KEY_CUSTOM_DECLARATION_NUMBER = "CUSTOM_DECLARATION_NUMBER"

        @JvmStatic
        fun from(bundle: Bundle?): LegalPersonAttributes? {
            return bundle?.let {
                LegalPersonAttributes(
                        it.getString(KEY_EXCISE),
                        it.getString(KEY_COUNTRY_ORIGIN_CODE),
                        it.getString(KEY_CUSTOM_DECLARATION_NUMBER)
                )
            }
        }
    }

}