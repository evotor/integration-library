package ru.evotor.framework.organisation.agent.mapper

import android.os.Bundle
import ru.evotor.framework.organisation.agent.TransactionOperator
import ru.evotor.framework.organisation.mapper.OrganisationMapper
import java.lang.Exception

object TransactionOperatorMapper {

    fun read(bundle: Bundle?) =
            OrganisationMapper.read(bundle)?.let {
                try {
                    TransactionOperator(
                            uuid = it.uuid,
                            fullName = it.fullName,
                            shortName = it.shortName,
                            inn = it.inn,
                            kpp = it.kpp,
                            contacts = it.contacts
                    )
                } catch (e: Exception) {
                    null
                }
            }

}