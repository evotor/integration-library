package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Date;

import ru.evotor.framework.Utils;
import ru.evotor.framework.receipt.Receipt;


public final class ReceiptHeaderMapper {

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_RECEIPT_NUMBER = "receiptNumber";
    private static final String KEY_RECEIPT_TYPE = "receiptType";
    private static final String KEY_RECEIPT_DATE = "receiptDate";
    private static final String KEY_CLIENT_EMAIL = "clientEmail";
    private static final String KEY_CLIENT_PHONE = "clientPhone";
    private static final String KEY_EXTRA = "extra";

    @Nullable
    public static Receipt.Header from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID);
        String receiptNumber = bundle.getString(KEY_RECEIPT_NUMBER);
        String receiptType = bundle.getString(KEY_RECEIPT_TYPE);
        Date date = null;
        if (bundle.containsKey(KEY_RECEIPT_DATE)) {
            date = new Date(bundle.getLong(KEY_RECEIPT_DATE));
        }

        if (receiptUuid == null) {
            return null;
        }

        return new Receipt.Header(
                receiptUuid,
                receiptNumber,
                Utils.safeValueOf(Receipt.Type.class, receiptType, null),
                date,
                bundle.getString(KEY_CLIENT_EMAIL),
                bundle.getString(KEY_CLIENT_PHONE),
                bundle.getString(KEY_EXTRA)
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

        Date data = header.getDate();
        if (data == null) {
            bundle.putString(KEY_RECEIPT_DATE, null);
        } else {
            bundle.putLong(KEY_RECEIPT_DATE, data.getTime());
        }
        bundle.putString(KEY_CLIENT_PHONE, header.getClientPhone());
        bundle.putString(KEY_CLIENT_EMAIL, header.getClientEmail());
        bundle.putString(KEY_EXTRA, header.getExtra());

        return bundle;
    }

    private ReceiptHeaderMapper() {
    }

}
