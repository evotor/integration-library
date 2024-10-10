package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;

import java.math.BigDecimal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.evotor.IBundlable;
import ru.evotor.framework.BundleUtils;

/**
 * Событие, которое возникает при начислении скидки на чек.
 * <p>
 * Константы события указывают тип чека, на который начисляется скидка.
 * <p>
 * Чтобы приложение получало событие, значение константы необходимо указать в элементе <code><action></code> intent-фильтра соотвествующей службы.
 */
public class ReceiptDiscountEvent implements IBundlable {
    private static final String TAG = "ReceiptDiscountEvent";

    /**
     * Скидка начислена на чек продажи.
     * <p>
     * Значение константы: <code>evo.v2.receipt.sell.receiptDiscount</code>.
     */
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.receiptDiscount";
    /**
     * Скидка начислена на чек возврата.
     * <p>
     * Значение константы: <code>evo.v2.receipt.payback.receiptDiscount</code>.
     */
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.receiptDiscount";

    /**
     * Скидка начислена на чек возврата на основании чека продажи.
     * <p>
     * Значение константы: <code>evo.v2.receipt.paybackWithSell.receiptDiscount</code>.
     */
    public static final String NAME_PAYBACK_WITH_SELL_RECEIPT = "evo.v2.receipt.paybackWithSell.receiptDiscount";

    /**
     * Скидка начислена на чек покупки.
     * <p>
     * Значение константы: <code>evo.v2.receipt.buy.receiptDiscount</code>.
     */
    public static final String NAME_BUY_RECEIPT = "evo.v2.receipt.buy.receiptDiscount";

    /**
     * Скидка начислена на чек возврата покупки без основания.
     * <p>
     * Значение константы: <code>evo.v2.receipt.buyback.receiptDiscount</code>.
     */
    public static final String NAME_BUYBACK_RECEIPT = "evo.v2.receipt.buyback.receiptDiscount";

    /**
     * Скидка начислена на чек возврата на основании чека покупки.
     * <p>
     * Значение константы: <code>evo.v2.receipt.buybackWithBuy.receiptDiscount</code>.
     */
    public static final String NAME_BUYBACK_WITH_BUY_RECEIPT = "evo.v2.receipt.buybackWithBuy.receiptDiscount";

    /**
     * Скидка начислена на чек коррекции прихода.
     * <p>
     * Значение константы: <code>evo.v2.receipt.correction.income.receiptDiscount</code>.
     */
    public static final String NAME_CORRECTION_INCOME_RECEIPT = "evo.v2.receipt.correction.income.receiptDiscount";

    /**
     * Скидка начислена на чек коррекции расхода.
     * <p>
     * Значение константы: <code>evo.v2.receipt.correction.outcome.receiptDiscount</code>.
     */
    public static final String NAME_CORRECTION_OUTCOME_RECEIPT = "evo.v2.receipt.correction.outcome.receiptDiscount";

    /**
     * Скидка начислена на чек коррекции возврата прихода.
     * <p>
     * Значение константы: <code>evo.v2.receipt.correction.return.income.receiptDiscount</code>.
     */
    public static final String NAME_CORRECTION_RETURN_INCOME_RECEIPT = "evo.v2.receipt.correction.return.income.receiptDiscount";

    /**
     * Скидка начислена на чек коррекции возврата расхода.
     * <p>
     * Значение константы: <code>evo.v2.receipt.correction.return.outcome.receiptDiscount</code>.
     */
    public static final String NAME_CORRECTION_RETURN_OUTCOME_RECEIPT = "evo.v2.receipt.correction.return.outcome.receiptDiscount";

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_LOYALTY_CARD_ID = "loyaltyCardId";

    @Nullable
    public static ReceiptDiscountEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null);
        BigDecimal discount = BundleUtils.getMoney(bundle, KEY_DISCOUNT);
        if (discount == null) {
            return null;
        }
        String loyaltyCardId = bundle.getString(KEY_LOYALTY_CARD_ID, null);
        return new ReceiptDiscountEvent(receiptUuid, discount, loyaltyCardId);
    }

    @NonNull
    private final String receiptUuid;
    @NonNull
    private final BigDecimal discount;
    @Nullable
    private String loyaltyCardId = null;

    public ReceiptDiscountEvent(
            @NonNull String receiptUuid,
            @NonNull BigDecimal discount
    ) {
        this.receiptUuid = receiptUuid;
        this.discount = discount;
    }
    public ReceiptDiscountEvent(
            @NonNull String receiptUuid,
            @NonNull BigDecimal discount,
            @Nullable String loyaltyCardId
    ) {
        this.receiptUuid = receiptUuid;
        this.discount = discount;
        this.loyaltyCardId = loyaltyCardId;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        result.putString(KEY_DISCOUNT, discount.toPlainString());
        result.putString(KEY_LOYALTY_CARD_ID, loyaltyCardId);
        return result;
    }

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }

    @NonNull
    public BigDecimal getDiscount() {
        return discount;
    }

    @Nullable
    public String getLoyaltyCardId(){
        return loyaltyCardId;
    }
}
