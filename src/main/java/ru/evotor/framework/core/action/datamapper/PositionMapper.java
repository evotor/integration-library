package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.evotor.framework.BundleUtils;
import ru.evotor.framework.Utils;
import ru.evotor.framework.inventory.AttributeValue;
import ru.evotor.framework.inventory.ProductType;
import ru.evotor.framework.receipt.ExtraKey;
import ru.evotor.framework.receipt.Measure;
import ru.evotor.framework.receipt.Position;
import ru.evotor.framework.receipt.TaxNumber;
import ru.evotor.framework.receipt.position.AgentRequisites;
import ru.evotor.framework.receipt.position.ImportationData;
import ru.evotor.framework.receipt.position.Mark;
import ru.evotor.framework.receipt.position.MarksCheckingInfo;
import ru.evotor.framework.receipt.position.PartialRealization;
import ru.evotor.framework.receipt.position.PreferentialMedicine;
import ru.evotor.framework.receipt.TimeRange;
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

    private static final String KEY_MEASURE_CODE = "measureCode";

    private static final String KEY_TAX_NUMBER = "taxNumber";

    private static final String KEY_BARCODE = "barcode";

    private static final String KEY_MARK = "mark";

    private static final String KEY_MARK_ENTITY = "markEntity";

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

    private static final String KEY_PREFERENTIAL_MEDICINE = "preferentialMedicine";

    private static final String KEY_CLASSIFICATION_CODE = "classificationCode";

    private static final String KEY_PARTIAL_REALIZATION = "partialRealization";

    private static final String KEY_IS_EXCISABLE = "is_excisable";

    private static final String KEY_MARKS_CHECK_INFO = "marks_check_info";
    private static final String KEY_IS_AGE_LIMITED = "is_age_limited";
    private static final String KEY_IS_MARK_SKIPPED = "is_mark_skipped";
    private static final String KEY_SALE_BAN_TIME = "sale_ban_time";

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
        int measureCode = bundle.getInt(KEY_MEASURE_CODE, Measure.UNKNOWN_MEASURE_CODE);
        TaxNumber taxNumber = TaxNumberMapper.from(bundle.getBundle(KEY_TAX_NUMBER));
        BigDecimal price = BundleUtils.getMoney(bundle, KEY_PRICE);
        BigDecimal priceWithDiscountPosition = BundleUtils.getMoney(bundle, KEY_PRICE_WITH_DISCOUNT_POSITION);
        BigDecimal quantity = BundleUtils.getQuantity(bundle, KEY_QUANTITY);
        String barcode = bundle.getString(KEY_BARCODE);
        Mark mark = readMarkFromBundle(bundle);
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

        PreferentialMedicine preferentialMedicine =
                PreferentialMedicine.from(bundle.getBundle(KEY_PREFERENTIAL_MEDICINE));

        PartialRealization partialRealization = PartialRealization.from(bundle.getBundle(KEY_PARTIAL_REALIZATION));
        Boolean isExcisable = (Boolean) bundle.getSerializable(KEY_IS_EXCISABLE);

        MarksCheckingInfo marksCheckingInfo = MarksCheckingInfo.from(bundle.getBundle(KEY_MARKS_CHECK_INFO));
        Boolean isAgeLimited = (Boolean) bundle.getSerializable(KEY_IS_AGE_LIMITED);
        Boolean isMarkSkipped = (Boolean) bundle.getSerializable(KEY_IS_MARK_SKIPPED);
        if (quantity == null ||
                price == null ||
                priceWithDiscountPosition == null
        ) {
            return null;
        }
        TimeRange saleBanTime = TimeRange.from(bundle.getBundle(KEY_SALE_BAN_TIME));

        Measure measure = new Measure(
                measureName,
                measurePrecision,
                measureCode
        );

        Position.Builder builder = Position.Builder.copyFrom(new Position(
                uuid,
                productUuid,
                productCode,
                productType,
                name,
                measure,
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
        builder.setPreferentialMedicine(preferentialMedicine);
        builder.setClassificationCode(classificationCode);
        builder.setPartialRealization(partialRealization);
        builder.setIsExcisable(isExcisable);
        builder.setMarksCheckingInfo(marksCheckingInfo);
        builder.setIsAgeLimited(isAgeLimited);
        builder.setIsMarkSkipped(isMarkSkipped);
        builder.setSaleBanTime(saleBanTime);
        return builder.build();
    }

    private static Mark readMarkFromBundle(Bundle bundle) {
        Mark mark = MarkMapper.fromBundle(bundle.getBundle(KEY_MARK_ENTITY));
        if (mark == null && bundle.containsKey(KEY_MARK)) {
            String rawMark = bundle.getString(KEY_MARK);
            if (rawMark != null) {
                mark = new Mark.RawMark(rawMark);
            }
        }
        return mark;
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
        bundle.putString(KEY_MEASURE_NAME, position.getMeasure().getName());
        bundle.putInt(KEY_MEASURE_PRECISION, position.getMeasure().getPrecision());
        bundle.putInt(KEY_MEASURE_CODE, position.getMeasure().getCode());
        bundle.putBundle(KEY_TAX_NUMBER, TaxNumberMapper.toBundle(position.getTaxNumber()));
        bundle.putString(KEY_PRICE, position.getPrice().toPlainString());
        bundle.putString(KEY_PRICE_WITH_DISCOUNT_POSITION, position.getPriceWithDiscountPosition().toPlainString());
        bundle.putString(KEY_QUANTITY, position.getQuantity().toPlainString());
        bundle.putString(KEY_BARCODE, position.getBarcode());
        putMarkToBundle(position, bundle);
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

        final PreferentialMedicine preferentialMedicine = position.getPreferentialMedicine();
        bundle.putBundle(KEY_PREFERENTIAL_MEDICINE, preferentialMedicine != null ? preferentialMedicine.toBundle() : null);

        final BigDecimal excise = position.getExcise();
        if (excise != null) {
            bundle.putString(KEY_EXCISE, excise.toPlainString());
        }
        final String classificationCode = position.getClassificationCode();
        if (classificationCode != null) {
            bundle.putString(KEY_CLASSIFICATION_CODE, classificationCode);
        }

        final PartialRealization partialRealization = position.getPartialRealization();
        bundle.putBundle(KEY_PARTIAL_REALIZATION, partialRealization != null ? partialRealization.toBundle() : null);

        bundle.putSerializable(KEY_IS_EXCISABLE, position.getIsExcisable());

        final MarksCheckingInfo marksCheckingInfo = position.getMarksCheckingInfo();
        bundle.putBundle(KEY_MARKS_CHECK_INFO, marksCheckingInfo != null ? marksCheckingInfo.toBundle() : null);
        bundle.putSerializable(KEY_IS_AGE_LIMITED, position.getIsAgeLimited());
        bundle.putSerializable(KEY_IS_MARK_SKIPPED, position.getIsMarkSkipped());
        bundle.putBundle(KEY_SALE_BAN_TIME, position.getSaleBanTime() != null ? position.getSaleBanTime().toBundle() : null);
        return bundle;
    }

    private static void putMarkToBundle(Position position, Bundle bundle) {
        Mark mark = position.getMark();
        String rawMark;
        if (mark instanceof Mark.RawMark) {
            rawMark = ((Mark.RawMark) mark).getValue();
        } else if (mark instanceof Mark.MarkByFiscalTags) {
            rawMark = ((Mark.MarkByFiscalTags) mark).getProductCode();
        } else {
            rawMark = null;
        }
        bundle.putString(KEY_MARK, rawMark);
        bundle.putBundle(KEY_MARK_ENTITY, MarkMapper.toBundle(mark));
    }
}
