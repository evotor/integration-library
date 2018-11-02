package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.core.FfdTag
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.AgentMapper
import java.util.*

/**
 * Агент
 */
class Agent(
        uuid: UUID?,

        @FfdTag(1057, 1222)
        val type: Type?,

        counterpartyType: Counterparty.Type?,

        fullName: String?,

        shortName: String?,

        inn: String?,

        kpp: String?,

        @FfdTag(1073, 1074)
        phones: List<String>?,

        addresses: List<String>?
) : Counterparty(
        uuid,
        counterpartyType,
        fullName,
        shortName,
        inn,
        kpp,
        phones,
        addresses
) {

    /**
     * Тип агента
     */
    enum class Type {
        /**
         * Агент
         */
        AGENT,
        /**
         * Комиссионер
         */
        COMMISSIONER,
        /**
         * Поверенный
         */
        ATTORNEY_IN_FACT,
        /**
         * Платёжный агент
         */
        PAYMENT_AGENT,
        /**
         * Банковский платёжный агент
         */
        BANK_PAYMENT_AGENT,
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
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }

}