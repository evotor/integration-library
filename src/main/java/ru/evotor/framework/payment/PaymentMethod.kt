package ru.evotor.framework.payment

import java.util.*

/**
 * Способ оплаты
 */
sealed class PaymentMethod(val appUuid: UUID) {
    /**
     * Стандартный способ оплаты "Наличные"
     */
    class DefaultCash : PaymentMethod(APP_UUID) {
        companion object {
            private val APP_UUID = java.util.UUID.fromString("")
        }
    }

    /**
     * Стандартный способ оплаты "Банковская карта"
     */
    class DefaultCashless : PaymentMethod(APP_UUID) {
        companion object {
            private val APP_UUID = java.util.UUID.fromString("")
        }
    }

    /**
     * Способ оплаты через стороннюю интеграцию, установленную на терминал из Эвотор.Маркета
     */
    class ThirdPartyIntegration(appUuid: UUID) : PaymentMethod(appUuid) {

     /*   class Query : FilterBuilder<Query, Query.SortOrder, ThirdPartyIntegration>() {
            val appUuid = addFieldFilter<UUID>("")

            class SortOrder : FilterBuilder.SortOrder<SortOrder>() {

            }

            override fun getValue(context: Context, cursor: Cursor<ThirdPartyIntegration>): ThirdPartyIntegration =
        }*/
    }
}