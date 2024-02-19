package ru.evotor.framework.receipt.position

import org.junit.Assert
import org.junit.Test
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Principal
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator

@Suppress("SameParameterValue")
class AgentRequisitesTest {

    @Test
    fun createForAgent() = createForAgent("1234", listOf("89000000000", "89000000001"), "name")

    private fun createForAgent(
            principalInn: String,
            principalPhones: List<String>,
            principalName: String
    ) = Assert.assertEquals(
            AgentRequisites.createForAgent(principalInn, principalPhones, principalName),
            AgentRequisites(
                    Agent(null, Agent.Type.AGENT, null, null, null, null, null, null, null),
                    null,
                    Principal(null, null, null, principalName, principalInn, null, principalPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForCommissioner() = createForCommissioner("1234", listOf("89000000000", "89000000001"), "name")

    private fun createForCommissioner(
            principalInn: String,
            principalPhones: List<String>,
            principalName: String
    ) = Assert.assertEquals(
            AgentRequisites.createForCommissioner(principalInn, principalPhones, principalName),
            AgentRequisites(
                    Agent(null, Agent.Type.COMMISSIONER, null, null, null, null, null, null, null),
                    null,
                    Principal(null, null, null, principalName, principalInn, null, principalPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForAttorneyInFact() = createForAttorneyInFact("1234", listOf("89000000000", "89000000001"), "name")

    private fun createForAttorneyInFact(
            principalInn: String,
            principalPhones: List<String>,
            principalName: String
    ) = Assert.assertEquals(
            AgentRequisites.createForAttorneyInFact(principalInn, principalPhones, principalName),
            AgentRequisites(
                    Agent(null, Agent.Type.ATTORNEY_IN_FACT, null, null, null, null, null, null, null),
                    null,
                    Principal(null, null, null, principalName, principalInn, null, principalPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForPaymentAgent() = createForPaymentAgent(listOf("+70000000000"), "1234", listOf("89000000000", "89000000001"), "name", )

    private fun createForPaymentAgent(
            agentPhones: List<String>,
            principalInn: String,
            principalPhones: List<String>,
            principalName: String
    ) = Assert.assertEquals(
            AgentRequisites.createForPaymentAgent(agentPhones, principalInn, principalPhones, principalName),
            AgentRequisites(
                    Agent(null, Agent.Type.PAYMENT_AGENT, null, null, null, null, null, agentPhones, null),
                    null,
                    Principal(null, null, null, principalName, principalInn, null, principalPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForPaymentSubagent() = createForPaymentSubagent(listOf("+70000000000"), listOf("+77776668888"), "1234", listOf("89000000000", "89000000001"), "name")

    private fun createForPaymentSubagent(
            agentPhones: List<String>,
            subagentPhones: List<String>,
            principalInn: String,
            principalPhones: List<String>,
            principalName: String
    ) = Assert.assertEquals(
            AgentRequisites.createForPaymentSubagent(agentPhones, subagentPhones, principalInn, principalPhones, principalName),
            AgentRequisites(
                    Agent(null, null, null, null, null, null, null, agentPhones, null),
                    Subagent(null, Subagent.Type.PAYMENT_SUBAGENT, null, null, null, null, null, subagentPhones, null),
                    Principal(null, null, null, principalName, principalInn, null, principalPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForBankPaymentAgent() = createForBankPaymentAgent(listOf("+70000000000"), "1234", listOf("89000000000", "89000000001"), "name", "Страховое агентство", "4321", listOf("89859998070"), "улица Пушкина, дом Калатушкина, офис 420", "Страхование на выгодных условиях")

    private fun createForBankPaymentAgent(
            agentPhones: List<String>,
            principalInn: String,
            principalPhones: List<String>,
            principalName: String,
            transactionOperatorName: String,
            transactionOperatorInn: String,
            transactionOperatorPhones: List<String>,
            transactionOperatorAddress: String,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForBankPaymentAgent(agentPhones, principalInn, principalPhones, principalName, transactionOperatorName, transactionOperatorInn, transactionOperatorPhones, transactionOperatorAddress, operationDescription),
            AgentRequisites(
                    Agent(null, Agent.Type.BANK_PAYMENT_AGENT, null, null, null, null, null, agentPhones, null),
                    null,
                    Principal(null, null, null, principalName, principalInn, null, principalPhones, null),
                    TransactionOperator(null, null, transactionOperatorName, null, transactionOperatorInn, null, transactionOperatorPhones, listOf(transactionOperatorAddress)),
                    operationDescription
            )
    )

    @Test
    fun createForBankPaymentSubagent() = createForBankPaymentSubagent(listOf("+77776668888"), "1234", listOf("89000000000", "89000000001"), "name", "Страховое агентство", "4321", listOf("89859998070"), "улица Пушкина, дом Калатушкина, офис 420", "Страхование на выгодных условиях")

    private fun createForBankPaymentSubagent(
            subagentPhones: List<String>,
            principalInn: String,
            principalPhones: List<String>,
            principalName: String,
            transactionOperatorName: String,
            transactionOperatorInn: String,
            transactionOperatorPhones: List<String>,
            transactionOperatorAddress: String,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForBankPaymentSubagent(subagentPhones, principalInn, principalPhones, principalName, transactionOperatorName, transactionOperatorInn, transactionOperatorPhones, transactionOperatorAddress, operationDescription),
            AgentRequisites(
                    null,
                    Subagent(null, Subagent.Type.BANK_PAYMENT_SUBAGENT, null, null, null, null, null, subagentPhones, null),
                    Principal(null, null, null, principalName, principalInn, null, principalPhones, null),
                    TransactionOperator(null, null, transactionOperatorName, null, transactionOperatorInn, null, transactionOperatorPhones, listOf(transactionOperatorAddress)),
                    operationDescription
            )
    )


}
