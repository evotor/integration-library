package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.core.FfdTag
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.TransactionOperatorMapper
import java.util.*

/**
 * Оператор перевода
 */
data class TransactionOperator(
        override val uuid: UUID?,

        override val counterpartyType: Counterparty.Type?,

        @FfdTag(1026)
        override val fullName: String?,

        override val shortName: String?,

        @FfdTag(1016)
        override val inn: String?,

        override val kpp: String?,

        @FfdTag(1075)
        override val phones: List<String>?,

        @FfdTag(1005)
        override val addresses: List<String>?
) : Counterparty() {

    companion object {
        fun from(bundle: Bundle?): TransactionOperator? = TransactionOperatorMapper.read(bundle)
    }

}