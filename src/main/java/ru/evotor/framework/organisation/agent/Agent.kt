package ru.evotor.framework.organisation.agent

import android.os.Bundle
import ru.evotor.framework.organisation.Organisation
import ru.evotor.framework.organisation.agent.mapper.AgentMapper
import java.util.*

class Agent(
        val type: Type?,
        uuid: UUID?,
        shortName: String?,
        fullName: String?,
        inn: String?,
        kpp: String?,
        contacts: Contacts?
) : Organisation(
        uuid,
        shortName,
        fullName,
        inn,
        kpp,
        contacts
) {

    enum class Type {
        AGENT,
        COMMISSIONER,
        ATTORNEY_IN_FACT,
        PAYMENT_AGENT,
        PAYMENT_SUBAGENT,
        BANK_PAYMENT_AGENT,
        BANK_PAYMENT_SUBAGENT,
    }

    companion object {
        fun from(bundle: Bundle?): Agent? = AgentMapper.read(bundle)
    }

    override fun toBundle() = AgentMapper.write(this, super.toBundle())

}