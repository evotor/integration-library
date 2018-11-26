package ru.evotor.framework.receipt

import org.json.JSONObject
import ru.evotor.framework.Document
import ru.evotor.framework.FfdTag
import ru.evotor.framework.FiscalDocument
import ru.evotor.framework.RepresentsFfdTags
import ru.evotor.framework.kkt.FfdVersion
import java.math.BigDecimal
import java.util.*

data class FiscalReceipt internal constructor(
        @RepresentsFfdTags(FfdTag(1057, FfdTag.NecessityIndex.ONE, Document.Form.PRINT, Document.Form.ELECTRONIC))
        override val uuid: UUID,

        override val name: String,
        override val formCode: String,
        override val number: Long,
        override val creationDate: Date,
        override val userName: String,
        override val userInn: String,
        override val settlementsAddress: String,
        override val settlementsPlace: String,
        override val kktRegistrationNumber: Long,
        override val sessionNumber: Long,
        override val registeredFfdVersion: FfdVersion,
        override val fiscalStorageNumber: Long,
        override val fpd: Long,
        override val fps: Long,
        /**
         * Способ, которым был сформирован чек
         */
        val formationMethod: ReceiptFormationMethod,
        /**
         * Тип происведённого расчёта
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
        val extra: JSONObject?
) : FiscalDocument()