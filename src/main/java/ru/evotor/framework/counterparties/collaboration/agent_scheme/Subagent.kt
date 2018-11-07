package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.core.FfdTag
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.SubagentMapper
import java.util.*

/**
 * Cубагент
 */
data class Subagent(
        override val uuid: UUID?,

        @FfdTag(1057, 1222)
        val type: Type,

        override val counterpartyType: Counterparty.Type?,

        override val fullName: String?,

        override val shortName: String?,

        override val inn: String?,

        override val kpp: String?,

        @FfdTag(1073)
        override val phones: List<String>?,

        override val addresses: List<String>?
) : Counterparty() {

    /**
     * Тип субагента
     */
    enum class Type {
        /**
         * Платёжный субагент
         */
        PAYMENT_SUBAGENT,
        /**
         * Банковский платёжный субагент
         */
        BANK_PAYMENT_SUBAGENT,
    }

    companion object {
        fun from(bundle: Bundle?): Subagent? = SubagentMapper.read(bundle)
    }

    override fun toBundle(): Bundle = SubagentMapper.write(this, super.toBundle())

}