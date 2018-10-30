package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.AgentMapper
import java.util.*

class Agent(
        uuid: UUID?,
        val type: Type,
        counterpartyType: Counterparty.Type?,
        fullName: String?,
        shortName: String?,
        inn: String?,
        kpp: String?,
        contacts: Contacts?
) : Counterparty(
        uuid,
        counterpartyType,
        fullName,
        shortName,
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