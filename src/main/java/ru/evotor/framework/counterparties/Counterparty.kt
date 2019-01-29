package ru.evotor.framework.counterparties

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper
import java.util.*

/**
 * Контрагент
 */
abstract class Counterparty : IBundlable {
    abstract val uuid: UUID?

    abstract val counterpartyType: Type?

    abstract val fullName: String?

    abstract val shortName: String?

    abstract val inn: String?

    abstract val kpp: String?

    abstract val phones: List<String>?

    abstract val addresses: List<String>?

    /**
     * Тип контрагента
     */
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

    override fun toBundle(): Bundle = CounterpartyMapper.write(this)
}