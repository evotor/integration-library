package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.evotor.framework.Utils;
import ru.evotor.framework.receipt.Receipt;


public final class ReceiptHeaderMapper {

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_RECEIPT_NUMBER = "receiptNumber";
    private static final String KEY_RECEIPT_TYPE = "receiptType";

    @Nullable
    public static Receipt.Header from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID);
        String receiptNumber = bundle.getString(KEY_RECEIPT_NUMBER);
        String receiptType = bundle.getString(KEY_RECEIPT_TYPE);
        return new Receipt.Header(
                receiptUuid,
                receiptNumber,
                Utils.safeValueOf(Receipt.Type.class, receiptType, null)
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable Receipt.Header header) {
        if (header == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RECEIPT_UUID, header.getUuid());
        bundle.putString(KEY_RECEIPT_NUMBER, header.getNumber());
        bundle.putString(KEY_RECEIPT_TYPE, header.getType().name());

        return bundle;
    }

    private ReceiptHeaderMapper() {
    }

}
