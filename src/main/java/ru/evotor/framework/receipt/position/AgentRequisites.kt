package ru.evotor.framework.receipt.position

import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Supplier

data class AgentRequisites(
        val agent: Agent,
        val supplier: Supplier,
        val transactionOperator: TransactionOperator?,
        val operationDescription: String?
) {

    companion object {

        fun createForAgentTypeAgent(
                supplierInn: String,
                supplierPhones: List<String>
        ) = create(
                Agent.Type.AGENT,
                null,
                supplierInn,
                supplierPhones,
                null,
                null,
                null,
                null,
                null
        )

        fun createForAgentTypeCommissioner(
                supplierInn: String,
                supplierPhones: List<String>
        ) = create(
                Agent.Type.COMMISSIONER,
                null,
                supplierInn,
                supplierPhones,
                null,
                null,
                null,
                null,
                null
        )

        fun createForAgentTypeAttorneyInFact(
                supplierInn: String,
                supplierPhones: List<String>
        ) = create(
                Agent.Type.ATTORNEY_IN_FACT,
                null,
                supplierInn,
                supplierPhones,
                null,
                null,
                null,
                null,
                null
        )

        fun createForAgentTypePaymentAgent(
                agentPhones: List<String>,
                supplierInn: String,
                supplierPhones: List<String>,
                transactionOperatorPhones: List<String>,
                operationDescription: String
        ) = create(
                Agent.Type.PAYMENT_AGENT,
                agentPhones,
                supplierInn,
                supplierPhones,
                null,
                null,
                transactionOperatorPhones,
                null,
                operationDescription
        )

        fun createForAgentTypePaymentSubagent(
                agentPhones: List<String>,
                supplierInn: String,
                supplierPhones: List<String>,
                transactionOperatorPhones: List<String>,
                operationDescription: String
        ) = create(
                Agent.Type.PAYMENT_SUBAGENT,
                agentPhones,
                supplierInn,
                supplierPhones,
                null,
                null,
                transactionOperatorPhones,
                null,
                operationDescription
        )

        fun createForAgentTypeBankPaymentAgent(
                agentPhones: List<String>,
                supplierInn: String,
                supplierPhones: List<String>,
                transactionOperatorName: String,
                transactionOperatorInn: String,
                transactionOperatorPhones: List<String>,
                transactionOperatorAddress: String,
                operationDescription: String
        ) = create(
                Agent.Type.BANK_PAYMENT_AGENT,
                agentPhones,
                supplierInn,
                supplierPhones,
                transactionOperatorName,
                transactionOperatorInn,
                transactionOperatorPhones,
                transactionOperatorAddress,
                operationDescription
        )

        fun createForAgentTypeBankPaymentSubagent(
                agentPhones: List<String>,
                supplierInn: String,
                supplierPhones: List<String>,
                transactionOperatorName: String,
                transactionOperatorInn: String,
                transactionOperatorPhones: List<String>,
                transactionOperatorAddress: String,
                operationDescription: String
        ) = create(
                Agent.Type.BANK_PAYMENT_SUBAGENT,
                agentPhones,
                supplierInn,
                supplierPhones,
                transactionOperatorName,
                transactionOperatorInn,
                transactionOperatorPhones,
                transactionOperatorAddress,
                operationDescription
        )

        private fun create(
                agentType: Agent.Type,
                agentPhones: List<String>?,
                supplierInn: String,
                supplierPhones: List<String>,
                transactionOperatorName: String?,
                transactionOperatorInn: String?,
                transactionOperatorPhones: List<String>?,
                transactionOperatorAddress: String?,
                operationDescription: String?
        ) = AgentRequisites(
                Agent(
                        null,
                        agentType,
                        null,
                        null,
                        null,
                        null,
                        null,
                        Counterparty.Contacts(agentPhones, null)
                ),
                Supplier(
                        null,
                        null,
                        null,
                        null,
                        supplierInn,
                        null,
                        Counterparty.Contacts(supplierPhones, null)
                ),
                TransactionOperator(
                        null,
                        null,
                        transactionOperatorName,
                        null,
                        transactionOperatorInn,
                        null,
                        Counterparty.Contacts(
                                transactionOperatorPhones,
                                transactionOperatorAddress?.let { listOf(it) }
                        )
                ),
                operationDescription
        )

    }

}