package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Supplier
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.AgentMapper
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.TransactionOperatorMapper
import ru.evotor.framework.receipt.position.mapper.AgentRequisitesMapper

data class AgentRequisites(
        val agent: Agent?,
        val subagent: Subagent?,
        val supplier: Supplier,
        val transactionOperator: TransactionOperator?,
        val operationDescription: String?
) : IBundlable {

    companion object {

        fun createForAgent(
                supplierInn: String,
                supplierPhones: List<String>
        ) = create(
                Agent.Type.AGENT,
                null,
                null,
                null,
                supplierInn,
                supplierPhones,
                null,
                null,
                null,
                null,
                null
        )

        fun createForCommissioner(
                supplierInn: String,
                supplierPhones: List<String>
        ) = create(
                Agent.Type.COMMISSIONER,
                null,
                null,
                null,
                supplierInn,
                supplierPhones,
                null,
                null,
                null,
                null,
                null
        )

        fun createForAttorneyInFact(
                supplierInn: String,
                supplierPhones: List<String>
        ) = create(
                Agent.Type.ATTORNEY_IN_FACT,
                null,
                null,
                null,
                supplierInn,
                supplierPhones,
                null,
                null,
                null,
                null,
                null
        )

        fun createForPaymentAgent(
                agentPhones: List<String>,
                supplierInn: String,
                supplierPhones: List<String>,
                operationDescription: String
        ) = create(
                Agent.Type.PAYMENT_AGENT,
                agentPhones,
                null,
                null,
                supplierInn,
                supplierPhones,
                null,
                null,
                null,
                null,
                operationDescription
        )

        fun createForPaymentSubagent(
                agentPhones: List<String>,
                subagentPhones: List<String>,
                supplierInn: String,
                supplierPhones: List<String>,
                operationDescription: String
        ) = create(
                null,
                agentPhones,
                Subagent.Type.PAYMENT_SUBAGENT,
                subagentPhones,
                supplierInn,
                supplierPhones,
                null,
                null,
                null,
                null,
                operationDescription
        )

        fun createForBankPaymentAgent(
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
                null,
                null,
                supplierInn,
                supplierPhones,
                transactionOperatorName,
                transactionOperatorInn,
                transactionOperatorPhones,
                transactionOperatorAddress,
                operationDescription
        )

        fun createForBankPaymentSubagent(
                agentPhones: List<String>,
                subagentPhones: List<String>,
                supplierInn: String,
                supplierPhones: List<String>,
                transactionOperatorName: String,
                transactionOperatorInn: String,
                transactionOperatorPhones: List<String>,
                transactionOperatorAddress: String,
                operationDescription: String
        ) = create(
                null,
                agentPhones,
                Subagent.Type.BANK_PAYMENT_SUBAGENT,
                subagentPhones,
                supplierInn,
                supplierPhones,
                transactionOperatorName,
                transactionOperatorInn,
                transactionOperatorPhones,
                transactionOperatorAddress,
                operationDescription
        )

        private fun create(
                agentType: Agent.Type?,
                agentPhones: List<String>?,
                subagentType: Subagent.Type?,
                subagentPhones: List<String>?,
                supplierInn: String,
                supplierPhones: List<String>,
                transactionOperatorName: String?,
                transactionOperatorInn: String?,
                transactionOperatorPhones: List<String>?,
                transactionOperatorAddress: String?,
                operationDescription: String?
        ) = AgentRequisites(
                AgentMapper.convertToNull(
                        Agent(
                                null,
                                agentType,
                                null,
                                null,
                                null,
                                null,
                                null,
                                Counterparty.Contacts(agentPhones, null)
                        )
                ),
                subagentType?.let {
                    Subagent(
                            null,
                            it,
                            null,
                            null,
                            null,
                            null,
                            null,
                            Counterparty.Contacts(subagentPhones, null)
                    )
                },
                Supplier(
                        null,
                        null,
                        null,
                        null,
                        supplierInn,
                        null,
                        Counterparty.Contacts(supplierPhones, null)
                ),
                TransactionOperatorMapper.convertToNull(
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
                        )
                ),
                operationDescription
        )

        fun from(bundle: Bundle?): AgentRequisites? = AgentRequisitesMapper.read(bundle)

    }

    override fun toBundle(): Bundle = AgentRequisitesMapper.write(this)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AgentRequisites) return false

        if (agent != other.agent) return false
        if (supplier != other.supplier) return false
        if (transactionOperator != other.transactionOperator) return false
        if (operationDescription != other.operationDescription) return false

        return true
    }

    override fun hashCode(): Int {
        var result = agent.hashCode()
        result = 31 * result + supplier.hashCode()
        result = 31 * result + (transactionOperator?.hashCode() ?: 0)
        result = 31 * result + (operationDescription?.hashCode() ?: 0)
        return result
    }

}