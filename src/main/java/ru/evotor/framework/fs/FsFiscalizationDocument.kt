package ru.evotor.framework.fs

import ru.evotor.framework.receipt.TaxationSystem
import java.util.*
import kotlin.collections.ArrayList
import kotlin.experimental.and


/**
 * Сокращенный отчет о регистрации ФН
 */
data class FsFiscalizationDocument(
    /**
     * Дата и время
     */
    val date: Date,
    /**
     * ИНН
     */
    val inn: String,
    /**
     * Регистрационный номер ККТ
     */
    val rnm: String,
    /**
     * Системы налогообложения. Формат ФН
     */
    val taxationSystems: Byte,
    /**
     * Режим работы
     */
    val workModeFlags: Byte,
    /**
     * Расширенные признаки работы ККТ, только в ФФД1.1
     */
    val workModeExFlags: Byte? = null,
    /**
     * ИНН ОФД, только в ФФД1.1
     */
    val ofdInn: String? = null,
    /**
     * Код причины перерегистрации
     */
    val reregistrationReasonCode: Int? = null,
    /**
     * Номер ФД
     */
    val fiscalDocumentNumber: Int,
    /**
     * Фискальный признак
     */
    val fiscalDocumentSign: Int
) {
    /**
     * Список систем налогообложения
     */
    fun gatTaxationSystemsList(): List<TaxationSystem> {
        val resultList = ArrayList<TaxationSystem>()
        if (isBitSet(taxationSystems, 0)) {
            resultList.add(TaxationSystem.COMMON)
        }

        if (isBitSet(taxationSystems, 1)) {
            resultList.add(TaxationSystem.SIMPLIFIED_INCOME)
        }

        if (isBitSet(taxationSystems, 2)) {
            resultList.add(TaxationSystem.SIMPLIFIELD_INCOME_OUTCOME)
        }

        if (isBitSet(taxationSystems, 3)) {
            resultList.add(TaxationSystem.SINGLE_IMPUTED_INCOME)
        }

        if (isBitSet(taxationSystems, 4)) {
            resultList.add(TaxationSystem.SINGLE_AGRICULTURE)
        }

        if (isBitSet(taxationSystems, 5)) {
            resultList.add(TaxationSystem.PATENT)
        }

        return resultList
    }

    /**
     * Режим работы: Шифрование
     */
    fun isCrypt() = isBitSet(workModeFlags, 0)

    /**
     * Режим работы: Автономный режим
     */
    fun isAutonomous() = isBitSet(workModeFlags, 1)

    /**
     * Режим работы: Автоматический режим
     */
    fun isAutomatic() = isBitSet(workModeFlags, 2)

    /**
     * Режим работы: Применение в сфере услуг
     */
    fun isService() = isBitSet(workModeFlags, 3)

    /**
     * Режим работы: Режим кассовых чеков
     */
    fun isReceiptMode() = isBitSet(workModeFlags, 4)

    /**
     * Режим работы: Применение при расчетах в Интернет
     */
    fun isInternetPays() = isBitSet(workModeFlags, 5)


    private fun isBitSet(value: Byte, bitNum: Int): Boolean {
        if ((bitNum < 0) && (bitNum > 7)) {
            return false
        }
        val mask: Byte = 1.shl(bitNum).toByte()
        return value and mask == mask
    }
}