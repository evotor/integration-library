package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.evotor.framework.Utils;
import ru.evotor.framework.inventory.AttributeValue;
import ru.evotor.framework.inventory.ProductType;
import ru.evotor.framework.receipt.ExtraKey;
import ru.evotor.framework.receipt.Position;
import ru.evotor.framework.receipt.TaxNumber;
import ru.evotor.framework.receipt.position.AgentRequisites;
import ru.evotor.framework.receipt.position.ImportationData;
import ru.evotor.framework.receipt.position.SettlementMethod;

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
    private static final String KEY_TAX_NUMBER = "taxNumber";
    private static final String KEY_BARCODE = "barcode";
    private static final String KEY_MARK = "mark";
    private static final String KEY_ALCOHOL_BY_VOLUME = "alcoholByVolume";
    private static final String KEY_ALCOHOL_PRODUCT_KIND_CODE = "alcoholProductKindCode";
    private static final String KEY_TARE_VOLUME = "tareVolume";
    private static final String KEY_EXTRA_KEYS = "extraKeys";
    private static final String KEY_SUB_POSITION = "subPosition";
    private static final String KEY_ATTRIBUTES = "attributes";
    private static final String KEY_SETTLEMENT_METHOD = "settlementMethod";
    private static final String KEY_AGENT_REQUISITES = "agentRequisites";
    private static final String KEY_IMPORTATION_DATA = "importationData";
    private static final String KEY_EXCISE = "excise";
    private static final String KEY_CLASSIFICATION_CODE = "classificationCode";

    @Nullable
    public static Position from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String uuid = bundle.getString(KEY_UUID);
        String productUuid = bundle.getString(KEY_PRODUCT_UUID);
        String productCode = bundle.getString(KEY_PRODUCT_CODE);
        ProductType productType = Utils.safeValueOf(ProductType.class, bundle.getString(KEY_PRODUCT_TYPE), ProductType.NORMAL);
        String name = bundle.getString(KEY_NAME);
        String measureName = bundle.getString(KEY_MEASURE_NAME);
        int measurePrecision = bundle.getInt(KEY_MEASURE_PRECISION, 0);
        TaxNumber taxNumber = TaxNumberMapper.from(bundle.getBundle(KEY_TAX_NUMBER));
        BigDecimal price = BundleUtils.getMoney(bundle, KEY_PRICE);
        BigDecimal priceWithDiscountPosition = BundleUtils.getMoney(bundle, KEY_PRICE_WITH_DISCOUNT_POSITION);
        BigDecimal quantity = BundleUtils.getQuantity(bundle, KEY_QUANTITY);
        String barcode = bundle.getString(KEY_BARCODE);
        String mark = bundle.getString(KEY_MARK);
        String alcoholByVolume = bundle.getString(KEY_ALCOHOL_BY_VOLUME);
        String alcoholProductKindCode = bundle.getString(KEY_ALCOHOL_PRODUCT_KIND_CODE);
        String tareVolume = bundle.getString(KEY_TARE_VOLUME);
        String classificationCode = bundle.getString(KEY_CLASSIFICATION_CODE);

        Parcelable[] extraKeysParcelable = bundle.getParcelableArray(KEY_EXTRA_KEYS);
        Set<ExtraKey> extraKeys = new HashSet<>();
        if (extraKeysParcelable != null) {
            for (Parcelable extraKey : extraKeysParcelable) {
                extraKeys.add(ExtraKeyMapper.from((Bundle) extraKey));
            }
        }

        List<Position> subPositions = new ArrayList<>();
        Parcelable[] parcelablesSubPositions = bundle.getParcelableArray(KEY_SUB_POSITION);
        if (parcelablesSubPositions != null) {
            for (Parcelable parcelable : parcelablesSubPositions) {
                if (parcelable instanceof Bundle) {
                    subPositions.add(from((Bundle) parcelable));
                }
            }
        }

        Map<String, AttributeValue> attributes =
                PositionAttributesMapper.fromBundle(bundle.getBundle(KEY_ATTRIBUTES));

        SettlementMethod settlementMethod =
                SettlementMethodMapper.fromBundle(bundle.getBundle(KEY_SETTLEMENT_METHOD));

        AgentRequisites agentRequisites =
                AgentRequisites.Companion.from(bundle.getBundle(KEY_AGENT_REQUISITES));

        final ImportationData importationData =
                ImportationData.from(bundle.getBundle(KEY_IMPORTATION_DATA));

        final BigDecimal excise = BundleUtils.getMoney(bundle, KEY_EXCISE);
        if (quantity == null ||
                price == null ||
                priceWithDiscountPosition == null
        ) {
            return null;
        }

        Position.Builder builder = Position.Builder.copyFrom(new Position(
                uuid,
                productUuid,
                productCode,
                productType,
                name,
                measureName,
                measurePrecision,
                taxNumber,
                price,
                priceWithDiscountPosition,
                quantity,
                barcode,
                mark,
                alcoholByVolume == null ? null : new BigDecimal(alcoholByVolume),
                alcoholProductKindCode == null ? null : Long.valueOf(alcoholProductKindCode),
                tareVolume == null ? null : new BigDecimal(tareVolume),
                extraKeys,
                subPositions
        ));
        builder.setAttributes(attributes);
        builder.setSettlementMethod(settlementMethod);
        builder.setAgentRequisites(agentRequisites);
        builder.setImportationData(importationData);
        builder.setExcise(excise);
        builder.setClassificationCode(classificationCode);
        return builder.build();
    }

    @Nullable
    public static Bundle toBundle(@Nullable Position position) {
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
        bundle.putBundle(KEY_TAX_NUMBER, TaxNumberMapper.toBundle(position.getTaxNumber()));
        bundle.putString(KEY_PRICE, position.getPrice().toPlainString());
        bundle.putString(KEY_PRICE_WITH_DISCOUNT_POSITION, position.getPriceWithDiscountPosition().toPlainString());
        bundle.putString(KEY_QUANTITY, position.getQuantity().toPlainString());
        bundle.putString(KEY_BARCODE, position.getBarcode());
        bundle.putString(KEY_MARK, position.getMark());
        bundle.putString(KEY_ALCOHOL_BY_VOLUME, position.getAlcoholByVolume() == null ? null : position.getAlcoholByVolume().toPlainString());
        bundle.putString(KEY_ALCOHOL_PRODUCT_KIND_CODE, position.getAlcoholProductKindCode() == null ? null : position.getAlcoholProductKindCode().toString());
        bundle.putString(KEY_TARE_VOLUME, position.getTareVolume() == null ? null : position.getTareVolume().toPlainString());
        Parcelable[] extraKeys = new Parcelable[position.getExtraKeys().size()];
        Iterator<ExtraKey> it = position.getExtraKeys().iterator();
        for (int i = 0; i < extraKeys.length; i++) {
            extraKeys[i] = ExtraKeyMapper.toBundle(it.next());
        }
        bundle.putParcelableArray(KEY_EXTRA_KEYS, extraKeys);

        List<Position> subPositions = position.getSubPositions();
        List<Parcelable> subPositionsParcelablesList = subPositions == null ? null : new ArrayList<Parcelable>();
        Parcelable[] subPositionsParcelables = null;
        if (subPositions != null) {
            for (Position subPosition : subPositions) {
                subPositionsParcelablesList.add(toBundle(subPosition));
            }
            subPositionsParcelables = subPositionsParcelablesList.toArray(new Parcelable[]{});
        }
        bundle.putParcelableArray(KEY_SUB_POSITION, subPositionsParcelables);

        bundle.putBundle(KEY_ATTRIBUTES, PositionAttributesMapper.toBundle(position.getAttributes()));
        bundle.putBundle(KEY_SETTLEMENT_METHOD, SettlementMethodMapper.toBundle(position.getSettlementMethod()));
        bundle.putBundle(KEY_AGENT_REQUISITES, position.getAgentRequisites() != null ? position.getAgentRequisites().toBundle() : null);

        final ImportationData importationData = position.getImportationData();
        final Bundle importationDataBundle =
                importationData != null ? importationData.toBundle() : null;
        bundle.putBundle(KEY_IMPORTATION_DATA, importationDataBundle);

        final BigDecimal excise = position.getExcise();
        if (excise != null) {
            bundle.putString(KEY_EXCISE, excise.toPlainString());
        }
        final String classificationCode = position.getClassificationCode();
        if (classificationCode != null) {
            bundle.putString(KEY_CLASSIFICATION_CODE, classificationCode);
        }
        return bundle;
    }

}
