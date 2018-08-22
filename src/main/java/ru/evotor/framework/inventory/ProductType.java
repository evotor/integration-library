package ru.evotor.framework.inventory;

/**
 * Created by a.kuznetsov on 30/04/2017.
 */

public enum ProductType {
    /**
     * Товар
     */
    NORMAL,

    /**
     * Маркированный алкоголь
     */
    ALCOHOL_MARKED,

    /**
     * Немаркированный алкоголь
     */
    ALCOHOL_NOT_MARKED,

    /**
     * Услуга
     */
    SERVICE,

    /**
     * Работа
     */
    LABOUR,

    /**
     * Ставка азартной игры
     */
    GAME_RATE,

    /**
     * Выигрыш азартной игры
     */
    GAME_PRIZE,

    /**
     * Ставка лотереи
     */
    LOTTERY_RATE,

    /**
     * Выигрыш лотереи
     */
    LOTTERY_PRIZE,

    /**
     * Предоставление РИД (результатов интеллектуальной деятельности)
     */
    RID_PROVIDE,

    /**
     * Выплата / Платеж
     */
    PAYMENT,

    /**
     * Агентское вознаграждение
     */
    AGENT_COMMISSION,

    /**
     * Составной предмет расчета
     */
    COMBINED_ITEM,

    /**
     * Иной предмет расчета
     */
    OTHER,

    /**
     * Имущественное право
     */
    PROPERTY_LAW,

    /**
     * Внереализационный доход
     */
    NON_OPERATING_INCOME,

    /**
     * Страховые взносы
     */
    INSURANCE_CONTRIBUTIONS,

    /**
     * Торговый сбор
     */
    MERCHANT_FEE,

    /**
     * Курортный сбор
     */
    RESORT_FEE
}
