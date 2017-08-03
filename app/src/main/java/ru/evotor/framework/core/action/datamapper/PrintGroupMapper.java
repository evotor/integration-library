package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.evotor.framework.Utils;
import ru.evotor.framework.receipt.PrintGroup;
import ru.evotor.framework.receipt.TaxationSystem;

public final class PrintGroupMapper {
    private static final String KEY_IDENTIFIER = "identifier";
    private static final String KEY_TYPE = "type";
    private static final String KEY_ORG_NAME = "orgName";
    private static final String KEY_ORG_INN = "orgInn";
    private static final String KEY_ORG_ADDRESS = "orgAddress";
    private static final String KEY_TAXATION_SYSTEM = "taxationSystem";
    private static final String KEY_SHOULD_PRINT_RECEIPT = "shouldPrintReceipt";

    @Nullable
    public static PrintGroup from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String identifier = bundle.getString(KEY_IDENTIFIER);
        String type = bundle.getString(KEY_TYPE);
        String orgName = bundle.getString(KEY_ORG_NAME);
        String orgInn = bundle.getString(KEY_ORG_INN);
        String orgAddress = bundle.getString(KEY_ORG_ADDRESS);
        String taxationSystem = bundle.getString(KEY_TAXATION_SYSTEM);
        boolean shouldPrintReceipt = bundle.getBoolean(KEY_SHOULD_PRINT_RECEIPT, true);
        return new PrintGroup(
                identifier,
                Utils.safeValueOf(PrintGroup.Type.class, type, PrintGroup.Type.CASH_RECEIPT),
                orgName,
                orgInn,
                orgAddress,
                Utils.safeValueOf(TaxationSystem.class, taxationSystem, null),
                shouldPrintReceipt
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable PrintGroup printGroup) {
        if (printGroup == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_IDENTIFIER, printGroup.getIdentifier());
        bundle.putString(KEY_TYPE, printGroup.getType().name());
        bundle.putString(KEY_ORG_NAME, printGroup.getOrgName());
        bundle.putString(KEY_ORG_INN, printGroup.getOrgInn());
        bundle.putString(KEY_ORG_ADDRESS, printGroup.getOrgAddress());
        bundle.putString(KEY_TAXATION_SYSTEM, printGroup.getTaxationSystem() == null ? null : printGroup.getTaxationSystem().name());
        bundle.putBoolean(KEY_SHOULD_PRINT_RECEIPT, printGroup.isShouldPrintReceipt());

        return bundle;
    }

    private PrintGroupMapper() {
    }

}
