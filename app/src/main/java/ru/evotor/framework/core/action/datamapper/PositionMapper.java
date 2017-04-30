package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;

import java.math.BigDecimal;

import ru.evotor.framework.Utils;
import ru.evotor.framework.inventory.Measure;
import ru.evotor.framework.inventory.ProductType;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public final class PositionMapper {
    private static final String KEY_UUID = "uuid";
    private static final String KEY_PRODUCT_UUID = "productUuid";
    private static final String KEY_PRODUCT_TYPE = "productType";
    private static final String KEY_MEASURE = "measure";
    private static final String KEY_PRICE = "price";
    private static final String KEY_PRICE_WITH_DISCOUNT_POSITION = "priceWithDiscountPosition";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_NAME = "name";
    private static final String KEY_BARCODE = "barcode";
    private static final String KEY_MARK = "mark";
    private static final String KEY_ALCOHOL_BY_VOLUME = "alcoholByVolume";
    private static final String KEY_ALCOHOL_PRODUCT_KIND_CODE = "alcoholProductKindCode";
    private static final String KEY_TARE_VOLUME = "tareVolume";
    private static final String KEY_PRINT_GROUP = "printGroup";

    public static Position from(Bundle bundle) {
        String uuid = bundle.getString(KEY_UUID);
        String productUuid = bundle.getString(KEY_PRODUCT_UUID);
        ProductType productType = Utils.safeValueOf(ProductType.class, bundle.getString(KEY_PRODUCT_TYPE), ProductType.NORMAL);
        String name = bundle.getString(KEY_NAME);
        Measure measure = MeasureMapper.from(bundle.getBundle(KEY_MEASURE));
        String price = bundle.getString(KEY_PRICE);
        String priceWithDiscountPosition = bundle.getString(KEY_PRICE_WITH_DISCOUNT_POSITION);
        String quantity = bundle.getString(KEY_QUANTITY);
        String barcode = bundle.getString(KEY_BARCODE);
        String mark = bundle.getString(KEY_MARK);
        String alcoholByVolume = bundle.getString(KEY_ALCOHOL_BY_VOLUME);
        String alcoholProductKindCode = bundle.getString(KEY_ALCOHOL_PRODUCT_KIND_CODE);
        String tareVolume = bundle.getString(KEY_TARE_VOLUME);

        return new Position(
                uuid,
                productUuid,
                productType,
                name,
                measure,
                new BigDecimal(price),
                new BigDecimal(priceWithDiscountPosition),
                new BigDecimal(quantity),
                barcode,
                mark,
                new BigDecimal(alcoholByVolume),
                Long.valueOf(alcoholProductKindCode),
                new BigDecimal(tareVolume),
                PrintGroupMapper.from(bundle.getBundle(KEY_PRINT_GROUP))

        );
    }

    public static Bundle toBundle(Position position) {
        if (position == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_UUID, position.getUuid());
        bundle.putString(KEY_PRODUCT_UUID, position.getProductUuid());
        bundle.putString(KEY_PRODUCT_TYPE, position.getProductType().toString());
        bundle.putString(KEY_NAME, position.getName());
        bundle.putBundle(KEY_MEASURE, MeasureMapper.toBundle(position.getMeasure()));
        bundle.putString(KEY_PRICE, position.getPrice().toPlainString());
        bundle.putString(KEY_PRICE_WITH_DISCOUNT_POSITION, position.getPriceWithDiscountPosition().toPlainString());
        bundle.putString(KEY_QUANTITY, position.getQuantity().toPlainString());
        bundle.putString(KEY_BARCODE, position.getBarcode());
        bundle.putString(KEY_MARK, position.getMark());
        bundle.putString(KEY_ALCOHOL_BY_VOLUME, position.getAlcoholByVolume().toPlainString());
        bundle.putString(KEY_ALCOHOL_PRODUCT_KIND_CODE, position.getAlcoholProductKindCode().toString());
        bundle.putString(KEY_TARE_VOLUME, position.getTareVolume().toPlainString());
        bundle.putBundle(KEY_PRINT_GROUP, PrintGroupMapper.toBundle(position.getPrintGroup()));

        return bundle;
    }

    private PositionMapper() {
    }

}
