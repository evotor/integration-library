package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.FLAG_MULTIPLE_VALUES
import ru.evotor.framework.FiscalRequisite
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.TransactionOperatorMapper
import ru.evotor.framework.receipt.position.AgentRequisites
import java.util.*

/**
 * Оператор перевода
 */
data class TransactionOperator(
        /**
         * Uuid контрагента
         */
        override val uuid: UUID? = null,

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
        @FiscalRequisite(tag = AgentRequisites.TAG_TRANSACTION_OPERATOR_NAME)
        override val shortName: String? = null,

        /**
         * ИНН
         */
        @FiscalRequisite(tag = AgentRequisites.TAG_TRANSACTION_OPERATOR_INN)
        override val inn: String? = null,

        /**
         * КПП
         */
        override val kpp: String? = null,

        /**
         * Телефоны
         */
        @FiscalRequisite(tag = AgentRequisites.TAG_TRANSACTION_OPERATOR_PHONE, flags = [FLAG_MULTIPLE_VALUES])
        override val phones: List<String>? = null,

        /**
         * Адреса
         */
        @FiscalRequisite(tag = AgentRequisites.TAG_TRANSACTION_OPERATOR_ADDRESS)
        override val addresses: List<String>? = null
) : Counterparty() {
    companion object {
        fun from(bundle: Bundle?): TransactionOperator? = TransactionOperatorMapper.read(bundle)
    }
}