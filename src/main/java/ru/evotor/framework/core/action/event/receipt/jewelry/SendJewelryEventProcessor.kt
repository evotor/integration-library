package ru.evotor.framework.core.action.event.receipt.jewelry

import android.os.Bundle
import ru.evotor.framework.core.action.processor.ActionProcessor

/**
 * Обработчик события [SendJewelryEvent].
 */
abstract class SendJewelryEventProcessor : ActionProcessor() {

    override fun process(action: String, bundle: Bundle?, callback: ActionProcessor.Callback) {
        val event = SendJewelryEvent.from(bundle) ?: run {
            callback.skip()
            return
        }
        call(action, event, callback)
    }

    /**
     * Используйте метод, чтобы обработать событие {@link SendJewelryEvent} и сохранить результат {@link SendJewelryEventResult}.
     * @param action
     * @param event экземпляр события выбора оплаты.
     * @param callback функция обратного вызова. Методы функции позволяют пропускать обработку события, возвращать результат,
     *                запускать операции и обрабатывать ошибки.
     */
    abstract fun call(action: String, event: SendJewelryEvent, callback: ActionProcessor.Callback)

}
