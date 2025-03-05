package ru.evotor.framework.receipt.attribute

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.position.mapper.VeterinaryAttributeMapper

/**
 * Дополнительные реквизиты, которые необходимы для формирования структурного тега 1260 для ветеринарных препаратов
 */
data class VeterinaryAttribute(
    /**
     * Тип документа, по которому отпускается препарат
     */
    val type: VeterinaryDocumentType,
    /**
     * Номер документа, не более 70 символов
     */
    val documentNumber: String,
    /**
     * Дата документа в формате ГГММДД
     */
    val documentDate: String
) : IBundlable {

    override fun toBundle(): Bundle = VeterinaryAttributeMapper.writeToBundle(this)

    companion object {
        /**
         * Текущая версия объекта VeterinaryAttribute.
         */
        const val VERSION = 1

        @JvmStatic
        fun from(bundle: Bundle?): VeterinaryAttribute? = VeterinaryAttributeMapper.readFromBundle(bundle)
    }

    /**
     * Тип документа, по которому отпускается препарат
     */
    enum class VeterinaryDocumentType {
        /**
         * Рецепт
         */
        VPR,

        /**
         * Требование
         */
        VPT
    }
}
