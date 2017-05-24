package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ru.evotor.framework.Utils;
import ru.evotor.framework.inventory.ProductType;
import ru.evotor.framework.receipt.ExtraKey;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public final class PositionMapper {
    public static final String KEY_POSITION = "position";

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

    public static Position from(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
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
        Set<ExtraKey> extraKeys = new HashSet<>();
        if (extraKeysParcelable != null) {
            for (Parcelable extraKey : extraKeysParcelable) {
                extraKeys.add(ExtraKeyMapper.from((Bundle) extraKey));
            }
        }

        if (quantity == null ||
                price == null ||
                priceWithDiscountPosition == null
                ) {
            return null;
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
                alcoholByVolume == null ? null : new BigDecimal(alcoholByVolume),
                alcoholProductKindCode == null ? null : Long.valueOf(alcoholProductKindCode),
                tareVolume == null ? null : new BigDecimal(tareVolume),
                PrintGroupMapper.from(bundle.getBundle(KEY_PRINT_GROUP)),
                extraKeys
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
        bundle.putString(KEY_ALCOHOL_BY_VOLUME, position.getAlcoholByVolume() == null ? null : position.getAlcoholByVolume().toPlainString());
        bundle.putString(KEY_ALCOHOL_PRODUCT_KIND_CODE, position.getAlcoholProductKindCode() == null ? null : position.getAlcoholProductKindCode().toString());
        bundle.putString(KEY_TARE_VOLUME, position.getTareVolume() == null ? null : position.getTareVolume().toPlainString());
        bundle.putBundle(KEY_PRINT_GROUP, PrintGroupMapper.toBundle(position.getPrintGroup()));
        Parcelable[] extraKeys = new Parcelable[position.getExtraKeys().size()];
        Iterator<ExtraKey> it = position.getExtraKeys().iterator();
        for (int i = 0; i < extraKeys.length; i++) {
            extraKeys[i] = ExtraKeyMapper.toBundle(it.next());
        }
        bundle.putParcelableArray(KEY_EXTRA_KEYS, extraKeys);

        return bundle;
    }

    private PositionMapper() {
    }

}
