package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Обработчик события {@link PrintGroupRequiredEvent}.
 *
 * @see <a href="https://developer.evotor.ru/docs/doc_java_receipt_printgroups_division.html">"Разделение чека на печатные группы"</a>
 */
public abstract class PrintGroupRequiredEventProcessor extends ActionProcessor {
    @Override
    public void process(@NonNull String action, @Nullable Bundle bundle, @NonNull Callback callback) throws RemoteException {
        PrintGroupRequiredEvent event = PrintGroupRequiredEvent.create(bundle);

        if (event == null) {
            callback.skip();
            return;
        }

        call(
                action,
                event,
                callback
        );
    }

    /**
     * Используйте метод, чтобы обработать событие {@link PrintGroupRequiredEvent} и сохранить результат {@link PrintGroupRequiredEventResult}.
     * @param action
     * @param event экземпляр события разделения чека на печатные группы.
     * @param callback функция обратного вызова. Методы функции позволяют пропускать обработку события, возвращать результат,
     *                запускать операции и обрабатывать ошибки.
     */

    public abstract void call(@NonNull String action, @NonNull PrintGroupRequiredEvent event, @NonNull Callback callback);
}

