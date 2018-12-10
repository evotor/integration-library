package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.FLAG_MULTIPLE_VALUES
import ru.evotor.framework.FiscalRequisite
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.SubagentMapper
import ru.evotor.framework.receipt.position.AgentRequisites
import java.util.*

/**
 * Cубагент
 */
data class Subagent(
        /**
         * Uuid контрагента
         */
        override val uuid: UUID? = null,

        /**
         * Тип (признак) субагента
         */
        @FiscalRequisite(tag = AgentRequisites.TAG_SETTLEMENT_SUBJECT_AGENT_TYPE)
        val type: Type,

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
        @FiscalRequisite(tag = AgentRequisites.TAG_PAYMENT_AGENT_PHONE, flags = [FLAG_MULTIPLE_VALUES])
        override val phones: List<String>? = null,

        /**
         * Адреса
         */
        override val addresses: List<String>? = null
) : Counterparty() {
    /**
     * Тип субагента
     */
    enum class Type {
        /**
         * Платёжный субагент
         */
        PAYMENT_SUBAGENT,

        /**
         * Банковский платёжный субагент
         */
        BANK_PAYMENT_SUBAGENT,
    }

    companion object {
        fun from(bundle: Bundle?): Subagent? = SubagentMapper.read(bundle)
    }

    override fun toBundle(): Bundle = SubagentMapper.write(this, super.toBundle())
}