package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper

internal object SubagentMapper {

    private const val KEY_TYPE = "TYPE"

    fun read(bundle: Bundle?): Subagent? =
            bundle?.let {
                Subagent(
                        uuid = CounterpartyMapper.readUuid(it),
                        type = Subagent.Type.values()[it.getInt(KEY_TYPE)],
                        counterpartyType = CounterpartyMapper.readCounterpartyType(it),
                        fullName = CounterpartyMapper.readFullName(it),
                        shortName = CounterpartyMapper.readShortName(it),
                        inn = CounterpartyMapper.readInn(it),
                        kpp = CounterpartyMapper.readKpp(it),
                        phones = CounterpartyMapper.readPhones(it),
                        addresses = CounterpartyMapper.readAddresses(it)
                )
            }

    fun write(subagent: Subagent, bundle: Bundle) = bundle.apply {
        this.putInt(KEY_TYPE, subagent.type.ordinal)
    }

}