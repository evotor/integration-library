package ru.evotor.framework.counterparties.collaboration.agent_scheme

import android.os.Bundle
import ru.evotor.framework.counterparties.Counterparty
import ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper.SupplierMapper
import java.util.*

class Supplier(
        uuid: UUID?,
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

    companion object {
        fun from(bundle: Bundle?): Supplier? = SupplierMapper.read(bundle)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Supplier) return false
        if (!super.equals(other)) return false
        return true
    }

}