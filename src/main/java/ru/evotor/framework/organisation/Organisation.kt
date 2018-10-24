package ru.evotor.framework.organisation

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.organisation.mapper.OrganisationMapper
import java.util.*

open class Organisation(
        val uuid: UUID?,
        val fullName: String?,
        val shortName: String?,
        val inn: String?,
        val kpp: String?,
        val contacts: Contacts?
) : IBundlable {

    data class Contacts(val phone: String?, val address: String?)

    companion object {
        fun from(bundle: Bundle?): Organisation? = OrganisationMapper.read(bundle)
    }

    override fun toBundle() = OrganisationMapper.write(this)

}