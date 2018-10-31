package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper

internal object AgentMapper {

    private const val KEY_TYPE = "TYPE"

    fun read(bundle: Bundle?): Agent? =
            CounterpartyMapper.read(bundle)?.let {
                Agent(
                        uuid = it.uuid,
                        type = Agent.Type.values()[bundle!!.getInt(KEY_TYPE)],
                        counterpartyType = it.counterpartyType,
                        fullName = it.fullName,
                        shortName = it.shortName,
                        inn = it.inn,
                        kpp = it.kpp,
                        contacts = it.contacts
                )

            }

    fun write(agent: Agent, bundle: Bundle) = bundle.apply {
        agent.type.let { this.putInt(KEY_TYPE, it.ordinal) }
    }

}