package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Principal
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.TransactionOperatorMapper
import ru.evotor.framework.optInt
import ru.evotor.framework.optList
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.position.AgentRequisites
import ru.evotor.framework.receipt.position.provider.AgentRequisitesContract
import java.util.*

internal object AgentRequisitesMapper {

    private const val KEY_AGENT = "AGENT"
    private const val KEY_SUBAGENT = "SUBAGENT"
    private const val KEY_PRINCIPAL = "PRINCIPAL"
    private const val KEY_TRANSACTION_OPERATOR = "TRANSACTION_OPERATOR"
    private const val KEY_OPERATION_DESCRIPTION = "OPERATION_DESCRIPTION"

    fun read(bundle: Bundle?): AgentRequisites? = bundle?.let {
        AgentRequisites(
                agent = Agent.from(it.getBundle(KEY_AGENT)),
                subagent = Subagent.from(it.getBundle(KEY_SUBAGENT)),
                principal = Principal.from(it.getBundle(KEY_PRINCIPAL)) ?: return null,
                transactionOperator = TransactionOperator.from(it.getBundle(KEY_TRANSACTION_OPERATOR)),
                operationDescription = it.getString(KEY_OPERATION_DESCRIPTION)
        )
    }

    fun read(cursor: Cursor) = AgentRequisites(
            agent = readAgent(cursor),
            subagent = readSubagent(cursor),
            principal = readPrincipal(cursor),
            transactionOperator = readTransactionOperator(cursor),
            operationDescription = cursor.optString(AgentRequisitesContract.COLUMN_OPERATION_DESCRIPTION)
    )

    private fun readAgent(cursor: Cursor) = Agent(
            uuid = cursor.optString(AgentRequisitesContract.COLUMN_AGENT_UUID)?.let { UUID.fromString(it) },
            type = cursor.optInt(AgentRequisitesContract.COLUMN_AGENT_TYPE)?.let { Agent.Type.values()[it] },
            counterpartyType = cursor.optInt(AgentRequisitesContract.COLUMN_AGENT_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
            fullName = cursor.optString(AgentRequisitesContract.COLUMN_AGENT_FULL_NAME),
            shortName = cursor.optString(AgentRequisitesContract.COLUMN_AGENT_SHORT_NAME),
            inn = cursor.optString(AgentRequisitesContract.COLUMN_AGENT_INN),
            kpp = cursor.optString(AgentRequisitesContract.COLUMN_AGENT_KPP),
            phones = cursor.optList(AgentRequisitesContract.COLUMN_AGENT_PHONES),
            addresses = cursor.optList(AgentRequisitesContract.COLUMN_AGENT_ADDRESSES)
    )

    private fun readSubagent(cursor: Cursor): Subagent? {
        return Subagent(
                uuid = cursor.optString(AgentRequisitesContract.COLUMN_SUBAGENT_UUID)?.let { UUID.fromString(it) },
                type = Subagent.Type.values()[cursor.optInt(AgentRequisitesContract.COLUMN_SUBAGENT_TYPE)
                        ?: return null],
                counterpartyType = cursor.optInt(AgentRequisitesContract.COLUMN_SUBAGENT_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
                fullName = cursor.optString(AgentRequisitesContract.COLUMN_SUBAGENT_FULL_NAME),
                shortName = cursor.optString(AgentRequisitesContract.COLUMN_SUBAGENT_SHORT_NAME),
                inn = cursor.optString(AgentRequisitesContract.COLUMN_SUBAGENT_INN),
                kpp = cursor.optString(AgentRequisitesContract.COLUMN_SUBAGENT_KPP),
                phones = cursor.optList(AgentRequisitesContract.COLUMN_SUBAGENT_PHONES),
                addresses = cursor.optList(AgentRequisitesContract.COLUMN_SUBAGENT_ADDRESSES)
        )
    }

    private fun readPrincipal(cursor: Cursor): Principal =
            Principal(
                    uuid = cursor.optString(AgentRequisitesContract.COLUMN_PRINCIPAL_UUID)?.let { UUID.fromString(it) },
                    counterpartyType = cursor.optInt(AgentRequisitesContract.COLUMN_PRINCIPAL_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
                    fullName = cursor.optString(AgentRequisitesContract.COLUMN_PRINCIPAL_FULL_NAME),
                    shortName = cursor.optString(AgentRequisitesContract.COLUMN_PRINCIPAL_SHORT_NAME),
                    inn = cursor.optString(AgentRequisitesContract.COLUMN_PRINCIPAL_INN),
                    kpp = cursor.optString(AgentRequisitesContract.COLUMN_PRINCIPAL_KPP),
                    phones = cursor.optList(AgentRequisitesContract.COLUMN_PRINCIPAL_PHONES),
                    addresses = cursor.optList(AgentRequisitesContract.COLUMN_PRINCIPAL_ADDRESSES)
            )

    private fun readTransactionOperator(cursor: Cursor): TransactionOperator? =
            TransactionOperatorMapper.convertToNull(
                    TransactionOperator(
                            uuid = cursor.optString(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_UUID)?.let { UUID.fromString(it) },
                            counterpartyType = cursor.optInt(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
                            fullName = cursor.optString(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_FULL_NAME),
                            shortName = cursor.optString(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_SHORT_NAME),
                            inn = cursor.optString(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_INN),
                            kpp = cursor.optString(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_KPP),
                            phones = cursor.optList(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_PHONES),
                            addresses = cursor.optList(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_ADDRESSES)
                    )
            )

    fun write(agentRequisites: AgentRequisites) = Bundle().apply {
        this.putBundle(KEY_AGENT, agentRequisites.agent?.toBundle())
        this.putBundle(KEY_SUBAGENT, agentRequisites.subagent?.toBundle())
        this.putBundle(KEY_PRINCIPAL, agentRequisites.principal.toBundle())
        this.putBundle(KEY_TRANSACTION_OPERATOR, agentRequisites.transactionOperator?.toBundle())
        this.putString(KEY_OPERATION_DESCRIPTION, agentRequisites.operationDescription)
    }

}