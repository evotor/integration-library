package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ru.evotor.framework.calculator.MoneyCalculator;
import ru.evotor.framework.calculator.PercentCalculator;
import ru.evotor.framework.inventory.ProductType;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public class Position implements Parcelable {
    /**
     * UUID позиции
     */
    private String uuid;
    /**
     * UUID товара.
     */
    private String productUuid;
    /**
     * Код товара.
     */
    private String productCode;
    /**
     * Вид товара.
     */
    private ProductType productType;
    /**
     * Наименование.
     */
    private String name;
    /**
     * Наименование единицы измерения.
     */
    private String measureName;
    /**
     * Точность единицы измерения.
     */
    private int measurePrecision;
    /**
     * Цена без скидок.
     */
    private BigDecimal price;
    /**
     * Цена с учетом скидки на позицию.
     */
    private BigDecimal priceWithDiscountPosition;
    /**
     * Количество.
     */
    private BigDecimal quantity;
    /**
     * Штрихкод, по которому товар был найден.
     */
    private String barcode;
    /**
     * Алкогольная марка.
     */
    private String mark;
    /**
     * Крепость.
     */
    private BigDecimal alcoholByVolume;
    /**
     * Код вида продукции ФСРАР.
     */
    private Long alcoholProductKindCode;
    /**
     * Объём тары.
     */
    private BigDecimal tareVolume;
    /**
     * Группа печати.
     */
    private PrintGroup printGroup;
    /**
     * Экстра ключи
     */
    private List<ExtraKey> extraKeys = new ArrayList<>();
    /**
     * В качестве ключа используется TaxNumber
     */
    private Map<TaxNumber, Tax> taxes = new LinkedHashMap<>();

    public Position(
            String uuid,
            String productUuid,
            String productCode,
            ProductType productType,
            String name,
            String measureName,
            int measurePrecision,
            BigDecimal price,
            BigDecimal priceWithDiscountPosition,
            BigDecimal quantity,
            String barcode,
            String mark,
            BigDecimal alcoholByVolume,
            Long alcoholProductKindCode,
            BigDecimal tareVolume,
            PrintGroup printGroup,
            List<ExtraKey> extraKeys,
            Map<TaxNumber, Tax> taxes
    ) {
        this.uuid = uuid;
        this.productUuid = productUuid;
        this.productCode = productCode;
        this.productType = productType;
        this.name = name;
        this.measureName = measureName;
        this.measurePrecision = measurePrecision;
        this.price = price;
        this.priceWithDiscountPosition = priceWithDiscountPosition;
        this.quantity = quantity;
        this.barcode = barcode;
        this.mark = mark;
        this.alcoholByVolume = alcoholByVolume;
        this.alcoholProductKindCode = alcoholProductKindCode;
        this.tareVolume = tareVolume;
        this.printGroup = printGroup;
        if (extraKeys != null) {
            this.extraKeys.addAll(extraKeys);
        }
        if (taxes != null) {
            this.taxes.putAll(taxes);
        }
    }

    public Position(Position position) {
        this(
                position.getUuid(),
                position.getProductUuid(),
                position.getProductCode(),
                position.getProductType(),
                position.getName(),
                position.getMeasureName(),
                position.getMeasurePrecision(),
                position.getPrice(),
                position.getPriceWithDiscountPosition(),
                position.getQuantity(),
                position.getBarcode(),
                position.getMark(),
                position.getAlcoholByVolume(),
                position.getAlcoholProductKindCode(),
                position.getTareVolume(),
                position.getPrintGroup(),
                position.getExtraKeys(),
                position.getTaxes()
        );
    }

    /**
     * Возвращает сумму без учета скидок.
     *
     * @return сумма без учета скидок.
     */
    public BigDecimal getTotalWithoutDiscounts() {
        return MoneyCalculator.multiply(price, quantity);
    }

    /**
     * Возвращает сумму без учета скидки на чек.
     *
     * @return сумма без учета скидки на чек.
     */
    public BigDecimal getTotalWithoutDocumentDiscount() {
        return MoneyCalculator.multiply(priceWithDiscountPosition, quantity);
    }

    /**
     * Возвращает процент скидки на позицию.
     *
     * @return процент скидки на позицию.
     */
    public BigDecimal getDiscountPercents() {
        if (getDiscountPositionSum().compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return PercentCalculator.calcPercent(getTotalWithoutDiscounts(), getDiscountPositionSum());
    }

    /**
     * Возвращает сумму скидки на позицию.
     *
     * @return сумма скидки на позицию.
     */
    public BigDecimal getDiscountPositionSum() {
        return MoneyCalculator.subtract(getTotalWithoutDiscounts(), getTotalWithoutDocumentDiscount());
    }

    /**
     * Возвращает сумму c учетом скидки на чек и на позицию.
     *
     * @param discountDocumentPositionSum сумма скидки на чек, распределенная на эту позицию
     * @return сумма с учетом скидки на чек и на позицию.
     */
    public BigDecimal getTotal(BigDecimal discountDocumentPositionSum) {
        return MoneyCalculator.subtract(MoneyCalculator.multiply(priceWithDiscountPosition, quantity),
                discountDocumentPositionSum);
    }

    public String getUuid() {
        return uuid;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public String getProductCode() {
        return productCode;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getName() {
        return name;
    }

    public String getMeasureName() {
        return measureName;
    }

    public int getMeasurePrecision() {
        return measurePrecision;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPriceWithDiscountPosition() {
        return priceWithDiscountPosition;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getMark() {
        return mark;
    }

    public BigDecimal getAlcoholByVolume() {
        return alcoholByVolume;
    }

    public Long getAlcoholProductKindCode() {
        return alcoholProductKindCode;
    }

    public BigDecimal getTareVolume() {
        return tareVolume;
    }

    public PrintGroup getPrintGroup() {
        return printGroup;
    }

    public List<ExtraKey> getExtraKeys() {
        return extraKeys;
    }

    public Map<TaxNumber, Tax> getTaxes() {
        return taxes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.productUuid);
        dest.writeString(this.productCode);
        dest.writeInt(this.productType == null ? -1 : this.productType.ordinal());
        dest.writeString(this.name);
        dest.writeString(this.measureName);
        dest.writeInt(this.measurePrecision);
        dest.writeSerializable(this.price);
        dest.writeSerializable(this.priceWithDiscountPosition);
        dest.writeSerializable(this.quantity);
        dest.writeString(this.barcode);
        dest.writeString(this.mark);
        dest.writeSerializable(this.alcoholByVolume);
        dest.writeValue(this.alcoholProductKindCode);
        dest.writeSerializable(this.tareVolume);
        dest.writeParcelable(this.printGroup, flags);
        dest.writeList(this.extraKeys);
        dest.writeInt(this.taxes.size());
        for (Map.Entry<TaxNumber, Tax> entry : this.taxes.entrySet()) {
            dest.writeInt(entry.getKey() == null ? -1 : entry.getKey().ordinal());
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    protected Position(Parcel in) {
        this.uuid = in.readString();
        this.productUuid = in.readString();
        this.productCode = in.readString();
        int tmpProductType = in.readInt();
        this.productType = tmpProductType == -1 ? null : ProductType.values()[tmpProductType];
        this.name = in.readString();
        this.measureName = in.readString();
        this.measurePrecision = in.readInt();
        this.price = (BigDecimal) in.readSerializable();
        this.priceWithDiscountPosition = (BigDecimal) in.readSerializable();
        this.quantity = (BigDecimal) in.readSerializable();
        this.barcode = in.readString();
        this.mark = in.readString();
        this.alcoholByVolume = (BigDecimal) in.readSerializable();
        this.alcoholProductKindCode = (Long) in.readValue(Long.class.getClassLoader());
        this.tareVolume = (BigDecimal) in.readSerializable();
        this.printGroup = in.readParcelable(PrintGroup.class.getClassLoader());
        this.extraKeys = new ArrayList<>();
        in.readList(this.extraKeys, ExtraKey.class.getClassLoader());
        int taxesSize = in.readInt();
        this.taxes = new HashMap<>(taxesSize);
        for (int i = 0; i < taxesSize; i++) {
            int tmpKey = in.readInt();
            TaxNumber key = tmpKey == -1 ? null : TaxNumber.values()[tmpKey];
            Tax value = in.readParcelable(Tax.class.getClassLoader());
            this.taxes.put(key, value);
        }
    }

    public static final Creator<Position> CREATOR = new Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel source) {
            return new Position(source);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };

    public static final class Builder {
        private Position position;

        public Builder(Position position) {
            this.position = new Position(position);
        }

        public Builder setQuantity(BigDecimal quantity) {
            position.quantity = quantity;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            position.price = price;
            return this;
        }

        public Builder setPriceWithDiscountPosition(BigDecimal priceWithDiscountPosition) {
            position.priceWithDiscountPosition = priceWithDiscountPosition;
            return this;
        }

        public Builder setMark(String mark) {
            position.mark = mark;
            return this;
        }

        public Builder setExtraKeys(List<ExtraKey> extraKeys) {
            position.extraKeys = extraKeys;
            return this;
        }

        public Position build() {
            return new Position(position);
        }
    }
}
