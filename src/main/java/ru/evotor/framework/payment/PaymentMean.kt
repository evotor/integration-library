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
     * Предварительно уплаченные деньги (например, аванс)
     */
    PREPAID_MONEY,

    /**
     * Деньги, которые будут уплачены позже (кредитные деньги)
     */
    POSTPAID_MONEY,

    /**
     * Встречное предоставление
     */
    COUNTEROFFER
}