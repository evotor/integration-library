package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper

internal object TransactionOperatorMapper {

    fun read(bundle: Bundle?): TransactionOperator? =
            bundle?.let {
                TransactionOperator(
                        uuid = CounterpartyMapper.readUuid(it),
                        counterpartyType = CounterpartyMapper.readCounterpartyType(it),
                        fullName = CounterpartyMapper.readFullName(it),
                        shortName = CounterpartyMapper.readShortName(it),
                        inn = CounterpartyMapper.readInn(it),
                        kpp = CounterpartyMapper.readKpp(it),
                        phones = CounterpartyMapper.readPhones(it),
                        addresses = CounterpartyMapper.readAddresses(it)
                )
            }

    fun convertToNull(transactionOperator: TransactionOperator): TransactionOperator? =
            CounterpartyMapper.convertToNull(transactionOperator)

}