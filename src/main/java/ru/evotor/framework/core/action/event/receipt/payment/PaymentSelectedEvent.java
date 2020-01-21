package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.payment.PaymentPurpose;

/**
 * Событие, обрабатывая которое приложение может разделить оплату на несколько платежей, например, в счёт разных юридических лиц.
 * <p>
 * Смарт-терминал рассылает событие после того, как пользователь, в процессе оплаты или возврата, выбирает тип оплаты <b>Банковская карта</b>.
 * После выбора типа оплаты <b>Банковская карта</b>, пользователь самостоятельно выбирает приложение, которое разделит платежи.
 * Пользователь каждый раз вручную выбирает приложение, которое обработает событие.
 * <p>
 * Для обработки события используется обработчик {@link PaymentSelectedEventProcessor}.
 * Обрабатывая событие приложение возвращает смарт-терминалу результат {@link PaymentSelectedEventResult}.
 * <p>
 * Константы {@value NAME_SELL_RECEIPT} и {@value NAME_PAYBACK_RECEIPT} события указывают тип чека, платежи которого будут разделены.
 * <p>
 * Чтобы приложение получало событие, значение константы необходимо указать в элементе <code>action</code> intent-фильтра соотвествующей службы.
 *
 * @see <a href="https://developer.evotor.ru/docs/doc_java_receipt_division.html">"Разделение чека на несколько платежей"</a>
 */
public class PaymentSelectedEvent extends PaymentEvent {
    /**
     * Выбрана оплата чека продажи. Значение константы: {@value}.
     */
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.payment.SELECTED";
    /**
     * Выбрана оплата чека возврата. Значение константы {@value}.
     */
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.payment.SELECTED";

    public PaymentSelectedEvent(@NonNull PaymentPurpose paymentPurpose) {
        super(paymentPurpose);
    }

    @Nullable
    public static PaymentSelectedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        PaymentPurpose paymentPurpose = PaymentEvent.getPaymentPurpose(bundle);
        if (paymentPurpose == null) {
            return null;
        }

        return new PaymentSelectedEvent(paymentPurpose);
    }
}
