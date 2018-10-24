package ru.evotor.framework.receipt

import ru.evotor.framework.organisation.agent.Agent
import ru.evotor.framework.organisation.agent.TransactionOperator
import ru.evotor.framework.organisation.agent.Supplier

data class AgentRequisites(
        val supplier: Supplier,
        val agent: Agent,
        val transactionOperator: TransactionOperator?,
        val operationDescription: String?
)