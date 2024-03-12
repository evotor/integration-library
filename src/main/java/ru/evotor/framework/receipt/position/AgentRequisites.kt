package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Principal
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.receipt.position.mapper.AgentRequisitesMapper

/**
 * Реквизиты агента, необходимые при работе по [агентскому договору](https://developer.evotor.ru/docs/doc_agency_agreement.html).
 *
 * ВАЖНО! При создании реквизитов агента на устройстве агента или субагента,
 * используйте только те типы агента или субагента, которые были указаны при регистрации кассы.
 *
 * @see [ru.evotor.framework.kkt.api.KktApi.getRegisteredAgentTypes]
 * @see [ru.evotor.framework.kkt.api.KktApi.getRegisteredSubagentTypes]
 */
data class AgentRequisites(
    /**
     * Агент
     */
    val agent: Agent? = null,

    /**
     * Субагент
     */
    val subagent: Subagent? = null,

    /**
     * Принципал (поставщик)
     */
    val principal: Principal,

    /**
     * Оператор перевода
     */
    val transactionOperator: TransactionOperator? = null,

    /**
     * Описание операции
     */
    @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_OPERATION)
    val operationDescription: String? = null
) : IBundlable {
    companion object {
        /**
         * Создает агентские реквизиты для агента типа "Агент".
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         */
        @JvmStatic
        fun createForAgent(
            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_INN)
            principalInn: String,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            principalPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_NAME)
            principalName: String
        ) = AgentRequisitesMapper.create(
            agentType = Agent.Type.AGENT,
            agentPhones = null,
            subagentType = null,
            subagentPhones = null,
            principalInn = principalInn,
            principalPhones = principalPhones,
            principalName = principalName,
            transactionOperatorName = null,
            transactionOperatorInn = null,
            transactionOperatorPhones = null,
            transactionOperatorAddress = null,
            operationDescription = null
        )

        /**
         * Создает агентские реквизиты для агента типа "Комиссионер".
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         */
        @JvmStatic
        fun createForCommissioner(
            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_INN)
            principalInn: String,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            principalPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_NAME)
            principalName: String
        ) = AgentRequisitesMapper.create(
            agentType = Agent.Type.COMMISSIONER,
            agentPhones = null,
            subagentType = null,
            subagentPhones = null,
            principalInn = principalInn,
            principalPhones = principalPhones,
            principalName = principalName,
            transactionOperatorName = null,
            transactionOperatorInn = null,
            transactionOperatorPhones = null,
            transactionOperatorAddress = null,
            operationDescription = null
        )

        /**
         * Создает агентские реквизиты для агента типа "Поверенный".
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         */
        @JvmStatic
        fun createForAttorneyInFact(
            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_INN)
            principalInn: String,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            principalPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_NAME)
            principalName: String
        ) = AgentRequisitesMapper.create(
            agentType = Agent.Type.ATTORNEY_IN_FACT,
            agentPhones = null,
            subagentType = null,
            subagentPhones = null,
            principalInn = principalInn,
            principalPhones = principalPhones,
            principalName = principalName,
            transactionOperatorName = null,
            transactionOperatorInn = null,
            transactionOperatorPhones = null,
            transactionOperatorAddress = null,
            operationDescription = null
        )

        /**
         * Создает агентские реквизиты для агента типа "платёжный агент".
         * @param agentPhones телефоны платёжного агента и оператора по приёму платежей
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         */

        @JvmStatic
        fun createForPaymentAgent(
            @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            agentPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_INN)
            principalInn: String,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            principalPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_NAME)
            principalName: String,
        ) = AgentRequisitesMapper.create(
            agentType = Agent.Type.PAYMENT_AGENT,
            agentPhones = agentPhones,
            subagentType = null,
            subagentPhones = null,
            principalInn = principalInn,
            principalPhones = principalPhones,
            principalName = principalName,
            transactionOperatorName = null,
            transactionOperatorInn = null,
            transactionOperatorPhones = null,
            transactionOperatorAddress = null,
            operationDescription = null
        )

        /**
         * Создает агентские реквизиты для агента типа "Платёжный субагент".
         * @param agentPhones телефоны оператора по приёму платежей
         * @param subagentPhones телефоны платежного агента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         */

        fun createForPaymentSubagent(
            @FiscalRequisite(tag = FiscalTags.PAYMENT_OPERATOR_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            agentPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            subagentPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_INN)
            principalInn: String,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            principalPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_NAME)
            principalName: String,
        ) = AgentRequisitesMapper.create(
            agentType = null,
            agentPhones = agentPhones,
            subagentType = Subagent.Type.PAYMENT_SUBAGENT,
            subagentPhones = subagentPhones,
            principalInn = principalInn,
            principalPhones = principalPhones,
            principalName = principalName,
            transactionOperatorName = null,
            transactionOperatorInn = null,
            transactionOperatorPhones = null,
            transactionOperatorAddress = null,
            operationDescription = null
        )

        /**
         * Создает агентские реквизиты для агента типа "Банковский платёжный агент".
         * @param agentPhones телефоны платёжного агента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         * @param transactionOperatorName наименование оператора перевода
         * @param transactionOperatorInn ИНН оператора перевода
         * @param transactionOperatorPhones телефоны оператора перевода
         * @param transactionOperatorAddress адрес оператора перевода
         * @param operationDescription описание операции банковского платежного агента
         */
        @JvmStatic
        fun createForBankPaymentAgent(
            @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            agentPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_INN)
            principalInn: String,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            principalPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_NAME)
            principalName: String,

            @FiscalRequisite(tag = FiscalTags.TRANSACTION_OPERATOR_NAME)
            transactionOperatorName: String,

            @FiscalRequisite(tag = FiscalTags.TRANSACTION_OPERATOR_INN)
            transactionOperatorInn: String,

            @FiscalRequisite(tag = FiscalTags.TRANSACTION_OPERATOR_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            transactionOperatorPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.TRANSACTION_OPERATOR_ADDRESS)
            transactionOperatorAddress: String,

            @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_OPERATION)
            operationDescription: String
        ) = AgentRequisitesMapper.create(
            agentType = null,
            agentPhones = null,
            subagentType = Subagent.Type.BANK_PAYMENT_SUBAGENT,
            subagentPhones = agentPhones,
            principalInn = principalInn,
            principalPhones = principalPhones,
            principalName = principalName,
            transactionOperatorName = transactionOperatorName,
            transactionOperatorInn = transactionOperatorInn,
            transactionOperatorPhones = transactionOperatorPhones,
            transactionOperatorAddress = transactionOperatorAddress,
            operationDescription = operationDescription
        )

        /**
         * Создает агентские реквизиты для агента типа "Банковский платёжный субагент".
         * @param subagentPhones телефоны платежного агента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         * @param transactionOperatorName наименование оператора перевода
         * @param transactionOperatorInn ИНН оператора перевода
         * @param transactionOperatorPhones телефоны оператора перевода
         * @param transactionOperatorAddress адрес оператора перевода
         * @param operationDescription описание операции банковского платежного субагента
         */

        @JvmStatic
        fun createForBankPaymentSubagent(
            @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            subagentPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_INN)
            principalInn: String,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            principalPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.PRINCIPAL_NAME)
            principalName: String,

            @FiscalRequisite(tag = FiscalTags.TRANSACTION_OPERATOR_NAME)
            transactionOperatorName: String,

            @FiscalRequisite(tag = FiscalTags.TRANSACTION_OPERATOR_INN)
            transactionOperatorInn: String,

            @FiscalRequisite(tag = FiscalTags.TRANSACTION_OPERATOR_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
            transactionOperatorPhones: List<String>,

            @FiscalRequisite(tag = FiscalTags.TRANSACTION_OPERATOR_ADDRESS)
            transactionOperatorAddress: String,

            @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_OPERATION)
            operationDescription: String
        ) = AgentRequisitesMapper.create(
            agentType = null,
            agentPhones = null,
            subagentType = Subagent.Type.BANK_PAYMENT_SUBAGENT,
            subagentPhones = subagentPhones,
            principalInn = principalInn,
            principalPhones = principalPhones,
            principalName = principalName,
            transactionOperatorName = transactionOperatorName,
            transactionOperatorInn = transactionOperatorInn,
            transactionOperatorPhones = transactionOperatorPhones,
            transactionOperatorAddress = transactionOperatorAddress,
            operationDescription = operationDescription
        )

        fun from(bundle: Bundle?): AgentRequisites? = AgentRequisitesMapper.read(bundle)
    }

    override fun toBundle(): Bundle = AgentRequisitesMapper.write(this)
}
