package ru.evotor.framework.organisation.agent

import android.os.Bundle
import ru.evotor.framework.organisation.Organisation
import ru.evotor.framework.organisation.agent.mapper.SupplierMapper
import java.util.*

class Supplier(
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

    companion object {
        fun from(bundle: Bundle?): Supplier? = SupplierMapper.read(bundle)
    }

}