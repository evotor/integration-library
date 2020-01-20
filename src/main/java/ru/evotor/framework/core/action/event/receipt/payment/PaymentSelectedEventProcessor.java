package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Обработчик события {@link PaymentSelectedEvent}.
 *
 * @see <a href="https://developer.evotor.ru/docs/doc_java_receipt_division.html">"Разделение чека на несколько платежей"</a>
 */
public abstract class PaymentSelectedEventProcessor extends ActionProcessor {
    @Override
    public void process(@NonNull String action, @Nullable Bundle bundle, @NonNull Callback callback) throws RemoteException {
        PaymentSelectedEvent event = PaymentSelectedEvent.create(bundle);

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
     * Используйте метод, чтобы обработать событие {@link PaymentSelectedEvent} и сохранить результат {@link PaymentSelectedEventResult}.
     * @param action
     * @param event экземпляр события выбора оплаты.
     * @param callback функция обратного вызова. Методы функции позволяют пропускать обработку события, возвращать результат,
     *                запускать операции и обрабатывать ошибки.
     */
    public abstract void call(@NonNull String action, @NonNull PaymentSelectedEvent event, @NonNull Callback callback);
}

