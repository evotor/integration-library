package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Principal
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.AgentMapper
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.TransactionOperatorMapper
import ru.evotor.framework.core.safeGetInt
import ru.evotor.framework.core.safeGetList
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.receipt.position.AgentRequisites
import ru.evotor.framework.receipt.provider.ReceiptContract
import java.util.*

internal object AgentRequisitesMapper {

    private const val KEY_AGENT = "AGENT"
    private const val KEY_SUBAGENT = "SUBAGENT"
    private const val KEY_PRINCIPAL = "PRINCIPAL"
    private const val KEY_TRANSACTION_OPERATOR = "TRANSACTION_OPERATOR"
    private const val KEY_OPERATION_DESCRIPTION = "OPERATION_DESCRIPTION"

    fun create(
            agentType: Agent.Type?,
            agentPhones: List<String>?,
            subagentType: Subagent.Type?,
            subagentPhones: List<String>?,
            principalInn: String,
            principalPhones: List<String>,
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
                            agentPhones,
                            null
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
                        subagentPhones,
                        null
                )
            },
            Principal(
                    null,
                    null,
                    null,
                    null,
                    principalInn,
                    null,
                    principalPhones,
                    null
            ),
            TransactionOperatorMapper.convertToNull(
                    TransactionOperator(
                            null,
                            null,
                            transactionOperatorName,
                            null,
                            transactionOperatorInn,
                            null,
                            transactionOperatorPhones,
                            transactionOperatorAddress?.let { listOf(it) }
                    )
            ),
            operationDescription
    )

    fun read(bundle: Bundle?): AgentRequisites? = bundle?.let {
        AgentRequisites(
                agent = Agent.from(it.getBundle(KEY_AGENT)),
                subagent = Subagent.from(it.getBundle(KEY_SUBAGENT)),
                principal = Principal.from(it.getBundle(KEY_PRINCIPAL)) ?: return null,
                transactionOperator = TransactionOperator.from(it.getBundle(KEY_TRANSACTION_OPERATOR)),
                operationDescription = it.getString(KEY_OPERATION_DESCRIPTION)
        )
    }

    fun read(cursor: Cursor): AgentRequisites? {
        return AgentRequisites(
                agent = readAgent(cursor),
                subagent = readSubagent(cursor),
                principal = readPrincipal(cursor)
                        ?: throw IntegrationLibraryMappingException(AgentRequisites::class.java, AgentRequisites::principal),
                transactionOperator = readTransactionOperator(cursor),
                operationDescription = cursor.safeGetString(ReceiptContract.Position.OPERATION_DESCRIPTION)
        )
    }

    private fun readAgent(cursor: Cursor): Agent? =
            if (cursor.safeGetString(ReceiptContract.Position.AGENT_IS_NULL)?.toBoolean() != false)
                null
            else
                Agent(
                        uuid = cursor.safeGetString(ReceiptContract.Position.AGENT_UUID)?.let { UUID.fromString(it) },
                        type = cursor.safeGetInt(ReceiptContract.Position.AGENT_TYPE)?.let { Agent.Type.values()[it] },
                        counterpartyType = cursor.safeGetInt(ReceiptContract.Position.AGENT_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
                        fullName = cursor.safeGetString(ReceiptContract.Position.AGENT_FULL_NAME),
                        shortName = cursor.safeGetString(ReceiptContract.Position.AGENT_SHORT_NAME),
                        inn = cursor.safeGetString(ReceiptContract.Position.AGENT_INN),
                        kpp = cursor.safeGetString(ReceiptContract.Position.AGENT_KPP),
                        phones = cursor.safeGetList(ReceiptContract.Position.AGENT_PHONES),
                        addresses = cursor.safeGetList(ReceiptContract.Position.AGENT_ADDRESSES)
                )

    private fun readSubagent(cursor: Cursor): Subagent? {
        return Subagent(
                uuid = cursor.safeGetString(ReceiptContract.Position.SUBAGENT_UUID)?.let { UUID.fromString(it) },
                type = Subagent.Type.values()[cursor.safeGetInt(ReceiptContract.Position.SUBAGENT_TYPE)
                        ?: return null],
                counterpartyType = cursor.safeGetInt(ReceiptContract.Position.SUBAGENT_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
                fullName = cursor.safeGetString(ReceiptContract.Position.SUBAGENT_FULL_NAME),
                shortName = cursor.safeGetString(ReceiptContract.Position.SUBAGENT_SHORT_NAME),
                inn = cursor.safeGetString(ReceiptContract.Position.SUBAGENT_INN),
                kpp = cursor.safeGetString(ReceiptContract.Position.SUBAGENT_KPP),
                phones = cursor.safeGetList(ReceiptContract.Position.SUBAGENT_PHONES),
                addresses = cursor.safeGetList(ReceiptContract.Position.SUBAGENT_ADDRESSES)
        )
    }

    private fun readPrincipal(cursor: Cursor): Principal? {
        return Principal(
                uuid = cursor.safeGetString(ReceiptContract.Position.PRINCIPAL_UUID)?.let { UUID.fromString(it) },
                counterpartyType = cursor.safeGetInt(ReceiptContract.Position.PRINCIPAL_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
                fullName = cursor.safeGetString(ReceiptContract.Position.PRINCIPAL_FULL_NAME),
                shortName = cursor.safeGetString(ReceiptContract.Position.PRINCIPAL_SHORT_NAME),
                inn = cursor.safeGetString(ReceiptContract.Position.PRINCIPAL_INN)
                        ?: return null,
                kpp = cursor.safeGetString(ReceiptContract.Position.PRINCIPAL_KPP),
                phones = cursor.safeGetList(ReceiptContract.Position.PRINCIPAL_PHONES)
                        ?: return null,
                addresses = cursor.safeGetList(ReceiptContract.Position.PRINCIPAL_ADDRESSES)
        )
    }

    private fun readTransactionOperator(cursor: Cursor): TransactionOperator? =
            if (cursor.safeGetString(ReceiptContract.Position.TRANSACTION_OPERATOR_IS_NULL)?.toBoolean() != false)
                null
            else
                TransactionOperator(
                        uuid = cursor.safeGetString(ReceiptContract.Position.TRANSACTION_OPERATOR_UUID)?.let { UUID.fromString(it) },
                        counterpartyType = cursor.safeGetInt(ReceiptContract.Position.TRANSACTION_OPERATOR_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
                        fullName = cursor.safeGetString(ReceiptContract.Position.TRANSACTION_OPERATOR_FULL_NAME),
                        shortName = cursor.safeGetString(ReceiptContract.Position.TRANSACTION_OPERATOR_SHORT_NAME),
                        inn = cursor.safeGetString(ReceiptContract.Position.TRANSACTION_OPERATOR_INN),
                        kpp = cursor.safeGetString(ReceiptContract.Position.TRANSACTION_OPERATOR_KPP),
                        phones = cursor.safeGetList(ReceiptContract.Position.TRANSACTION_OPERATOR_PHONES),
                        addresses = cursor.safeGetList(ReceiptContract.Position.TRANSACTION_OPERATOR_ADDRESSES)
                )

    fun write(agentRequisites: AgentRequisites) = Bundle().apply {
        this.putBundle(KEY_AGENT, agentRequisites.agent?.toBundle())
        this.putBundle(KEY_SUBAGENT, agentRequisites.subagent?.toBundle())
        this.putBundle(KEY_PRINCIPAL, agentRequisites.principal.toBundle())
        this.putBundle(KEY_TRANSACTION_OPERATOR, agentRequisites.transactionOperator?.toBundle())
        this.putString(KEY_OPERATION_DESCRIPTION, agentRequisites.operationDescription)
    }

}