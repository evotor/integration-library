package ru.evotor.framework.core.action.event.receipt.internet

import android.os.Bundle
import ru.evotor.framework.core.action.processor.ActionProcessor

/**
 * Обработчик события [InternetRequisitesRequiredEvent].
 */
abstract class InternetRequisitesRequiredEventProcessor : ActionProcessor() {

    override fun process(action: String, bundle: Bundle?, callback: Callback) {
        val event = InternetRequisitesRequiredEvent.Companion.from(bundle) ?: run {
            callback.skip()
            return
        }
        call(action, event, callback)
    }

    /**
     * Используйте метод, чтобы обработать событие [InternetRequisitesRequiredEvent] и сохранить результат [InternetRequisitesRequiredEventResult].
     * @param action
     * @param event экземпляр события передачи реквизитов в случае интернет-расчёта.
     * @param callback функция обратного вызова. Методы функции позволяют пропускать обработку события, возвращать результат,
     *                запускать операции и обрабатывать ошибки.
     */
    abstract fun call(action: String, event: InternetRequisitesRequiredEvent, callback: Callback)

}
