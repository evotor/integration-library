package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.core.FfdTag
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.AgentMapper
import java.util.*

/**
 * Агент
 */
data class Agent(
        override val uuid: UUID?,

        @FfdTag(1057, 1222)
        val type: Type?,

        override val counterpartyType: Counterparty.Type?,

        override val fullName: String?,

        override val shortName: String?,

        override val inn: String?,

        override val kpp: String?,

        @FfdTag(1073, 1074)
        override val phones: List<String>?,

        override val addresses: List<String>?
) : Counterparty() {

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

}