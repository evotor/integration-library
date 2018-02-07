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
    private static final String KEY_SINGLE_PAYMENT = "payment";
    private static final String KEY_SINGLE_PAYMENT_VALUE = "paymentValue";
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_SINGLE_CHANGE = "change";
    private static final String KEY_SINGLE_CHANGE_VALUE = "changeValue";

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
        ArrayList<Bundle> paymentsParcelableList = bundle.getParcelableArrayList(KEY_PAYMENTS);
        if (paymentsParcelableList == null) {
            return null;
        }
        for (int i = 0; i < paymentsParcelableList.size(); i++) {
            Bundle completePaymentBundle = paymentsParcelableList.get(i);
            Payment payment = PaymentMapper.from(completePaymentBundle.getBundle(KEY_SINGLE_PAYMENT));
            payments.put(payment, BundleUtils.getMoney(completePaymentBundle, KEY_SINGLE_PAYMENT_VALUE));
        }

        Map<Payment, BigDecimal> changes = new HashMap<>();
        ArrayList<Bundle> changesParcelableList = bundle.getParcelableArrayList(KEY_CHANGES);
        if (changesParcelableList == null) {
            return null;
        }
        for (int i = 0; i < changesParcelableList.size(); i++) {
            Bundle completeChangeBundle = changesParcelableList.get(i);
            Payment change = PaymentMapper.from(completeChangeBundle.getBundle(KEY_SINGLE_CHANGE));
            changes.put(change, BundleUtils.getMoney(completeChangeBundle, KEY_SINGLE_CHANGE_VALUE));
        }

        return new Receipt.PrintReceipt(
                printGroup,
                positions,
                payments,
                changes,
                new HashMap<String, BigDecimal>()
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

        ArrayList<Bundle> payments = new ArrayList<>();
        for (Map.Entry<Payment, BigDecimal> paymentEntry : printReceipt.getPayments().entrySet()) {
            Bundle singlePaymentBundle = PaymentMapper.toBundle(paymentEntry.getKey());
            Bundle completePaymentBundle = new Bundle();
            completePaymentBundle.putParcelable(KEY_SINGLE_PAYMENT, singlePaymentBundle);
            completePaymentBundle.putString(KEY_SINGLE_PAYMENT_VALUE, paymentEntry.getValue().toPlainString());
            payments.add(completePaymentBundle);
        }
        bundle.putParcelableArrayList(KEY_PAYMENTS, payments);

        ArrayList<Bundle> changes = new ArrayList<>();
        for (Map.Entry<Payment, BigDecimal> changeEntry : printReceipt.getChanges().entrySet()) {
            Bundle singleChangeBundle = PaymentMapper.toBundle(changeEntry.getKey());
            Bundle completeChangeBundle = new Bundle();
            completeChangeBundle.putParcelable(KEY_SINGLE_CHANGE, singleChangeBundle);
            completeChangeBundle.putString(KEY_SINGLE_CHANGE_VALUE, changeEntry.getValue().toPlainString());
            changes.add(completeChangeBundle);
        }
        bundle.putParcelableArrayList(KEY_CHANGES, changes);

        return bundle;
    }

    private PrintReceiptMapper() {
    }

}
