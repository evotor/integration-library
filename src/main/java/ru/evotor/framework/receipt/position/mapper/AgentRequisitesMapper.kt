package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Supplier
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
    private const val KEY_SUPPLIER = "SUPPLIER"
    private const val KEY_TRANSACTION_OPERATOR = "TRANSACTION_OPERATOR"
    private const val KEY_OPERATION_DESCRIPTION = "OPERATION_DESCRIPTION"

    fun read(bundle: Bundle?): AgentRequisites? = bundle?.let {
        AgentRequisites(
                agent = Agent.from(it.getBundle(KEY_AGENT)),
                subagent = Subagent.from(it.getBundle(KEY_SUBAGENT)),
                supplier = Supplier.from(it.getBundle(KEY_SUPPLIER)) ?: return null,
                transactionOperator = TransactionOperator.from(it.getBundle(KEY_TRANSACTION_OPERATOR)),
                operationDescription = it.getString(KEY_OPERATION_DESCRIPTION)
        )
    }

    fun read(cursor: Cursor) = AgentRequisites(
            agent = readAgent(cursor),
            subagent = readSubagent(cursor),
            supplier = readSupplier(cursor),
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
            contacts = Counterparty.Contacts(
                    phones = cursor.optList(AgentRequisitesContract.COLUMN_AGENT_PHONES),
                    addresses = cursor.optList(AgentRequisitesContract.COLUMN_AGENT_ADDRESSES)
            )
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
                contacts = Counterparty.Contacts(
                        phones = cursor.optList(AgentRequisitesContract.COLUMN_SUBAGENT_PHONES),
                        addresses = cursor.optList(AgentRequisitesContract.COLUMN_SUBAGENT_ADDRESSES)
                )
        )
    }

    private fun readSupplier(cursor: Cursor): Supplier =
            Supplier(
                    uuid = cursor.optString(AgentRequisitesContract.COLUMN_SUPPLIER_UUID)?.let { UUID.fromString(it) },
                    counterpartyType = cursor.optInt(AgentRequisitesContract.COLUMN_SUPPLIER_COUNTERPARTY_TYPE)?.let { Counterparty.Type.values()[it] },
                    fullName = cursor.optString(AgentRequisitesContract.COLUMN_SUPPLIER_FULL_NAME),
                    shortName = cursor.optString(AgentRequisitesContract.COLUMN_SUPPLIER_SHORT_NAME),
                    inn = cursor.optString(AgentRequisitesContract.COLUMN_SUPPLIER_INN),
                    kpp = cursor.optString(AgentRequisitesContract.COLUMN_SUPPLIER_KPP),
                    contacts = Counterparty.Contacts(
                            phones = cursor.optList(AgentRequisitesContract.COLUMN_SUPPLIER_PHONES),
                            addresses = cursor.optList(AgentRequisitesContract.COLUMN_SUPPLIER_ADDRESSES)
                    )
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
                            contacts = Counterparty.Contacts(
                                    phones = cursor.optList(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_PHONES),
                                    addresses = cursor.optList(AgentRequisitesContract.COLUMN_TRANSACTION_OPERATOR_ADDRESSES)
                            )
                    )
            )

    fun write(agentRequisites: AgentRequisites) = Bundle().apply {
        this.putBundle(KEY_AGENT, agentRequisites.agent?.toBundle())
        this.putBundle(KEY_SUBAGENT, agentRequisites.subagent?.toBundle())
        this.putBundle(KEY_SUPPLIER, agentRequisites.supplier.toBundle())
        this.putBundle(KEY_TRANSACTION_OPERATOR, agentRequisites.transactionOperator?.toBundle())
        this.putString(KEY_OPERATION_DESCRIPTION, agentRequisites.operationDescription)
    }

}