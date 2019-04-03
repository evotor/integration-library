package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.AgentMapper
import ru.evotor.framework.kkt.FiscalTags
import java.util.*

/**
 * Агент
 */
data class Agent(
        /**
         * Uuid контрагента
         */
        override val uuid: UUID? = null,

        /**
         * Тип (признак) агента
         */
        @FiscalRequisite(tag = FiscalTags.SETTLEMENT_SUBJECT_AGENT_TYPE)
        val type: Type? = null,

        /**
         * Тип контрагента
         */
        override val counterpartyType: Counterparty.Type? = null,

        /**
         * Наименование полное
         */
        override val fullName: String? = null,

        /**
         * Наименование краткое
         */
        override val shortName: String? = null,

        /**
         * ИНН
         */
        override val inn: String? = null,

        /**
         * КПП
         */
        override val kpp: String? = null,

        /**
         * Телефоны
         */
        @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
        @FiscalRequisite(tag = FiscalTags.PAYMENT_OPERATOR_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
        override val phones: List<String>? = null,

        /**
         * Адреса
         */
        override val addresses: List<String>? = null
) : Counterparty() {
    /**
     * Тип агента
     */
    enum class Type {
        /**
         * Агент
         */
        AGENT,

        /**
         * Комиссионер
         */
        COMMISSIONER,

        /**
         * Поверенный
         */
        ATTORNEY_IN_FACT,

        /**
         * Платёжный агент
         */
        PAYMENT_AGENT,

        /**
         * Банковский платёжный агент
         */
        BANK_PAYMENT_AGENT,
    }

    companion object {
        fun from(bundle: Bundle?): Agent? = AgentMapper.read(bundle)
    }

    override fun toBundle(): Bundle = AgentMapper.write(this, super.toBundle())
}