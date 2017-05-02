package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ru.evotor.framework.Utils;
import ru.evotor.framework.inventory.ProductType;
import ru.evotor.framework.receipt.ExtraKey;
import ru.evotor.framework.receipt.Position;
import ru.evotor.framework.receipt.Tax;
import ru.evotor.framework.receipt.TaxNumber;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public final class PositionMapper {
    private static final String KEY_UUID = "uuid";
    private static final String KEY_PRODUCT_UUID = "productUuid";
    private static final String KEY_PRODUCT_CODE = "productCode";
    private static final String KEY_PRODUCT_TYPE = "productType";
    private static final String KEY_PRICE = "price";
    private static final String KEY_PRICE_WITH_DISCOUNT_POSITION = "priceWithDiscountPosition";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_NAME = "name";
    private static final String KEY_MEASURE_NAME = "measureName";
    private static final String KEY_MEASURE_PRECISION = "measurePrecision";
    private static final String KEY_BARCODE = "barcode";
    private static final String KEY_MARK = "mark";
    private static final String KEY_ALCOHOL_BY_VOLUME = "alcoholByVolume";
    private static final String KEY_ALCOHOL_PRODUCT_KIND_CODE = "alcoholProductKindCode";
    private static final String KEY_TARE_VOLUME = "tareVolume";
    private static final String KEY_PRINT_GROUP = "printGroup";
    private static final String KEY_EXTRA_KEYS = "extraKeys";
    private static final String KEY_TAXES = "taxes";

    public static Position from(Bundle bundle) {
        String uuid = bundle.getString(KEY_UUID);
        String productUuid = bundle.getString(KEY_PRODUCT_UUID);
        String productCode = bundle.getString(KEY_PRODUCT_CODE);
        ProductType productType = Utils.safeValueOf(ProductType.class, bundle.getString(KEY_PRODUCT_TYPE), ProductType.NORMAL);
        String name = bundle.getString(KEY_NAME);
        String measureName = bundle.getString(KEY_MEASURE_NAME);
        int measurePrecision = bundle.getInt(KEY_MEASURE_PRECISION);
        String price = bundle.getString(KEY_PRICE);
        String priceWithDiscountPosition = bundle.getString(KEY_PRICE_WITH_DISCOUNT_POSITION);
        String quantity = bundle.getString(KEY_QUANTITY);
        String barcode = bundle.getString(KEY_BARCODE);
        String mark = bundle.getString(KEY_MARK);
        String alcoholByVolume = bundle.getString(KEY_ALCOHOL_BY_VOLUME);
        String alcoholProductKindCode = bundle.getString(KEY_ALCOHOL_PRODUCT_KIND_CODE);
        String tareVolume = bundle.getString(KEY_TARE_VOLUME);

        Parcelable[] extraKeysParcelable = bundle.getParcelableArray(KEY_EXTRA_KEYS);
        List<ExtraKey> extraKeys = new ArrayList<>(extraKeysParcelable.length);
        for (Parcelable extraKey : extraKeysParcelable) {
            extraKeys.add(ExtraKeyMapper.from((Bundle) extraKey));
        }

        Parcelable[] taxesParcelable = bundle.getParcelableArray(KEY_TAXES);
        LinkedHashMap<TaxNumber, Tax> taxes = new LinkedHashMap<>(taxesParcelable.length);
        for (Parcelable taxParcelable : taxesParcelable) {
            Tax tax = TaxMapper.from((Bundle) taxParcelable);
            taxes.put(tax.getTaxNumber(), tax);
        }

        return new Position(
                uuid,
                productUuid,
                productCode,
                productType,
                name,
                measureName,
                measurePrecision,
                new BigDecimal(price),
                new BigDecimal(priceWithDiscountPosition),
                new BigDecimal(quantity),
                barcode,
                mark,
                new BigDecimal(alcoholByVolume),
                Long.valueOf(alcoholProductKindCode),
                new BigDecimal(tareVolume),
                PrintGroupMapper.from(bundle.getBundle(KEY_PRINT_GROUP)),
                extraKeys,
                taxes
        );
    }

    public static Bundle toBundle(Position position) {
        if (position == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_UUID, position.getUuid());
        bundle.putString(KEY_PRODUCT_UUID, position.getProductUuid());
        bundle.putString(KEY_PRODUCT_CODE, position.getProductCode());
        bundle.putString(KEY_PRODUCT_TYPE, position.getProductType().name());
        bundle.putString(KEY_NAME, position.getName());
        bundle.putString(KEY_MEASURE_NAME, position.getMeasureName());
        bundle.putInt(KEY_MEASURE_PRECISION, position.getMeasurePrecision());
        bundle.putString(KEY_PRICE, position.getPrice().toPlainString());
        bundle.putString(KEY_PRICE_WITH_DISCOUNT_POSITION, position.getPriceWithDiscountPosition().toPlainString());
        bundle.putString(KEY_QUANTITY, position.getQuantity().toPlainString());
        bundle.putString(KEY_BARCODE, position.getBarcode());
        bundle.putString(KEY_MARK, position.getMark());
        bundle.putString(KEY_ALCOHOL_BY_VOLUME, position.getAlcoholByVolume().toPlainString());
        bundle.putString(KEY_ALCOHOL_PRODUCT_KIND_CODE, position.getAlcoholProductKindCode().toString());
        bundle.putString(KEY_TARE_VOLUME, position.getTareVolume().toPlainString());
        bundle.putBundle(KEY_PRINT_GROUP, PrintGroupMapper.toBundle(position.getPrintGroup()));
        Parcelable[] extraKeys = new Parcelable[position.getExtraKeys().size()];
        for (int i = 0; i < extraKeys.length; i++) {
            extraKeys[i] = ExtraKeyMapper.toBundle(position.getExtraKeys().get(i));
        }
        bundle.putParcelableArray(KEY_EXTRA_KEYS, extraKeys);

        Parcelable[] taxes = new Parcelable[position.getTaxes().size()];
        int i = 0;
        for (Tax tax : position.getTaxes().values()) {
            taxes[i] = TaxMapper.toBundle(tax);
            i++;
        }
        bundle.putParcelableArray(KEY_TAXES, taxes);

        return bundle;
    }

    private PositionMapper() {
    }

}
