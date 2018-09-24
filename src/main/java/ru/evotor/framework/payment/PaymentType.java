package ru.evotor.framework.payment;

/**
 * Тип оплаты (значение указывается в чеке как тип оплаты)
 */
public enum PaymentType {
    /**
     * Неизвестно. По-умолчанию
     */
    UNKNOWN,

    /**
     * Наличными
     */
    CASH,

    /**
     * Электронными
     */
    ELECTRON,

    /**
     * Предоплатой (зачетом аванса)
     */
    ADVANCE,

    /**
     * Постоплатой (в кредит)
     */
    CREDIT,
    /**
     * Встречным предоставлением
     */
    COUNTEROFFER
}
