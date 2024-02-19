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
                Agent.Type.AGENT,
                null,
                null,
                null,
                principalInn,
                principalPhones,
                principalName,
                null,
                null,
                null,
                null,
                null
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
                Agent.Type.COMMISSIONER,
                null,
                null,
                null,
                principalInn,
                principalPhones,
                principalName,
                null,
                null,
                null,
                null,
                null
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
                Agent.Type.ATTORNEY_IN_FACT,
                null,
                null,
                null,
                principalInn,
                principalPhones,
                principalName,
                null,
                null,
                null,
                null,
                null
        )

        /**
         * Создает агентские реквизиты для агента типа "платёжный агент".
         * @param agentPhones телефоны платёжного агента и оператора по приёму платежей
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         * @param operationDescription описание операции платежного агента
         */

        @JvmStatic
        @Deprecated(
            message = "Use the createForPaymentAgent function without the operationDescription argument",
            replaceWith = ReplaceWith("createForPaymentAgent(agentPhones, principalInn, principalPhones, principalName)"),
            level = DeprecationLevel.WARNING
        )
        fun createForPaymentAgent(
                @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
                agentPhones: List<String>,

                @FiscalRequisite(tag = FiscalTags.PRINCIPAL_INN)
                principalInn: String,

                @FiscalRequisite(tag = FiscalTags.PRINCIPAL_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
                principalPhones: List<String>,

                @FiscalRequisite(tag = FiscalTags.PRINCIPAL_NAME)
                principalName: String,

                @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_OPERATION)
                operationDescription: String
        ) = createForPaymentAgent(
                agentPhones,
                principalInn,
                principalPhones,
                principalName
            )

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
            Agent.Type.PAYMENT_AGENT,
            agentPhones,
            null,
            null,
            principalInn,
            principalPhones,
            principalName,
            null,
            null,
            null,
            null,
            null
        )

        /**
         * Создает агентские реквизиты для агента типа "Платёжный субагент".
         * @param agentPhones телефоны оператора по приёму платежей
         * @param subagentPhones телефоны платежного агента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param principalName название принципала (поставщика)
         * @param operationDescription описание операции платежного субагента
         */
        @JvmStatic
        @Deprecated(
            message = "Use the createForPaymentSubagent function without the operationDescription argument",
            replaceWith = ReplaceWith("createForPaymentSubagent(agentPhones, subagentPhones, principalInn, principalPhones, principalName)"),
            level = DeprecationLevel.WARNING
        )
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

                @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_OPERATION)
                operationDescription: String
        ) = createForPaymentSubagent(agentPhones, subagentPhones, principalInn, principalPhones, principalName)

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
            null,
            agentPhones,
            Subagent.Type.PAYMENT_SUBAGENT,
            subagentPhones,
            principalInn,
            principalPhones,
            principalName,
            null,
            null,
            null,
            null,
            null
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
                Agent.Type.BANK_PAYMENT_AGENT,
                agentPhones,
                null,
                null,
                principalInn,
                principalPhones,
                principalName,
                transactionOperatorName,
                transactionOperatorInn,
                transactionOperatorPhones,
                transactionOperatorAddress,
                operationDescription
        )

        /**
         * Создает агентские реквизиты для агента типа "Банковский платёжный субагент".
         * @param agentPhones телефоны банковского платёжного агента
         * @param subagentPhones телефоны банковского платёжного субагента
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
        @Deprecated(
            message = "Use the createForBankPaymentSubagent function without the agentPhones argument",
            replaceWith = ReplaceWith("createForBankPaymentSubagent(\n" +
                "            subagentPhones, \n" +
                "            principalInn, \n" +
                "            principalPhones, \n" +
                "            principalName, \n" +
                "            transactionOperatorName, \n" +
                "            transactionOperatorInn,\n" +
                "            transactionOperatorPhones,\n" +
                "            transactionOperatorAddress,\n" +
                "            operationDescription\n" +
                "        )"),
            level = DeprecationLevel.WARNING
        )
        fun createForBankPaymentSubagent(
                @FiscalRequisite(tag = FiscalTags.PAYMENT_AGENT_PHONE, flags = FiscalRequisite.FLAG_MULTIPLE_VALUES)
                agentPhones: List<String>,

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
        ) = createForBankPaymentSubagent(
            subagentPhones,
            principalInn,
            principalPhones,
            principalName,
            transactionOperatorName,
            transactionOperatorInn,
            transactionOperatorPhones,
            transactionOperatorAddress,
            operationDescription
        )

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
            null,
            null,
            Subagent.Type.BANK_PAYMENT_SUBAGENT,
            subagentPhones,
            principalInn,
            principalPhones,
            principalName,
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
