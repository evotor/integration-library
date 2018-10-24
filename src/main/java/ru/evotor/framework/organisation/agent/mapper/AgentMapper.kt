package ru.evotor.framework.organisation.agent.mapper

import android.os.Bundle
import ru.evotor.framework.organisation.agent.Agent
import ru.evotor.framework.organisation.mapper.OrganisationMapper
import java.lang.Exception

internal object AgentMapper {

    private const val KEY_TYPE = "type"

    fun read(bundle: Bundle?) =
            OrganisationMapper.read(bundle)?.let {
                try {
                    Agent(
                            type = Agent.Type.values()[bundle!!.getInt(KEY_TYPE)],
                            uuid = it.uuid,
                            fullName = it.fullName,
                            shortName = it.shortName,
                            inn = it.inn,
                            kpp = it.kpp,
                            contacts = it.contacts
                    )
                } catch (e: Exception) {
                    null
                }
            }

    fun write(agent: Agent, bundle: Bundle) = bundle.apply {
        agent.type?.let { this.putInt(KEY_TYPE, it.ordinal) }
    }

}