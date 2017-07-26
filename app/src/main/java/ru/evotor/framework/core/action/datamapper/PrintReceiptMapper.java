package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.evotor.framework.receipt.Payment;
import ru.evotor.framework.receipt.Position;
import ru.evotor.framework.receipt.PrintGroup;
import ru.evotor.framework.receipt.Receipt;


public final class PrintReceiptMapper {

    private static final String KEY_PRINT_GROUP = "printGroup";
    private static final String KEY_POSITIONS = "positions";
    private static final String KEY_PAYMENTS = "payments";
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_DISCOUNT_PERCENT = "discountPercent";

    @Nullable
    public static Receipt.PrintReceipt from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        PrintGroup printGroup = PrintGroupMapper.from(bundle.getBundle(KEY_PRINT_GROUP));

        List<Position> positions = new ArrayList<>();
        Parcelable[] positionsParcelable = bundle.getParcelableArray(KEY_POSITIONS);
        if (positionsParcelable == null) {
            return null;
        }
        for (int i = 0; i < positionsParcelable.length; i++) {
            Position position = PositionMapper.from((Bundle) positionsParcelable[i]);
            positions.add(position);
        }

        Map<Payment, BigDecimal> payments = new HashMap<>();
        Bundle paymentsBundle = bundle.getBundle(KEY_PAYMENTS);
        if (paymentsBundle == null) {
            return null;
        }
        for (String key : paymentsBundle.keySet()) {
            payments.put(PaymentMapper.from(paymentsBundle.getBundle(key)), new BigDecimal(key));
        }

        Map<Payment, BigDecimal> changes = new HashMap<>();
        Bundle changesBundle = bundle.getBundle(KEY_PAYMENTS);
        if (changesBundle == null) {
            return null;
        }
        for (String key : changesBundle.keySet()) {
            changes.put(PaymentMapper.from(changesBundle.getBundle(key)), new BigDecimal(key));
        }

        return new Receipt.PrintReceipt(
                printGroup,
                positions,
                payments,
                changes,
                BigDecimal.ZERO, //TODO discount,
                BigDecimal.ZERO //TODO discountPercent
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable Receipt.PrintReceipt printReceipt) {
        if (printReceipt == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle(KEY_PRINT_GROUP, PrintGroupMapper.toBundle(printReceipt.getPrintGroup()));

        Parcelable[] positionsParcelable = new Parcelable[printReceipt.getPositions().size()];
        for (int i = 0; i < positionsParcelable.length; i++) {
            Position position = printReceipt.getPositions().get(i);
            positionsParcelable[i] = PositionMapper.toBundle(position);
        }
        bundle.putParcelableArray(KEY_POSITIONS, positionsParcelable);

        Bundle payments = new Bundle();
        for (Map.Entry<Payment, BigDecimal> entry : printReceipt.getPayments().entrySet()) {
            Bundle singlePaymentBundle = PaymentMapper.toBundle(entry.getKey());
            payments.putBundle(entry.getValue().toPlainString(), singlePaymentBundle);

        }
        bundle.putBundle(KEY_PAYMENTS, payments);

        Bundle changes = new Bundle();
        for (Map.Entry<Payment, BigDecimal> entry : printReceipt.getChanges().entrySet()) {
            Bundle singleChangesBundle = PaymentMapper.toBundle(entry.getKey());
            payments.putBundle(entry.getValue().toPlainString(), singleChangesBundle);

        }
        bundle.putBundle(KEY_CHANGES, changes);

        return bundle;
    }

    private PrintReceiptMapper() {
    }

}
