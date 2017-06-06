package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;

import java.math.BigDecimal;

import ru.evotor.framework.Utils;
import ru.evotor.framework.calculator.MoneyCalculator;
import ru.evotor.framework.receipt.Tax;
import ru.evotor.framework.receipt.TaxNumber;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public final class TaxMapper {
    private static final String KEY_TAX_NUMBER = "taxNumber";
    private static final String KEY_TAX_RATE_PERCENT = "taxRatePercent";
    private static final String KEY_VALUE = "value";

    public static Tax from(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String taxNumber = bundle.getString(KEY_TAX_NUMBER);
        String taxRatePercent = bundle.getString(KEY_TAX_RATE_PERCENT);
        String value = bundle.getString(KEY_VALUE);
        return new Tax(
                Utils.safeValueOf(TaxNumber.class, taxNumber, TaxNumber.NO_VAT),
                new BigDecimal(taxRatePercent),
                MoneyCalculator.round(new BigDecimal(value))
        );
    }

    public static Bundle toBundle(Tax tax) {
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
