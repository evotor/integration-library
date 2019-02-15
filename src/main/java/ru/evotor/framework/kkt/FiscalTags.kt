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
}