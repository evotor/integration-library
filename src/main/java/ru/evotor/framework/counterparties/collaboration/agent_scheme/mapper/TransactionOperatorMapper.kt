package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper

internal object TransactionOperatorMapper {

    fun read(bundle: Bundle?): TransactionOperator? =
            CounterpartyMapper.read(bundle)?.let {
                TransactionOperator(
                        uuid = it.uuid,
                        counterpartyType = it.counterpartyType,
                        fullName = it.fullName,
                        shortName = it.shortName,
                        inn = it.inn,
                        kpp = it.kpp,
                        contacts = it.contacts
                )
            }

    fun convertToNull(transactionOperator: TransactionOperator): TransactionOperator? =
            CounterpartyMapper.convertToNull(transactionOperator)

}