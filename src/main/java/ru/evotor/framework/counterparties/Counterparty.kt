package ru.evotor.framework.counterparties

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper
import java.util.*

/**
 * Контрагент
 */
abstract class Counterparty(
        val uuid: UUID?,
        val counterpartyType: Type?,
        val fullName: String?,
        val shortName: String?,
        val inn: String?,
        val kpp: String?,
        val contacts: Contacts?
) : IBundlable {

    enum class Type {
        /**
         * Юридическое лицо
         */
        LEGAL_ENTITY,
        /**
         * Индивидуальный предприниматель
         */
        INDIVIDUAL_ENTREPRENEUR,
        /**
         * Государственный орган
         */
        GOVERNMENT_AGENCY
    }

    data class Contacts(val phones: List<String>?, val addresses: List<String>?)

    companion object {
        fun from(bundle: Bundle?): Counterparty? = CounterpartyMapper.read(bundle)
    }

    override fun toBundle() = CounterpartyMapper.write(this)

}