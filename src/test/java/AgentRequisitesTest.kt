import junit.framework.Assert
import org.junit.Test
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Supplier
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.receipt.position.AgentRequisites

class AgentRequisitesTest {

    @Test
    fun createForAgent() = createForAgent("1234", listOf("88005553535", "89876543210"))

    private fun createForAgent(
            supplierInn: String,
            supplierPhones: List<String>
    ) = Assert.assertEquals(
            AgentRequisites.createForAgent(supplierInn, supplierPhones),
            AgentRequisites(
                    Agent(null, Agent.Type.AGENT, null, null, null, null, null, null, null),
                    null,
                    Supplier(null, null, null, null, supplierInn, null, supplierPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForCommissioner() = createForCommissioner("1234", listOf("88005553535", "89876543210"))

    private fun createForCommissioner(
            supplierInn: String,
            supplierPhones: List<String>
    ) = Assert.assertEquals(
            AgentRequisites.createForCommissioner(supplierInn, supplierPhones),
            AgentRequisites(
                    Agent(null, Agent.Type.COMMISSIONER, null, null, null, null, null, null, null),
                    null,
                    Supplier(null, null, null, null, supplierInn, null, supplierPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForAttorneyInFact() = createForAttorneyInFact("1234", listOf("88005553535", "89876543210"))

    private fun createForAttorneyInFact(
            supplierInn: String,
            supplierPhones: List<String>
    ) = Assert.assertEquals(
            AgentRequisites.createForAttorneyInFact(supplierInn, supplierPhones),
            AgentRequisites(
                    Agent(null, Agent.Type.ATTORNEY_IN_FACT, null, null, null, null, null, null, null),
                    null,
                    Supplier(null, null, null, null, supplierInn, null, supplierPhones, null),
                    null,
                    null
            )
    )

    @Test
    fun createForPaymentAgent() = createForPaymentAgent(listOf("+70005226789"), "1234", listOf("88005553535", "89876543210"), "Продал Васе разноцветное дилдо")

    private fun createForPaymentAgent(
            agentPhones: List<String>,
            supplierInn: String,
            supplierPhones: List<String>,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForPaymentAgent(agentPhones, supplierInn, supplierPhones, operationDescription),
            AgentRequisites(
                    Agent(null, Agent.Type.PAYMENT_AGENT, null, null, null, null, null, agentPhones, null),
                    null,
                    Supplier(null, null, null, null, supplierInn, null, supplierPhones, null),
                    null,
                    operationDescription
            )
    )

    @Test
    fun createForPaymentSubagent() = createForPaymentSubagent(listOf("+70005226789"), listOf("+77776668888"), "1234", listOf("88005553535", "89876543210"), "Продал Васе разноцветное дилдо")

    private fun createForPaymentSubagent(
            agentPhones: List<String>,
            subagentPhones: List<String>,
            supplierInn: String,
            supplierPhones: List<String>,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForPaymentSubagent(agentPhones, subagentPhones, supplierInn, supplierPhones, operationDescription),
            AgentRequisites(
                    Agent(null, null, null, null, null, null, null, agentPhones, null),
                    Subagent(null, Subagent.Type.PAYMENT_SUBAGENT, null, null, null, null, null, subagentPhones, null),
                    Supplier(null, null, null, null, supplierInn, null, supplierPhones, null),
                    null,
                    operationDescription
            )
    )

    @Test
    fun createForBankPaymentAgent() = createForBankPaymentAgent(listOf("+70005226789"), "1234", listOf("88005553535", "89876543210"), "Кредитное бюро \"Фраерок\"", "4321", listOf("89859998070"), "улица Пушкина, дом Калатушкина, офис 420", "Продал Васе разноцветное дилдо")

    private fun createForBankPaymentAgent(
            agentPhones: List<String>,
            supplierInn: String,
            supplierPhones: List<String>,
            transactionOperatorName: String,
            transactionOperatorInn: String,
            transactionOperatorPhones: List<String>,
            transactionOperatorAddress: String,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForBankPaymentAgent(agentPhones, supplierInn, supplierPhones, transactionOperatorName, transactionOperatorInn, transactionOperatorPhones, transactionOperatorAddress, operationDescription),
            AgentRequisites(
                    Agent(null, Agent.Type.BANK_PAYMENT_AGENT, null, null, null, null, null, agentPhones, null),
                    null,
                    Supplier(null, null, null, null, supplierInn, null, supplierPhones, null),
                    TransactionOperator(null, null, transactionOperatorName, null, transactionOperatorInn, null, transactionOperatorPhones, listOf(transactionOperatorAddress)),
                    operationDescription
            )
    )

    @Test
    fun createForBankPaymentSubagent() = createForBankPaymentSubagent(listOf("+70005226789"), listOf("+77776668888"), "1234", listOf("88005553535", "89876543210"), "Кредитное бюро \"Фраерок\"", "4321", listOf("89859998070"), "улица Пушкина, дом Калатушкина, офис 420", "Продал Васе разноцветное дилдо")

    private fun createForBankPaymentSubagent(
            agentPhones: List<String>,
            subagentPhones: List<String>,
            supplierInn: String,
            supplierPhones: List<String>,
            transactionOperatorName: String,
            transactionOperatorInn: String,
            transactionOperatorPhones: List<String>,
            transactionOperatorAddress: String,
            operationDescription: String
    ) = Assert.assertEquals(
            AgentRequisites.createForBankPaymentSubagent(agentPhones, subagentPhones, supplierInn, supplierPhones, transactionOperatorName, transactionOperatorInn, transactionOperatorPhones, transactionOperatorAddress, operationDescription),
            AgentRequisites(
                    Agent(null,null, null, null, null, null, null, agentPhones, null),
                    Subagent(null, Subagent.Type.BANK_PAYMENT_SUBAGENT, null, null, null, null, null, subagentPhones, null),
                    Supplier(null, null, null, null, supplierInn, null, supplierPhones, null),
                    TransactionOperator(null, null, transactionOperatorName, null, transactionOperatorInn, null, transactionOperatorPhones, listOf(transactionOperatorAddress)),
                    operationDescription
            )
    )


}