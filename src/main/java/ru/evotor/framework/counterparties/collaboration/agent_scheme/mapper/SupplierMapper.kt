package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Supplier
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper

internal object SupplierMapper {

    fun read(bundle: Bundle?): Supplier? =
            bundle?.let {
                Supplier(
                        uuid = CounterpartyMapper.readUuid(it),
                        counterpartyType = CounterpartyMapper.readCounterpartyType(it),
                        fullName = CounterpartyMapper.readFullName(it),
                        shortName = CounterpartyMapper.readShortName(it),
                        inn = CounterpartyMapper.readInn(it),
                        kpp = CounterpartyMapper.readKpp(it),
                        contacts = CounterpartyMapper.readContacts(it)
                )
            }
}