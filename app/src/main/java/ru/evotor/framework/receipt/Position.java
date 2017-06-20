package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.evotor.framework.calculator.MoneyCalculator;
import ru.evotor.framework.calculator.PercentCalculator;
import ru.evotor.framework.inventory.ProductType;

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
     * Экстра ключи
     */
    private Set<ExtraKey> extraKeys = new HashSet<>();
    /*
     * Подпозиции (модификаторы)
     */
    private List<Position> subPositions = new ArrayList<>();

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
            Set<ExtraKey> extraKeys,
            List<Position> subPositions
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
        if (extraKeys != null) {
            this.extraKeys.addAll(extraKeys);
        }
        this.subPositions = subPositions;
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
                position.getExtraKeys(),
                position.getSubPosition()
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

    /**
     * Возвращает сумму без учета скидки на чек с учётом всех подпозиций.
     *
     * @return сумма без учета скидки на чек с учётом всех подпозиций.
     */
    public BigDecimal getTotalWithSubPositionsAndWithoutDocumentDiscount() {
        BigDecimal sum = getTotalWithoutDocumentDiscount();
        if (getSubPosition() != null) {
            for (Position subPosition : getSubPosition()) {
                sum = sum.add(subPosition.getTotalWithoutDocumentDiscount());
            }
        }
        return sum;
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

    public Set<ExtraKey> getExtraKeys() {
        return extraKeys;
    }

    public List<Position> getSubPosition() {
        return subPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (measurePrecision != position.measurePrecision) return false;
        if (uuid != null ? !uuid.equals(position.uuid) : position.uuid != null) return false;
        if (productUuid != null ? !productUuid.equals(position.productUuid) : position.productUuid != null)
            return false;
        if (productCode != null ? !productCode.equals(position.productCode) : position.productCode != null)
            return false;
        if (productType != position.productType) return false;
        if (name != null ? !name.equals(position.name) : position.name != null) return false;
        if (measureName != null ? !measureName.equals(position.measureName) : position.measureName != null)
            return false;
        if (price != null ? !price.equals(position.price) : position.price != null) return false;
        if (priceWithDiscountPosition != null ? !priceWithDiscountPosition.equals(position.priceWithDiscountPosition) : position.priceWithDiscountPosition != null)
            return false;
        if (quantity != null ? !quantity.equals(position.quantity) : position.quantity != null)
            return false;
        if (barcode != null ? !barcode.equals(position.barcode) : position.barcode != null)
            return false;
        if (mark != null ? !mark.equals(position.mark) : position.mark != null) return false;
        if (alcoholByVolume != null ? !alcoholByVolume.equals(position.alcoholByVolume) : position.alcoholByVolume != null)
            return false;
        if (alcoholProductKindCode != null ? !alcoholProductKindCode.equals(position.alcoholProductKindCode) : position.alcoholProductKindCode != null)
            return false;
        if (tareVolume != null ? !tareVolume.equals(position.tareVolume) : position.tareVolume != null)
            return false;
        if (extraKeys != null ? !extraKeys.equals(position.extraKeys) : position.extraKeys != null)
            return false;
        return subPositions != null ? subPositions.equals(position.subPositions) : position.subPositions == null;

    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (productUuid != null ? productUuid.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (measureName != null ? measureName.hashCode() : 0);
        result = 31 * result + measurePrecision;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (priceWithDiscountPosition != null ? priceWithDiscountPosition.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (alcoholByVolume != null ? alcoholByVolume.hashCode() : 0);
        result = 31 * result + (alcoholProductKindCode != null ? alcoholProductKindCode.hashCode() : 0);
        result = 31 * result + (tareVolume != null ? tareVolume.hashCode() : 0);
        result = 31 * result + (extraKeys != null ? extraKeys.hashCode() : 0);
        result = 31 * result + (subPositions != null ? subPositions.hashCode() : 0);
        return result;
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
        dest.writeList(new ArrayList<>(this.extraKeys));
        dest.writeTypedList(this.subPositions);
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
        List<ExtraKey> extraKeyList = new ArrayList<>();
        in.readList(extraKeyList, ExtraKey.class.getClassLoader());
        this.extraKeys.addAll(extraKeyList);
        List<Position> subPositions = new ArrayList<>();
        in.readTypedList(subPositions, Position.CREATOR);
        this.subPositions = subPositions;
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
        public static Builder newInstance(
                @Nullable String uuid,
                @Nullable String productUuid,
                @NonNull String name,
                @NonNull String measureName,
                int measurePrecision,
                @NonNull BigDecimal price,
                @NonNull BigDecimal quantity
        ) {
            return new Builder(
                    new Position(
                            uuid,
                            productUuid,
                            null,
                            ProductType.NORMAL,
                            name,
                            measureName,
                            measurePrecision,
                            price,
                            price,
                            quantity,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null
                    )
            );
        }

        public static Builder copyFrom(Position position) {
            return new Builder(new Position(position));
        }

        private Position position;

        @Deprecated
        public Builder(Position position) {
            this.position = new Position(position);
        }

        public Builder toAlcoholMarked(
                @NonNull String mark,
                @NonNull BigDecimal alcoholByVolume,
                @NonNull Long alcoholProductKindCode,
                @NonNull BigDecimal tareVolume
        ) {
            position.productType = ProductType.ALCOHOL_MARKED;
            position.mark = mark;
            position.alcoholByVolume = alcoholByVolume;
            position.alcoholProductKindCode = alcoholProductKindCode;
            position.tareVolume = tareVolume;
            return this;
        }

        public Builder toAlcoholNotMarked(
                @NonNull BigDecimal alcoholByVolume,
                @NonNull Long alcoholProductKindCode,
                @NonNull BigDecimal tareVolume
        ) {
            position.productType = ProductType.ALCOHOL_NOT_MARKED;
            position.alcoholByVolume = alcoholByVolume;
            position.alcoholProductKindCode = alcoholProductKindCode;
            position.tareVolume = tareVolume;
            return this;
        }

        public Builder setUuid(String uuid) {
            position.uuid = uuid;
            return this;
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

        public Builder setExtraKeys(Set<ExtraKey> extraKeys) {
            position.extraKeys = extraKeys;
            return this;
        }

        public Builder setMeasureName(String measureName) {
            position.measureName = measureName;
            return this;
        }

        public Builder setMeasurePrecision(int measurePrecision) {
            position.measurePrecision = measurePrecision;
            return this;
        }

        public Builder setSubPositions(List<Position> subPositions) {
            position.subPositions = subPositions;
            return this;
        }

        public Builder setBarcode(String barcode) {
            position.barcode = barcode;
            return this;
        }

        public Position build() {
            return new Position(position);
        }
    }
}
