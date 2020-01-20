package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent
import ru.evotor.framework.receipt.PrintGroup
import ru.evotor.framework.receipt.Purchaser

/**
 * Событие, с помощью которого смарт-терминал запрашивает у установленных приложений реквизиты покупателя.
 *
 * @property receiptUuid Идентификатор чека, в который будут добавлены реквизиты покупателя.
 * @property printGroups Список печатных групп с реквизитами покупателя.
 * @see <a href="https://developer.evotor.ru/docs/doc_java_purchase_requisites_event_processing.html">Добавление реквизитов покупателя в чек</a>
 */
data class ReturnPurchaserRequisitesForPrintGroupRequestedEvent(
        val receiptUuid: String,
        val printGroups: List<PrintGroup?>
) : IntegrationEvent() {

    override fun toBundle() = Bundle().apply {
        putString(KEY_RECEIPT_UUID, receiptUuid)
        putParcelableArrayList(KEY_PRINT_GROUPS, ArrayList(printGroups))
    }

    companion object {
        const val KEY_RECEIPT_UUID = "KEY_RECEIPT_UUID"
        const val KEY_PRINT_GROUPS = "KEY_PRINT_GROUPS"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            it.classLoader = PrintGroup::class.java.classLoader
            ReturnPurchaserRequisitesForPrintGroupRequestedEvent(
                    receiptUuid = it.getString(KEY_RECEIPT_UUID)
                            ?: return null,
                    printGroups = it.getParcelableArrayList(KEY_PRINT_GROUPS)
                            ?: return null)
        }

    }

    /**
     * Результат обработки события, который содержит массив печатных групп с соответствующими реквизитами покупателя.
     *
     * @property printGroupsWithPurchaserRequisites Массив печатных групп и соответствующих им объектов с реквизитами покупателя.
     */
    data class Result(
            val printGroupsWithPurchaserRequisites: Map<PrintGroup?, Purchaser?>?
    ) : IntegrationEvent.Result() {

        override fun toBundle() = Bundle().apply {
            classLoader = PrintGroup::class.java.classLoader
            putInt(KEY_MAP_ENTRIES_COUNT, printGroupsWithPurchaserRequisites?.size ?: -1)
            printGroupsWithPurchaserRequisites?.let {
                var i = 0
                for ((pg, p) in it) {
                    putParcelable("$KEY_MAP_ENTRIES_KEY_PREFIX$i", pg)
                    putParcelable("$KEY_MAP_ENTRIES_VALUE_PREFIX$i", p)
                    i++
                }
            }
        }

        companion object {
            private const val KEY_MAP_ENTRIES_COUNT = "KEY_MAP_ENTRIES_COUNT"
            private const val KEY_MAP_ENTRIES_KEY_PREFIX = "KEY_MAP_ENTRIES_KEY_PREFIX_"
            private const val KEY_MAP_ENTRIES_VALUE_PREFIX = "KEY_MAP_ENTRIES_VALUE_PREFIX_"

            @JvmStatic
            fun from(bundle: Bundle?) = bundle?.let {
                it.classLoader = PrintGroup::class.java.classLoader
                val count = it.getInt(KEY_MAP_ENTRIES_COUNT)
                when (count) {
                    -1 -> Result(null)
                    0 -> Result(emptyMap())
                    else -> {
                        val printGroupsWithPurchaserRequisites = mutableMapOf<PrintGroup?, Purchaser?>()
                        for (i in 0 until count) {
                            val key = it.getParcelable<PrintGroup?>("$KEY_MAP_ENTRIES_KEY_PREFIX$i")
                            val value = it.getParcelable<Purchaser?>("$KEY_MAP_ENTRIES_VALUE_PREFIX$i")
                            printGroupsWithPurchaserRequisites[key] = value
                        }
                        Result(printGroupsWithPurchaserRequisites)
                    }
                }
            }
        }
    }
}