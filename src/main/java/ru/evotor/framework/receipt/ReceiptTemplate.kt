package ru.evotor.framework.receipt

import org.json.JSONObject
import java.math.BigDecimal

data class ReceiptTemplate(
        /**
         * Uuid
         */
        val uuid: String,
        /**
         * Способ формирования
         */
        val formationMethod: ReceiptFormationMethod,
        /**
         * Тип происводимого расчёта
         */
        val settlementType: SettlementType,
        /**
         * Название организации продавца
         */
        val sellerOrganizationName: String,
        /**
         * ИНН организации продавца
         */
        val sellerOrganizationInn: String,
        /**
         * Адрес организации продавца
         */
        val sellerOrganizationAddress: String,
        /**
         * Система налогооблажения продавца
         */
        val sellerTaxationSystem: TaxationSystem,
        /**
         * Позиции
         */
        val positions: List<Position>,
        /**
         * Email покупателя для отправки чека по электронной почте
         */
        val customerEmail: String?,
        /**
         * Телефон покупателя для отправки чека по смс
         */
        val customerPhone: String?,
        /**
         * Оплаты
         */
        val payments: Map<Payment, BigDecimal>,
        /**
         * Сдачи
         */
        val changes: Map<Payment, BigDecimal>,
        /**
         * Дополнительные поля
         */
        val extra: JSONObject?,
        /**
         * Печатать/не печатать
         */
        val needToPrint: Boolean
)