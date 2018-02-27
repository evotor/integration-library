package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.evotor.framework.receipt.Receipt;


public final class ReceiptMapper {

    private static final String KEY_HEADER = "header";
    private static final String KEY_PRINT_DOCUMENTS = "printDocuments";

    @Nullable
    public static Receipt from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Bundle headerBundle = bundle.getBundle(KEY_HEADER);
        Receipt.Header header = ReceiptHeaderMapper.from(headerBundle);

        List<Receipt.PrintReceipt> printDocuments = new ArrayList<>();
        Parcelable[] printReceiptParcelable = bundle.getParcelableArray(KEY_PRINT_DOCUMENTS);
        if (printReceiptParcelable == null) {
            return null;
        }
        for (int i = 0; i < printReceiptParcelable.length; i++) {
            Receipt.PrintReceipt position = PrintReceiptMapper.from((Bundle) printReceiptParcelable[i]);
            printDocuments.add(position);
        }

        return new Receipt(
                header,
                printDocuments
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

        return bundle;
    }

    private ReceiptMapper() {
    }

}
