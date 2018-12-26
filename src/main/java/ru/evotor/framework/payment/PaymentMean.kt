package ru.evotor.framework.payment

/**
 * Платёжное средство
 */
enum class PaymentMean {
    /**
     * Наличные
     */
    CASH,

    /**
     * Безналичные
     */
    CASHLESS,

    /**
     * Предоплченные деньги
     */
    PREPAID_MONEY,

    /**
     * Постоплатные (кредитные) деньги
     */
    POSTPAID_MONEY,

    /**
     * Встречное предоставление
     */
    COUNTEROFFER
}