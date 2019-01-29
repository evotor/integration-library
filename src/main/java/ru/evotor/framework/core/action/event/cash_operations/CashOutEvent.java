package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.framework.core.action.datamapper.BundleUtils;

/**
 * @deprecated Используйте {@link ru.evotor.framework.kkt.event.CashWithdrawnEvent}
 */
@Deprecated
public class CashOutEvent extends CashOperationEvent {
    public static final String BROADCAST_ACTION_CASH_OUT = "evotor.intent.action.cashOperation.CASH_OUT";

    private static final String KEY_TOTAL = "total";

    @NonNull
    private final BigDecimal total;

    public CashOutEvent(@NonNull String documentUuid, @NonNull BigDecimal total) {
        super(documentUuid);
        this.total = total;
    }

    @Nullable
    public static CashOutEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String documentUuid = CashOperationEvent.getDocumentUuid(bundle);
        if (documentUuid == null) {
            return null;
        }

        BigDecimal total = BundleUtils.getMoney(bundle, KEY_TOTAL);
        if (total == null) {
            return null;
        }

        return new CashOutEvent(
                documentUuid,
                total
        );
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        bundle.putString(KEY_TOTAL, total.toPlainString());
        return bundle;
    }

    @NonNull
    public BigDecimal getTotal() {
        return total;
    }
}