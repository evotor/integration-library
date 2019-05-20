package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import ru.evotor.framework.calculator.MoneyCalculator;
import ru.evotor.framework.calculator.PercentCalculator;
import ru.evotor.framework.inventory.AttributeValue;
import ru.evotor.framework.inventory.ProductItem;
import ru.evotor.framework.inventory.ProductType;
import ru.evotor.framework.receipt.position.SettlementMethod;
import ru.evotor.framework.receipt.position.AgentRequisites;

/**
 * Позиция чека.
 */
public class Position implements Parcelable {
    /**
     * Текущая версия объекта Position
     */
    private static final int VERSION = 3;
    /**
     * Magic number для идентификации использования версионирования объекта
     */
    private static final int MAGIC_NUMBER = 8800;
    /**
     * UUID позиции
     */
    private String uuid;
    /**
     * UUID товара.
     */
    @Nullable
    private String productUuid;
    /**
     * Код товара.
     */
    @Nullable
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
     * НДС
     */
    @Nullable
    private TaxNumber taxNumber;
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
    @Nullable
    private String barcode;
    /**
     * Алкогольная или табачная марка. Марка записывается в реквизит "код товара" (тег 1162).
     */
    private String mark;
    /**
     * Крепость.
     */
    @Nullable
    private BigDecimal alcoholByVolume;
    /**
     * Код вида продукции ФСРАР.
     */
    @Nullable
    private Long alcoholProductKindCode;
    /**
     * Объём тары.
     */
    @Nullable
    private BigDecimal tareVolume;
    /**
     * Экстра ключи
     */
    private Set<ExtraKey> extraKeys = new HashSet<>();
    /**
     * Подпозиции (модификаторы)
     */
    private List<Position> subPositions = new ArrayList<>();

    /**
     * Атрибуты
     * ключ - id словаря для вариантов аттрибута
     * значение - выбранный элемент из словаря аттрибутов
     */
    @Nullable
    private Map<String, AttributeValue> attributes;

    /**
     * Признак способа расчета
     * По умолчанию это 'Полный расчет'
     */
    @NonNull
    private SettlementMethod settlementMethod = new SettlementMethod.FullSettlement();

    /**
     * Реквизиты агента
     */
    @Nullable
    private AgentRequisites agentRequisites;

    /**
     * Deprecated since 16.02.2018. Use position Builder.
     */
    @Deprecated
    public Position(
            String uuid,
            @Nullable String productUuid,
            @Nullable String productCode,
            ProductType productType,
            String name,
            String measureName,
            int measurePrecision,
            BigDecimal price,
            BigDecimal priceWithDiscountPosition,
            BigDecimal quantity,
            @Nullable String barcode,
            String mark,
            @Nullable BigDecimal alcoholByVolume,
            @Nullable Long alcoholProductKindCode,
            @Nullable BigDecimal tareVolume,
            Set<ExtraKey> extraKeys,
            List<Position> subPositions
    ) {
        this(
                uuid,
                productUuid,
                productCode,
                productType,
                name,
                measureName,
                measurePrecision,
                null,
                price,
                priceWithDiscountPosition,
                quantity,
                barcode,
                mark,
                alcoholByVolume,
                alcoholProductKindCode,
                tareVolume, extraKeys,
                subPositions
        );
    }

    public Position(
            String uuid,
            @Nullable String productUuid,
            @Nullable String productCode,
            ProductType productType,
            String name,
            String measureName,
            int measurePrecision,
            @Nullable TaxNumber taxNumber,
            BigDecimal price,
            BigDecimal priceWithDiscountPosition,
            BigDecimal quantity,
            @Nullable String barcode,
            String mark,
            @Nullable BigDecimal alcoholByVolume,
            @Nullable Long alcoholProductKindCode,
            @Nullable BigDecimal tareVolume,
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
        this.taxNumber = taxNumber;
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
        this.subPositions = subPositions != null ? new ArrayList<>(subPositions) : null;
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
                position.getTaxNumber(),
                position.getPrice(),
                position.getPriceWithDiscountPosition(),
                position.getQuantity(),
                position.getBarcode(),
                position.getMark(),
                position.getAlcoholByVolume(),
                position.getAlcoholProductKindCode(),
                position.getTareVolume(),
                position.getExtraKeys(),
                position.getSubPositions()
        );
        this.attributes = position.getAttributes();
        this.settlementMethod = position.getSettlementMethod();
        this.agentRequisites = position.getAgentRequisites();
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
        if (getSubPositions() != null) {
            for (Position subPosition : getSubPositions()) {
                sum = sum.add(subPosition.getTotalWithoutDocumentDiscount());
            }
        }
        return sum;
    }

    /**
     * @return UUID позиции
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @return UUID товара.
     */
    @Nullable
    public String getProductUuid() {
        return productUuid;
    }

    /**
     * @return Код товара.
     */
    @Nullable
    public String getProductCode() {
        return productCode;
    }

    /**
     * @return Вид товара.
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * @return Наименование
     */
    public String getName() {
        return name;
    }

    /**
     * @return Наименование единицы измерения.
     */
    public String getMeasureName() {
        return measureName;
    }

    /**
     * @return Точность единицы измерения.
     */
    public int getMeasurePrecision() {
        return measurePrecision;
    }

    /**
     * @return Цена без скидок.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @return Налоговая ставка.
     */
    @Nullable
    public TaxNumber getTaxNumber() {
        return taxNumber;
    }

    /**
     * @return Цена с учетом скидки на позицию.
     */
    public BigDecimal getPriceWithDiscountPosition() {
        return priceWithDiscountPosition;
    }

    /**
     * @return Количество.
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * @return Штрихкод, по которому товар был найден.
     */
    @Nullable
    public String getBarcode() {
        return barcode;
    }

    /**
     * @return Алкогольная или табачная марка. Марка записывается в реквизит "код товара" (тег 1162).
     */
    @Nullable
    public String getMark() {
        return mark;
    }

    /**
     * @return Крепость.
     */
    @Nullable
    public BigDecimal getAlcoholByVolume() {
        return alcoholByVolume;
    }

    /**
     * @return Код вида продукции ФСРАР.
     */
    @Nullable
    public Long getAlcoholProductKindCode() {
        return alcoholProductKindCode;
    }

    /**
     * @return Объём тары.
     */
    @Nullable
    public BigDecimal getTareVolume() {
        return tareVolume;
    }

    /**
     * @return Экстра ключи.
     */
    public Set<ExtraKey> getExtraKeys() {
        return extraKeys;
    }

    /**
     * @return Подпозиции (модификаторы).
     */
    public List<Position> getSubPositions() {
        return subPositions;
    }

    /**
     * @return значения атрибутов позиции
     */
    @Nullable
    public Map<String, AttributeValue> getAttributes() {
        return attributes;
    }

    /**
     * @return признак способа расчета
     */
    @NonNull
    public SettlementMethod getSettlementMethod() {
        return settlementMethod;
    }

    /**
     * @return Агентские реквизиты
     */
    @Nullable
    public AgentRequisites getAgentRequisites() {
        return agentRequisites;
    }

    @Override
    public boolean equals(Object o) {
        return equals(o, false);
    }

    public boolean equalsExceptQuantity(Object o) {
        return equals(o, true);
    }

    private boolean equals(Object o, boolean exceptQuantity) {
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
        if (taxNumber != position.taxNumber) return false;
        if ((price != null ? price : BigDecimal.ZERO).compareTo(position.price != null ? position.price : BigDecimal.ZERO) != 0)
            return false;
        if ((priceWithDiscountPosition != null ? priceWithDiscountPosition : BigDecimal.ZERO)
                .compareTo(position.priceWithDiscountPosition != null ? position.priceWithDiscountPosition : BigDecimal.ZERO) != 0)
            return false;
        if (!exceptQuantity && (quantity != null ? quantity : BigDecimal.ZERO).compareTo(position.quantity != null ? position.quantity : BigDecimal.ZERO) != 0)
            return false;
        if (barcode != null ? !barcode.equals(position.barcode) : position.barcode != null)
            return false;
        if (mark != null ? !mark.equals(position.mark) : position.mark != null) return false;
        if ((alcoholByVolume != null ? alcoholByVolume : BigDecimal.ZERO)
                .compareTo(position.alcoholByVolume != null ? position.alcoholByVolume : BigDecimal.ZERO) != 0)
            return false;
        if (alcoholProductKindCode != null ? !alcoholProductKindCode.equals(position.alcoholProductKindCode) : position.alcoholProductKindCode != null)
            return false;
        if ((tareVolume != null ? tareVolume : BigDecimal.ZERO).compareTo(position.tareVolume != null ? position.tareVolume : BigDecimal.ZERO) != 0)
            return false;
        if (extraKeys != null ? !extraKeys.equals(position.extraKeys) : position.extraKeys != null)
            return false;
        if (attributes != null ? !attributes.equals(position.attributes) : position.attributes != null)
            return false;
        if (!settlementMethod.equals(position.settlementMethod))
            return false;
        if (agentRequisites != null ? !agentRequisites.equals(position.agentRequisites) : position.agentRequisites != null)
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
        result = 31 * result + (taxNumber != null ? taxNumber.hashCode() : 0);
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
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (settlementMethod != null ? settlementMethod.hashCode() : 0);
        result = 31 * result + (agentRequisites != null ? agentRequisites.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "uuid='" + uuid + '\'' +
                ", productUuid='" + productUuid + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productType=" + productType +
                ", name='" + name + '\'' +
                ", measureName='" + measureName + '\'' +
                ", measurePrecision=" + measurePrecision +
                ", taxNumber=" + taxNumber +
                ", price=" + price +
                ", priceWithDiscountPosition=" + priceWithDiscountPosition +
                ", quantity=" + quantity +
                ", barcode='" + barcode + '\'' +
                ", mark='" + mark + '\'' +
                ", alcoholByVolume=" + alcoholByVolume +
                ", alcoholProductKindCode=" + alcoholProductKindCode +
                ", tareVolume=" + tareVolume +
                ", extraKeys=" + extraKeys +
                ", subPositions=" + subPositions +
                ", attributes=" + attributes +
                ", settlementMethod=" + settlementMethod +
                ", agentRequisites=" + agentRequisites +
                '}';
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
        dest.writeInt(this.taxNumber == null ? -1 : this.taxNumber.ordinal());
        dest.writeSerializable(this.price);
        dest.writeSerializable(this.priceWithDiscountPosition);
        dest.writeSerializable(this.quantity);
        dest.writeString(this.barcode);
        dest.writeString(this.mark);
        dest.writeSerializable(this.alcoholByVolume);
        dest.writeValue(this.alcoholProductKindCode);
        dest.writeSerializable(this.tareVolume);
        dest.writeTypedArray(this.extraKeys.toArray(new ExtraKey[this.extraKeys.size()]), flags);
        dest.writeTypedList(this.subPositions);
        dest.writeInt(MAGIC_NUMBER);
        dest.writeInt(VERSION);
        // Determine position in parcel for writing data size
        int dataSizePosition = dest.dataPosition();
        // Use integer placeholder for additional data size
        dest.writeInt(0);
        //Determine position of data start
        int startDataPosition = dest.dataPosition();

        //Write additional data
        writeAdditionalFields(dest, flags);

        // Calculate additional data size
        int dataSize = dest.dataPosition() - startDataPosition;
        // Save position at the end of data
        int endOfDataPosition = dest.dataPosition();
        //Set position to start to write additional data size
        dest.setDataPosition(dataSizePosition);
        dest.writeInt(dataSize);
        // Go back to the end of parcel
        dest.setDataPosition(endOfDataPosition);
    }

    private void writeAdditionalFields(Parcel dest, int flags) {
        // Attributes
        dest.writeInt(this.attributes != null ? this.attributes.size() : 0);
        if (this.attributes != null) {
            for (Map.Entry<String, AttributeValue> entry : this.attributes.entrySet()) {
                dest.writeString(entry.getKey());
                dest.writeParcelable(entry.getValue(), flags);
            }
        }
        // Payment features
        dest.writeParcelable(this.settlementMethod, flags);
        //AgentRequisites
        dest.writeBundle(this.agentRequisites != null ? this.agentRequisites.toBundle() : null);
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
        int tmpTaxNumber = in.readInt();
        this.taxNumber = tmpTaxNumber == -1 ? null : TaxNumber.values()[tmpTaxNumber];
        this.price = (BigDecimal) in.readSerializable();
        this.priceWithDiscountPosition = (BigDecimal) in.readSerializable();
        this.quantity = (BigDecimal) in.readSerializable();
        this.barcode = in.readString();
        this.mark = in.readString();
        this.alcoholByVolume = (BigDecimal) in.readSerializable();
        this.alcoholProductKindCode = (Long) in.readValue(Long.class.getClassLoader());
        this.tareVolume = (BigDecimal) in.readSerializable();
        this.extraKeys = new HashSet<>(Arrays.asList(in.createTypedArray(ExtraKey.CREATOR)));
        this.subPositions = in.createTypedArrayList(Position.CREATOR);
        readAdditionalFields(in);
    }

    private void readAdditionalFields(Parcel in) {

        boolean isVersionGreaterThanCurrent = false;
        int startReadingPosition = in.dataPosition();

        // Check if available data size is more than integer size and versioning is supported
        if (in.dataAvail() <= 4 || in.readInt() != MAGIC_NUMBER) {
            // Versioning is not supported return pointer to start position and end reading
            in.setDataPosition(startReadingPosition);
            return;
        }
        //Read object version
        int version = in.readInt();
        int dataSize = in.readInt();
        int startDataPosition = in.dataPosition();

        if (version > VERSION) {
            isVersionGreaterThanCurrent = true;
        }
        switch (version) {
            case 1: {
                readAttributesField(in);
                break;
            }
            case 2: {
                readAttributesField(in);
                readSettlementMethodField(in);
            }
            case 3: {
                readAttributesField(in);
                readSettlementMethodField(in);
                readAgentRequisitesField(in);
            }
        }

        if (isVersionGreaterThanCurrent) {
            in.setDataPosition(startDataPosition + dataSize);
        }
    }

    private void readAttributesField(Parcel in) {
        int attributesSize = in.readInt();
        if (attributesSize > 0) {
            this.attributes = new HashMap<>(attributesSize);
            for (int i = 0; i < attributesSize; i++) {
                String key = in.readString();
                AttributeValue value = in.readParcelable(AttributeValue.class.getClassLoader());
                this.attributes.put(key, value);
            }
        }
    }

    private void readSettlementMethodField(Parcel in) {
        SettlementMethod settlementMethod = in.readParcelable(SettlementMethod.class.getClassLoader());
        if (settlementMethod == null) {
            this.settlementMethod = new SettlementMethod.FullSettlement();
        } else {
            this.settlementMethod = settlementMethod;
        }
    }

    private void readAgentRequisitesField(Parcel in) {
        this.agentRequisites = AgentRequisites.Companion.from(in.readBundle(AgentRequisites.class.getClassLoader()));
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
                @NonNull ProductItem.Product product,
                @NonNull BigDecimal quantity
        ) {
            Builder builder = Builder.newInstance(
                    UUID.randomUUID().toString(),
                    product.getUuid(),
                    product.getName(),
                    product.getMeasureName(),
                    product.getMeasurePrecision(),
                    product.getPrice(),
                    quantity
            );

            builder.setTaxNumber(product.getTaxNumber())
                    .setAlcoParams(
                            null,
                            product.getAlcoholByVolume(),
                            product.getAlcoholProductKindCode(),
                            product.getTareVolume());

            builder.position.productType = product.getType();
            builder.position.productCode = product.getCode();

            return builder;
        }

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
                            null,
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
            setAlcoParams(
                    mark,
                    alcoholByVolume,
                    alcoholProductKindCode,
                    tareVolume
            );
            return this;
        }

        public Builder toAlcoholNotMarked(
                @NonNull BigDecimal alcoholByVolume,
                @NonNull Long alcoholProductKindCode,
                @NonNull BigDecimal tareVolume
        ) {
            position.productType = ProductType.ALCOHOL_NOT_MARKED;
            setAlcoParams(
                    null,
                    alcoholByVolume,
                    alcoholProductKindCode,
                    tareVolume
            );
            return this;
        }

        public Builder toTobaccoMarked(
                @NonNull String mark
        ) {
            position.productType = ProductType.TOBACCO_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setTobaccoParams(mark);
            return this;
        }

        public Builder toNormal() {
            position.productType = ProductType.NORMAL;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            return this;
        }

        public Builder toService() {
            position.productType = ProductType.SERVICE;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            return this;
        }

        private void setAlcoParams(
                String mark,
                BigDecimal alcoholByVolume,
                Long alcoholProductKindCode,
                BigDecimal tareVolume
        ) {
            position.mark = mark;
            position.alcoholByVolume = alcoholByVolume;
            position.alcoholProductKindCode = alcoholProductKindCode;
            position.tareVolume = tareVolume;
        }

        private void setTobaccoParams(String mark) {
            position.mark = mark;
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

        public Builder setTaxNumber(TaxNumber taxNumber) {
            position.taxNumber = taxNumber;
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

        public Builder setAttributes(@Nullable Map<String, AttributeValue> attributes) {
            position.attributes = attributes;
            return this;
        }

        public Builder setSettlementMethod(@NonNull SettlementMethod settlementMethod) {
            position.settlementMethod = settlementMethod;
            return this;
        }

        public Builder setAgentRequisites(@Nullable AgentRequisites agentRequisites) {
            position.agentRequisites = agentRequisites;
            return this;
        }

        public Builder setProductCode(@Nullable String productCode) {
            position.productCode = productCode;
            return this;
        }

        public Position build() {
            return new Position(position);
        }
    }

}
