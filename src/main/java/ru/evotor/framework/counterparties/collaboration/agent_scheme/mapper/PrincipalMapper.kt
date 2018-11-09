package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Principal
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper

internal object PrincipalMapper {

    fun read(bundle: Bundle?): Principal? =
            bundle?.let {
                Principal(
                        uuid = CounterpartyMapper.readUuid(it),
                        counterpartyType = CounterpartyMapper.readCounterpartyType(it),
                        fullName = CounterpartyMapper.readFullName(it),
                        shortName = CounterpartyMapper.readShortName(it),
                        inn = CounterpartyMapper.readInn(it) ?: return null,
                        kpp = CounterpartyMapper.readKpp(it),
                        phones = CounterpartyMapper.readPhones(it) ?: return null,
                        addresses = CounterpartyMapper.readAddresses(it)
                )
            }

}