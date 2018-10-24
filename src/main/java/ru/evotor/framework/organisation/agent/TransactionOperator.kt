package ru.evotor.framework.organisation.agent

import android.os.Bundle
import ru.evotor.framework.organisation.Organisation
import ru.evotor.framework.organisation.agent.mapper.TransactionOperatorMapper
import java.util.*

class TransactionOperator(
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
        fun from(bundle: Bundle?): TransactionOperator? = TransactionOperatorMapper.read(bundle)
    }

}