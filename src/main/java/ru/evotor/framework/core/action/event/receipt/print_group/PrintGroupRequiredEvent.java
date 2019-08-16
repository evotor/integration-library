package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.evotor.framework.payment.PaymentSystem;

/**
 * Событие, которое смарт-терминал рассылает перед печатью чека покупки, продажи или возврата.
 * Обрабатывая это событие вы сможете разделить чек на несколько печатных групп ({@link ru.evotor.framework.receipt.PrintGroup}).
 * <p>
 * Пользователь каждый раз вручную выбирает приложение, которое обработает событие.
 * <p>
 * Для обработки события используется обработчик {@link PrintGroupRequiredEventProcessor}.
 * Обрабатывая событие приложение возвращает смарт-терминалу результат {@link PrintGroupRequiredEventResult}.
 * <p>
 * Константы {@value NAME_SELL_RECEIPT}, {@value NAME_PAYBACK_RECEIPT} и {@value NAME_BUY_RECEIPT} указывают тип чека, который будет разделён на печатные группы в результате обработки события.
 * <p>
 * Чтобы приложение получало событие, значение константы необходимо указать в элементе <code>action</code> intent-фильтра соотвествующей службы.
 *
 * @see <a href="https://developer.evotor.ru/docs/doc_java_receipt_printgroups_division.html">"Разделение чека на печатные группы"</a>
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
    /**
     * Чек возврата разделён на несколько печатных групп.
     * <p>
     * Значение константы: <code>evo.v2.receipt.payback.printGroup.REQUIRED</code>.
     */
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.printGroup.REQUIRED";

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