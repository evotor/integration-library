package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.framework.Utils;
import ru.evotor.framework.receipt.Tax;
import ru.evotor.framework.receipt.TaxNumber;

public final class TaxMapper {
    private static final String KEY_TAX_NUMBER = "taxNumber";
    private static final String KEY_TAX_RATE_PERCENT = "taxRatePercent";
    private static final String KEY_VALUE = "value";

    @Nullable
    public static Tax from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String taxNumber = bundle.getString(KEY_TAX_NUMBER);
        BigDecimal taxRatePercent = BundleUtils.getBigDecimal(bundle, KEY_TAX_RATE_PERCENT, null);
        BigDecimal value = BundleUtils.getMoney(bundle, KEY_VALUE);
        return new Tax(
                Utils.safeValueOf(TaxNumber.class, taxNumber, TaxNumber.NO_VAT),
                taxRatePercent,
                value
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable Tax tax) {
        if (tax == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TAX_NUMBER, tax.getTaxNumber().name());
        bundle.putString(KEY_TAX_RATE_PERCENT, tax.getTaxRatePercent().toPlainString());
        bundle.putString(KEY_VALUE, tax.getValue().toPlainString());

        return bundle;
    }

    private TaxMapper() {
    }

}
