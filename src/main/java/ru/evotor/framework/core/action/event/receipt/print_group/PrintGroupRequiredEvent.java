package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.evotor.framework.payment.PaymentSystem;

/**
 * Событие, которое возникает при разделении чека на печатные группы.
 * <p>
 * Константы события указывают тип чека, который будет разделён на печатные группы.
 * <p>
 * Чтобы приложение получало событие, значение константы необходимо указать в элементе <code><action></code> intent-фильтра соотвествующей службы.
 */
public class PrintGroupRequiredEvent extends PrintGroupEvent {
    /**
     * Чек продажи разделён на несколько печатных групп.
     * <p>
     * Значение константы: <code>evo.v2.receipt.sell.printGroup.REQUIRED</code>.
     */
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.printGroup.REQUIRED";
    /**
     * Чек покупки разделён на несколько печатных групп.
     * <p>
     * Значение константы: <code>evo.v2.receipt.buy.printGroup.REQUIRED</code>.
     */
    public static final String NAME_BUY_RECEIPT = "evo.v2.receipt.buy.printGroup.REQUIRED";

    public PrintGroupRequiredEvent(@Nullable PaymentSystem paymentSystem) {
        super(paymentSystem);
    }

    @Nullable
    public static PrintGroupRequiredEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        PaymentSystem paymentSystem = PrintGroupEvent.getPaymentSystem(bundle);
        if (paymentSystem == null) {
            return null;
        }

        return new PrintGroupRequiredEvent(paymentSystem);
    }
}