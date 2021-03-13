package ru.evotor.framework.receipt

/**
 * Тип расчёта
 */
enum class SettlementType {
    /**
     * Приход
     */
    INCOME,

    /**
     * Возврат прихода
     */
    RETURN_OF_INCOME,

    /**
     * Расход
     */
    OUTCOME,

    /**
     * Возврат расхода
     */
    RETURN_OF_OUTCOME,

    /**
     * Коррекция прихода
     */
    CORRECTION_INCOME,

    /**
     * Коррекция расхода
     */
    CORRECTION_OUTCOME
}