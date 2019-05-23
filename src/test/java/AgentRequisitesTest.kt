import junit.framework.Assert
import org.junit.Test
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Principal
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.receipt.position.AgentRequisites

class AgentRequisitesTest {

    @Test
    fun createForAgent() = createForAgent("1234", listOf("89000000000", "89000000001"))

    private fun createForAgent(
            principalInn: String,
            principalPhones: List<String>
    ) = Assert.assertEquals(
            AgentRequisites.createForAgent(principalInn, principalPhones),
            AgentRequisites(
                    Agent(null, Agent.Type.AGENT, null, null, null, null, null, null, null),
                    null,
                    Principal(null, null, null, null, principalInn, null, principalPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForCommissioner() = createForCommissioner("1234", listOf("89000000000", "89000000001"))

    private fun createForCommissioner(
            principalInn: String,
            principalPhones: List<String>
    ) = Assert.assertEquals(
            AgentRequisites.createForCommissioner(principalInn, principalPhones),
            AgentRequisites(
                    Agent(null, Agent.Type.COMMISSIONER, null, null, null, null, null, null, null),
                    null,
                    Principal(null, null, null, null, principalInn, null, principalPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForAttorneyInFact() = createForAttorneyInFact("1234", listOf("89000000000", "89000000001"))

    private fun createForAttorneyInFact(
            principalInn: String,
            principalPhones: List<String>
    ) = Assert.assertEquals(
            AgentRequisites.createForAttorneyInFact(principalInn, principalPhones),
            AgentRequisites(
                    Agent(null, Agent.Type.ATTORNEY_IN_FACT, null, null, null, null, null, null, null),
                    null,
                    Principal(null, null, null, null, principalInn, null, principalPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForPaymentAgent() = createForPaymentAgent(listOf("+70000000000"), "1234", listOf("89000000000", "89000000001"), "Страхование на выгодных условиях")

    private fun createForPaymentAgent(
            agentPhones: List<String>,
            principalInn: String,
            principalPhones: List<String>,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForPaymentAgent(agentPhones, principalInn, principalPhones, operationDescription),
            AgentRequisites(
                    Agent(null, Agent.Type.PAYMENT_AGENT, null, null, null, null, null, agentPhones, null),
                    null,
                    Principal(null, null, null, null, principalInn, null, principalPhones, null),
                    null,
                    operationDescription
            )
    )

    @Test
    fun createForPaymentSubagent() = createForPaymentSubagent(listOf("+70000000000"), listOf("+77776668888"), "1234", listOf("89000000000", "89000000001"), "Страхование на выгодных условиях")

    private fun createForPaymentSubagent(
            agentPhones: List<String>,
            subagentPhones: List<String>,
            principalInn: String,
            principalPhones: List<String>,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForPaymentSubagent(agentPhones, subagentPhones, principalInn, principalPhones, operationDescription),
            AgentRequisites(
                    Agent(null, null, null, null, null, null, null, agentPhones, null),
                    Subagent(null, Subagent.Type.PAYMENT_SUBAGENT, null, null, null, null, null, subagentPhones, null),
                    Principal(null, null, null, null, principalInn, null, principalPhones, null),
                    null,
                    operationDescription
            )
    )

    @Test
    fun createForBankPaymentAgent() = createForBankPaymentAgent(listOf("+70000000000"), "1234", listOf("89000000000", "89000000001"), "Страховое агентство", "4321", listOf("89859998070"), "улица Пушкина, дом Калатушкина, офис 420", "Страхование на выгодных условиях")

    private fun createForBankPaymentAgent(
            agentPhones: List<String>,
            principalInn: String,
            principalPhones: List<String>,
            transactionOperatorName: String,
            transactionOperatorInn: String,
            transactionOperatorPhones: List<String>,
            transactionOperatorAddress: String,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForBankPaymentAgent(agentPhones, principalInn, principalPhones, transactionOperatorName, transactionOperatorInn, transactionOperatorPhones, transactionOperatorAddress, operationDescription),
            AgentRequisites(
                    Agent(null, Agent.Type.BANK_PAYMENT_AGENT, null, null, null, null, null, agentPhones, null),
                    null,
                    Principal(null, null, null, null, principalInn, null, principalPhones, null),
                    TransactionOperator(null, null, transactionOperatorName, null, transactionOperatorInn, null, transactionOperatorPhones, listOf(transactionOperatorAddress)),
                    operationDescription
            )
    )

    @Test
    fun createForBankPaymentSubagent() = createForBankPaymentSubagent(listOf("+70000000000"), listOf("+77776668888"), "1234", listOf("89000000000", "89000000001"), "Страховое агентство", "4321", listOf("89859998070"), "улица Пушкина, дом Калатушкина, офис 420", "Страхование на выгодных условиях")

    private fun createForBankPaymentSubagent(
            agentPhones: List<String>,
            subagentPhones: List<String>,
            principalInn: String,
            principalPhones: List<String>,
            transactionOperatorName: String,
            transactionOperatorInn: String,
            transactionOperatorPhones: List<String>,
            transactionOperatorAddress: String,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForBankPaymentSubagent(agentPhones, subagentPhones, principalInn, principalPhones, transactionOperatorName, transactionOperatorInn, transactionOperatorPhones, transactionOperatorAddress, operationDescription),
            AgentRequisites(
                    Agent(null, null, null, null, null, null, null, agentPhones, null),
                    Subagent(null, Subagent.Type.BANK_PAYMENT_SUBAGENT, null, null, null, null, null, subagentPhones, null),
                    Principal(null, null, null, null, principalInn, null, principalPhones, null),
                    TransactionOperator(null, null, transactionOperatorName, null, transactionOperatorInn, null, transactionOperatorPhones, listOf(transactionOperatorAddress)),
                    operationDescription
            )
    )


}