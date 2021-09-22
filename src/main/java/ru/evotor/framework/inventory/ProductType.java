package ru.evotor.framework.inventory;

/**
 * Тип товара.
 */
public enum ProductType {
    /**
     * Обычный товар.
     */
    NORMAL,

    /**
     * Маркированный алкоголь.
     */
    ALCOHOL_MARKED,

    /**
     * Немаркированный алкоголь.
     */
    ALCOHOL_NOT_MARKED,

    /**
     * Услуга.
     */
    SERVICE,

    /**
     * Маркированный табак.
     */
    TOBACCO_MARKED,

    /**
     * Маркированная обувь
     */
    SHOES_MARKED,

    /**
     * Маркированные лекарства
     */
    MEDICINE_MARKED,

    /**
     * Шины
     */
    TYRES_MARKED,

    /**
     * Парфюмерия (духи, туалетная вода)
     */
    PERFUME_MARKED,

    /**
     * Фототовары (фотокамеры, лампы-вспышки)
     */
    PHOTOS_MARKED,

    /**
     * Легкая промышленность (одежда, белье)
     */
    LIGHT_INDUSTRY_MARKED,

    /**
     * Альтернативный табак (сигары, кретек, кальянный табак и т.д.)
     */
    TOBACCO_PRODUCTS_MARKED,

    /**
     * Молочная продукция (сыры, йогурты, молоко и т.д.)
     */
    DAIRY_MARKED,

    /**
     * Вода (природная, искусственная, минеральная и т.д.)
     */
    WATER_MARKED
}
