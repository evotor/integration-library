package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.FfdTag
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Principal
import ru.evotor.framework.receipt.position.mapper.AgentRequisitesMapper

/**
 * Агентские реквизиты позиции чека
 */
data class AgentRequisites(
        /**
         * Агент
         */
        val agent: Agent?,

        /**
         * Субагент
         */
        val subagent: Subagent?,

        /**
         * Принципал (поставщик)
         */
        val principal: Principal,

        /**
         * Оператор перевода
         */
        val transactionOperator: TransactionOperator?,

        /**
         * Операция агента
         */
        @FfdTag(1044)
        val operationDescription: String?
) : IBundlable {

    companion object {

        /**
         * Создает агентские реквизиты для агента типа "агент".
         *
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         */
        @JvmStatic
        fun createForAgent(
                @FfdTag(1226)
                principalInn: String,

                @FfdTag(1171)
                principalPhones: List<String>
        ) = AgentRequisitesMapper.create(
                Agent.Type.AGENT,
                null,
                null,
                null,
                principalInn,
                principalPhones,
                null,
                null,
                null,
                null,
                null
        )

        /**
         * Создает агентские реквизиты для агента типа "комиссионер".
         *
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         */
        @JvmStatic
        fun createForCommissioner(
                @FfdTag(1226)
                principalInn: String,

                @FfdTag(1171)
                principalPhones: List<String>
        ) = AgentRequisitesMapper.create(
                Agent.Type.COMMISSIONER,
                null,
                null,
                null,
                principalInn,
                principalPhones,
                null,
                null,
                null,
                null,
                null
        )

        /**
         * Создает агентские реквизиты для агента типа "поверенный".
         *
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         */
        @JvmStatic
        fun createForAttorneyInFact(
                @FfdTag(1226)
                principalInn: String,

                @FfdTag(1171)
                principalPhones: List<String>
        ) = AgentRequisitesMapper.create(
                Agent.Type.ATTORNEY_IN_FACT,
                null,
                null,
                null,
                principalInn,
                principalPhones,
                null,
                null,
                null,
                null,
                null
        )

        /**
         * Создает агентские реквизиты для агента типа "платёжный агент".
         *
         * @param agentPhones телефоны платёжного агента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param operationDescription операция платежного агента
         */
        @JvmStatic
        fun createForPaymentAgent(
                @FfdTag(1073, 1074)
                agentPhones: List<String>,

                @FfdTag(1226)
                principalInn: String,

                @FfdTag(1171)
                principalPhones: List<String>,

                @FfdTag(1044)
                operationDescription: String
        ) = AgentRequisitesMapper.create(
                Agent.Type.PAYMENT_AGENT,
                agentPhones,
                null,
                null,
                principalInn,
                principalPhones,
                null,
                null,
                null,
                null,
                operationDescription
        )

        /**
         * Создает агентские реквизиты для агента типа "платёжный субагент".
         *
         * @param agentPhones телефоны платёжного агента (оператора по приёму платежей)
         * @param subagentPhones телефоны платёжного субагента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param operationDescription операция платежного субагента
         */
        @JvmStatic
        fun createForPaymentSubagent(
                @FfdTag(1074)
                agentPhones: List<String>,

                @FfdTag(1073)
                subagentPhones: List<String>,

                @FfdTag(1226)
                principalInn: String,

                @FfdTag(1171)
                principalPhones: List<String>,

                @FfdTag(1044)
                operationDescription: String
        ) = AgentRequisitesMapper.create(
                null,
                agentPhones,
                Subagent.Type.PAYMENT_SUBAGENT,
                subagentPhones,
                principalInn,
                principalPhones,
                null,
                null,
                null,
                null,
                operationDescription
        )

        /**
         * Создает агентские реквизиты для агента типа "банковский платёжный агент".
         *
         * @param agentPhones телефоны банковского платёжного агента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param transactionOperatorName наименование оператора перевода
         * @param transactionOperatorInn ИНН оператора перевода
         * @param transactionOperatorPhones телефоны оператора перевода
         * @param transactionOperatorAddress адрес оператора перевода
         * @param operationDescription операция банковского платежного агента
         */
        @JvmStatic
        fun createForBankPaymentAgent(
                @FfdTag(1073)
                agentPhones: List<String>,

                @FfdTag(1226)
                principalInn: String,

                @FfdTag(1171)
                principalPhones: List<String>,

                @FfdTag(1026)
                transactionOperatorName: String,

                @FfdTag(1016)
                transactionOperatorInn: String,

                @FfdTag(1075)
                transactionOperatorPhones: List<String>,

                @FfdTag(1005)
                transactionOperatorAddress: String,

                @FfdTag(1044)
                operationDescription: String
        ) = AgentRequisitesMapper.create(
                Agent.Type.BANK_PAYMENT_AGENT,
                agentPhones,
                null,
                null,
                principalInn,
                principalPhones,
                transactionOperatorName,
                transactionOperatorInn,
                transactionOperatorPhones,
                transactionOperatorAddress,
                operationDescription
        )

        /**
         * Создает агентские реквизиты для агента типа "банковский платёжный субагент".
         *
         * @param agentPhones телефоны банковского платёжного агента
         * @param subagentPhones телефоны банковского платёжного субагента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param transactionOperatorName наименование оператора перевода
         * @param transactionOperatorInn ИНН оператора перевода
         * @param transactionOperatorPhones телефоны оператора перевода
         * @param transactionOperatorAddress адрес оператора перевода
         * @param operationDescription операция банковского платежного субагента
         */
        @JvmStatic
        fun createForBankPaymentSubagent(
                @FfdTag(1073)
                agentPhones: List<String>,

                @FfdTag(1073)
                subagentPhones: List<String>,

                @FfdTag(1226)
                principalInn: String,

                @FfdTag(1171)
                principalPhones: List<String>,

                @FfdTag(1026)
                transactionOperatorName: String,

                @FfdTag(1016)
                transactionOperatorInn: String,

                @FfdTag(1075)
                transactionOperatorPhones: List<String>,

                @FfdTag(1005)
                transactionOperatorAddress: String,

                @FfdTag(1044)
                operationDescription: String
        ) = AgentRequisitesMapper.create(
                null,
                agentPhones,
                Subagent.Type.BANK_PAYMENT_SUBAGENT,
                subagentPhones,
                principalInn,
                principalPhones,
                transactionOperatorName,
                transactionOperatorInn,
                transactionOperatorPhones,
                transactionOperatorAddress,
                operationDescription
        )

        fun from(bundle: Bundle?): AgentRequisites? = AgentRequisitesMapper.read(bundle)

    }

    override fun toBundle(): Bundle = AgentRequisitesMapper.write(this)

}