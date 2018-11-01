package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper

object SubagentMapper {

    private const val KEY_TYPE = "TYPE"

    fun read(bundle: Bundle?): Subagent? =
            CounterpartyMapper.read(bundle)?.let {
                Subagent(
                        uuid = it.uuid,
                        type = Subagent.Type.values()[bundle!!.getInt(KEY_TYPE)],
                        counterpartyType = it.counterpartyType,
                        fullName = it.fullName,
                        shortName = it.shortName,
                        inn = it.inn,
                        kpp = it.kpp,
                        contacts = it.contacts
                )

            }

    fun write(agent: Subagent, bundle: Bundle) = bundle.apply {
        agent.type.let { this.putInt(KEY_TYPE, it.ordinal) }
    }

}