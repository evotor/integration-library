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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Counterparty) return false

        if (uuid != other.uuid) return false
        if (counterpartyType != other.counterpartyType) return false
        if (fullName != other.fullName) return false
        if (shortName != other.shortName) return false
        if (inn != other.inn) return false
        if (kpp != other.kpp) return false
        if (contacts != other.contacts) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid?.hashCode() ?: 0
        result = 31 * result + (counterpartyType?.hashCode() ?: 0)
        result = 31 * result + (fullName?.hashCode() ?: 0)
        result = 31 * result + (shortName?.hashCode() ?: 0)
        result = 31 * result + (inn?.hashCode() ?: 0)
        result = 31 * result + (kpp?.hashCode() ?: 0)
        result = 31 * result + (contacts?.hashCode() ?: 0)
        return result
    }

}