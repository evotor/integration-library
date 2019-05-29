package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.payment.PaymentPurpose;

/**
 * Событие, которое возникает при разделении платежей в чеке продажи.
 * <p>
 * Константа события указывает тип чека, платежи которого будут разделены.
 * <p>
 * Чтобы приложение получало событие, значение константы необходимо указать в элементе <code><action></code> intent-фильтра соотвествующей службы.
 */
public class PaymentSelectedEvent extends PaymentEvent {
    /**
     * Разделены платежи чека продажи.
     * <p>
     * Значение константы: <code>evo.v2.receipt.sell.payment.SELECTED</code>.
     */
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.payment.SELECTED";

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
