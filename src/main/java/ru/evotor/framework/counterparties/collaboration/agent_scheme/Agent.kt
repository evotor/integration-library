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

    override fun toBundle(): Bundle = AgentMapper.write(this, super.toBundle())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Agent) return false
        if (!super.equals(other)) return false

        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

}