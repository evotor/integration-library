package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import ru.evotor.framework.receipt.Receipt;


public final class ReceiptMapper {

    private static final String KEY_HEADER = "header";
    private static final String KEY_PRINT_DOCUMENTS = "printDocuments";
    private static final String KEY_STATE = "state";

    @Nullable
    public static Receipt from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Bundle headerBundle = bundle.getBundle(KEY_HEADER);
        Receipt.Header header;
        if (headerBundle != null) {
            header = ReceiptHeaderMapper.from(headerBundle);
        } else {
            return null;
        }
        if (header == null) {
            return null;
        }

        List<Receipt.PrintReceipt> printDocuments = new ArrayList<>();
        Parcelable[] printReceiptParcelable = bundle.getParcelableArray(KEY_PRINT_DOCUMENTS);
        if (printReceiptParcelable == null) {
            return null;
        }
        for (Parcelable parcelable : printReceiptParcelable) {
            Receipt.PrintReceipt position = PrintReceiptMapper.from((Bundle) parcelable);
            printDocuments.add(position);
        }

        Receipt.State state;
        Bundle stateBundle = bundle.getBundle(KEY_STATE);
        if (stateBundle != null) {
            state = ReceiptStateMapper.INSTANCE.from(bundle);
        } else {
            state = null;
        }

        return new Receipt(
                header,
                printDocuments,
                state
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable Receipt receipt) {
        if (receipt == null) {
            return null;
        }
        Bundle bundle = new Bundle();

        bundle.putBundle(KEY_HEADER, ReceiptHeaderMapper.toBundle(receipt.getHeader()));

        Parcelable[] printDocumentsParcelable = new Parcelable[receipt.getPrintDocuments().size()];
        for (int i = 0; i < printDocumentsParcelable.length; i++) {
            Receipt.PrintReceipt printReceipt = receipt.getPrintDocuments().get(i);
            printDocumentsParcelable[i] = PrintReceiptMapper.toBundle(printReceipt);
        }
        bundle.putParcelableArray(KEY_PRINT_DOCUMENTS, printDocumentsParcelable);

        if (receipt.getState() != null) {
            bundle.putBundle(KEY_STATE, ReceiptStateMapper.INSTANCE.toBundle(receipt.getState()));
        }

        return bundle;
    }

    private ReceiptMapper() {
    }

}
