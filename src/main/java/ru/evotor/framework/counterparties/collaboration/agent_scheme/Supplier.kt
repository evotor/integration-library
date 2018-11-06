package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.core.FfdTag
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.SupplierMapper
import java.util.*

/**
 * Поставщик
 */
data class Supplier(
        override val uuid: UUID?,

        override val counterpartyType: Counterparty.Type?,

        override val fullName: String?,

        override val shortName: String?,

        @FfdTag(1226)
        override val inn: String?,

        override val kpp: String?,

        @FfdTag(1171)
        override val phones: List<String>?,

        override val addresses: List<String>?
) : Counterparty() {

    companion object {
        fun from(bundle: Bundle?): Supplier? = SupplierMapper.read(bundle)
    }

}