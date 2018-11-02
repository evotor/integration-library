package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.core.FfdTag
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.TransactionOperatorMapper
import java.util.*

/**
 * Оператор перевода
 */
class TransactionOperator(
        uuid: UUID?,

        counterpartyType: Counterparty.Type?,

        @FfdTag(1026)
        fullName: String?,

        shortName: String?,

        @FfdTag(1016)
        inn: String?,

        kpp: String?,

        @FfdTag(1075)
        phones: List<String>?,

        @FfdTag(1005)
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

    companion object {
        fun from(bundle: Bundle?): TransactionOperator? = TransactionOperatorMapper.read(bundle)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TransactionOperator) return false
        if (!super.equals(other)) return false
        return true
    }

}