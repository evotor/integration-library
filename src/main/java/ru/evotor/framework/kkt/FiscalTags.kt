package ru.evotor.framework.kkt

/**
 * Фискальные теги
 */
object FiscalTags {
    /**
     * Номер ФД
     */
    const val DOCUMENT_NUMBER = 1040

    /**
     * Дата, время
     */
    const val CREATION_DATE = 1012

    /**
     * Регистрационный номер ККТ
     */
    const val KKT_REGISTRATION_NUMBER = 1037

    /**
     * Номер смены
     */
    const val SESSION_NUMBER = 1038

    /**
     * Номер ФН
     */
    const val FISCAL_STORAGE_NUMBER = 1041

    /**
     * ФПД
     */
    const val FISCAL_IDENTIFIER = 1077

    /**
     * Признак расчёта
     */
    const val SETTLEMENT_TYPE = 1054

    /**
     * Применяемая система налогообложения
     */
    const val TAXATION_SYSTEM = 1055

    /**
     * Ставка НДС
     */
    const val VAT_RATE = 1199

    /**
     * Признак агента по предмету расчёта
     */
    const val SETTLEMENT_SUBJECT_AGENT_TYPE = 1222

    /**
     * Телефон платёжного агента
     */
    const val PAYMENT_AGENT_PHONE = 1073

    /**
     * Телефон оператора по приёму платежей
     */
    const val PAYMENT_OPERATOR_PHONE = 1074

    /**
     * Наименование поставщика
     */
    const val PRINCIPAL_NAME = 1225

    /**
     * ИНН поставщика
     */
    const val PRINCIPAL_INN = 1226

    /**
     * Телефон поставщика
     */
    const val PRINCIPAL_PHONE = 1171

    /**
     * Наименование оператора перевода
     */
    const val TRANSACTION_OPERATOR_NAME = 1026

    /**
     * ИНН оператора перевода
     */
    const val TRANSACTION_OPERATOR_INN = 1016

    /**
     * Телефон оператора перевода
     */
    const val TRANSACTION_OPERATOR_PHONE = 1075

    /**
     * Адрес оператора перевода
     */
    const val TRANSACTION_OPERATOR_ADDRESS = 1005

    /**
     * Операция платёжного агента
     */
    const val PAYMENT_AGENT_OPERATION = 1044

    /**
     * Код товара
     */
    const val PRODUCT_CODE = 1162

    /**
     * Тип коррекции
     */
    const val CORRECTION_TYPE = 1173

    /**
     * Основание для коррекции
     */
    const val BASIS_FOR_CORRECTION = 1174

    /**
     * Описание коррекции
     */
    const val CORRECTION_DESCRIPTION = 1177

    /**
     * Дата совершения корректируемого расчета
     */
    const val CORRECTABLE_SETTLEMENT_DATE = 1178

    /**
     * Номер предписания налогового органа
     */
    const val PRESCRIPTION_NUMBER = 1179

    /**
     * Адрес места расчёта
     */
    const val PAYMENT_ADDRESS = 1009

    /**
     * Описание места расчёта
     */
    const val PAYMENT_PLACE = 1187

    /**
     * Акциз
     */
    const val EXCISE = 1229

    /**
     * Код страны происхождения товара
     */
    const val COUNTRY_ORIGIN_CODE = 1230

    /**
     * Номер таможенной декларации
     */
    const val CUSTOM_DECLARATION_NUMBER = 1231

    /**
     * продажа лекарств
     * дополнительный реквизит, структурный тег, в него входит 1085 и 1086. Указывается для всего чека
     */
    const val MEDICINE_COMPOUND_ADDITIONAL_REQUISITE = 1084

    /**
     * продажа лекарств
     * наименование дополнительного реквизита.
     */
    const val MEDICINE_ADDITIONAL_REQUISITE_TITLE = 1085

    /**
     * продажа лекарств
     * значение дополнительного реквизита.
     */
    const val MEDICINE_ADDITIONAL_REQUISITE_VALUE = 1086

}