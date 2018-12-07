package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.FLAG_MULTIPLE_VALUES
import ru.evotor.framework.FiscalRequisite
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Principal
import ru.evotor.framework.receipt.position.mapper.AgentRequisitesMapper

/**
 * Агентские реквизиты
 *
 * ВАЖНО! При создании агентских реквизитов на устройстве агента или субагента необходимо
 * использовать только те типы агента или субагента, которые были указаны при регистрации кассы.
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
         * Операция агента
         */
        @FiscalRequisite(tag = TAG_PAYMENT_AGENT_OPERATION)
        val operationDescription: String? = null
) : IBundlable {

    companion object {
        /**
         * Фискальный тег "Признак агента по предмету расчёта"
         */
        const val TAG_SETTLEMENT_SUBJECT_AGENT_TYPE = 1222

        /**
         * Фискальный тег "Телефон платёжного агента"
         */
        const val TAG_PAYMENT_AGENT_PHONE = 1073

        /**
         * Фискальный тег "Телефон оператора по приёму платежей"
         */
        const val TAG_PAYMENT_OPERATOR_PHONE = 1074

        /**
         * Фискальный тег "Наименование поставщика"
         */
        const val TAG_PRINCIPAL_NAME = 1225

        /**
         * Фискальный тег "ИНН поставщика"
         */
        const val TAG_PRINCIPAL_INN = 1226

        /**
         * Фискальный тег "Телефон поставщика"
         */
        const val TAG_PRINCIPAL_PHONE = 1171

        /**
         * Фискальный тег "Наименование оператора перевода"
         */
        const val TAG_TRANSACTION_OPERATOR_NAME = 1026

        /**
         * Фискальный тег "ИНН оператора перевода"
         */
        const val TAG_TRANSACTION_OPERATOR_INN = 1016

        /**
         * Фискальный тег "Телефон оператора перевода"
         */
        const val TAG_TRANSACTION_OPERATOR_PHONE = 1075

        /**
         * Фискальный тег "Адрес оператора перевода"
         */
        const val TAG_TRANSACTION_OPERATOR_ADDRESS = 1005

        /**
         * Фискальный тег "Операция платёжного агента"
         */
        const val TAG_PAYMENT_AGENT_OPERATION = 1044

        /**
         * Создает агентские реквизиты для агента типа "Агент".
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         */
        @JvmStatic
        fun createForAgent(
                @FiscalRequisite(tag = TAG_PRINCIPAL_INN)
                principalInn: String,

                @FiscalRequisite(tag = TAG_PRINCIPAL_PHONE, flags = [FLAG_MULTIPLE_VALUES])
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
         * Создает агентские реквизиты для агента типа "Комиссионер".
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         */
        @JvmStatic
        fun createForCommissioner(
                @FiscalRequisite(tag = TAG_PRINCIPAL_INN)
                principalInn: String,

                @FiscalRequisite(tag = TAG_PRINCIPAL_PHONE, flags = [FLAG_MULTIPLE_VALUES])
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
         * Создает агентские реквизиты для агента типа "Поверенный".
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         */
        @JvmStatic
        fun createForAttorneyInFact(
                @FiscalRequisite(tag = TAG_PRINCIPAL_INN)
                principalInn: String,

                @FiscalRequisite(tag = TAG_PRINCIPAL_PHONE, flags = [FLAG_MULTIPLE_VALUES])
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
         * @param agentPhones телефоны платёжного агента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param operationDescription операция платежного агента
         */
        @JvmStatic
        fun createForPaymentAgent(
                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                @FiscalRequisite(tag = TAG_PAYMENT_OPERATOR_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                agentPhones: List<String>,

                @FiscalRequisite(tag = TAG_PRINCIPAL_INN)
                principalInn: String,

                @FiscalRequisite(tag = TAG_PRINCIPAL_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                principalPhones: List<String>,

                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_OPERATION)
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
         * Создает агентские реквизиты для агента типа "Платёжный субагент".
         * @param agentPhones телефоны платёжного агента (оператора по приёму платежей)
         * @param subagentPhones телефоны платёжного субагента
         * @param principalInn ИНН принципала (поставщика)
         * @param principalPhones телефоны принципала (поставщика)
         * @param operationDescription операция платежного субагента
         */
        @JvmStatic
        fun createForPaymentSubagent(
                @FiscalRequisite(tag = TAG_PAYMENT_OPERATOR_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                agentPhones: List<String>,

                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                subagentPhones: List<String>,

                @FiscalRequisite(tag = TAG_PRINCIPAL_INN)
                principalInn: String,

                @FiscalRequisite(tag = TAG_PRINCIPAL_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                principalPhones: List<String>,

                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_OPERATION)
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
         * Создает агентские реквизиты для агента типа "Банковский платёжный агент".
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
                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                agentPhones: List<String>,

                @FiscalRequisite(tag = TAG_PRINCIPAL_INN)
                principalInn: String,

                @FiscalRequisite(tag = TAG_PRINCIPAL_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                principalPhones: List<String>,

                @FiscalRequisite(tag = TAG_TRANSACTION_OPERATOR_NAME)
                transactionOperatorName: String,

                @FiscalRequisite(tag = TAG_TRANSACTION_OPERATOR_INN)
                transactionOperatorInn: String,

                @FiscalRequisite(tag = TAG_TRANSACTION_OPERATOR_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                transactionOperatorPhones: List<String>,

                @FiscalRequisite(tag = TAG_TRANSACTION_OPERATOR_ADDRESS)
                transactionOperatorAddress: String,

                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_OPERATION)
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
         * Создает агентские реквизиты для агента типа "Банковский платёжный субагент".
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
                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                agentPhones: List<String>,

                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                subagentPhones: List<String>,

                @FiscalRequisite(tag = TAG_PRINCIPAL_INN)
                principalInn: String,

                @FiscalRequisite(tag = TAG_PRINCIPAL_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                principalPhones: List<String>,

                @FiscalRequisite(tag = TAG_TRANSACTION_OPERATOR_NAME)
                transactionOperatorName: String,

                @FiscalRequisite(tag = TAG_TRANSACTION_OPERATOR_INN)
                transactionOperatorInn: String,

                @FiscalRequisite(tag = TAG_TRANSACTION_OPERATOR_PHONE, flags = [FLAG_MULTIPLE_VALUES])
                transactionOperatorPhones: List<String>,

                @FiscalRequisite(tag = TAG_TRANSACTION_OPERATOR_ADDRESS)
                transactionOperatorAddress: String,

                @FiscalRequisite(tag = TAG_PAYMENT_AGENT_OPERATION)
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