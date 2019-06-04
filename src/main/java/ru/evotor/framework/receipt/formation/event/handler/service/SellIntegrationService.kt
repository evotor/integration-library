package ru.evotor.framework.receipt.formation.event.handler.service

import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.ReturnPositionsForBarcodeRequestedEvent

/**
 * Служба для работы с чеком продажи.
 */
abstract class SellIntegrationService : ReceiptFormationIntegrationService(
        ACTION_BARCODE_RECEIVED
) {

    /**
     * Обрабатывает события сканирования штрихкода и позволяет добавлять позиции в чек продажи.
     *
     * Если поступившее событие содержит параметр `creatingNewProduct == true`, метод должен вернуть `null`.
     *
     * @param event событие сканирования штрихкода.
     * @return результат обработки события. Содержит список позиций для добавления в чек и параметр `iCanCreateNewProduct`, который сообщает, намерено приложение создать новый товар или нет.
     * @see <a href="https://developer.evotor.ru/docs/doc_java_return_positions_for_barcode_requested.html">Обработка события сканирования штрихкода</a>
     */
    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    override fun handleEvent(event: ReturnPositionsForBarcodeRequestedEvent): ReturnPositionsForBarcodeRequestedEvent.Result? = null

    companion object {

        /**
         * Действие, которое сообщает о получении данных от сканера штрихкодов.
         *
         * Чтобы подписать службу на получение действия, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.event.sell.BARCODE_RECEIVED`.
         */
        const val ACTION_BARCODE_RECEIVED = "ru.evotor.event.sell.BARCODE_RECEIVED"

        /**
         * Разрешение необходимое приложению для работы со службой [ru.evotor.framework.receipt.formation.event.handler.service.SellIntegrationService].
         *
         * Чтобы выдать разрешение, в элементе `uses-permission` манифеста приложения, укажите значение `ru.evotor.permission.SELL_INTEGRATION_SERVICE`.
         */
        const val PERMISSION = "ru.evotor.permission.SELL_INTEGRATION_SERVICE"
    }
}
