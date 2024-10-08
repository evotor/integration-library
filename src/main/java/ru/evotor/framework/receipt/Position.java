package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import ru.evotor.framework.calculator.MoneyCalculator;
import ru.evotor.framework.calculator.PercentCalculator;
import ru.evotor.framework.core.IntegrationLibraryParsingException;
import ru.evotor.framework.inventory.AttributeValue;
import ru.evotor.framework.inventory.ProductItem;
import ru.evotor.framework.inventory.ProductType;
import ru.evotor.framework.kkt.FiscalRequisite;
import ru.evotor.framework.kkt.FiscalTags;
import ru.evotor.framework.receipt.position.AgentRequisites;
import ru.evotor.framework.receipt.position.ImportationData;
import ru.evotor.framework.receipt.position.Mark;
import ru.evotor.framework.receipt.position.MarksCheckingInfo;
import ru.evotor.framework.receipt.position.PartialRealization;
import ru.evotor.framework.receipt.position.PreferentialMedicine;
import ru.evotor.framework.receipt.position.SettlementMethod;

/**
 * Позиция чека.
 */
public class Position implements Parcelable {
    /**
     * Текущая версия объекта Position
     */
    private static final int VERSION = 14;
    /**
     * Магическое число для идентификации использования версионирования объекта.
     */
    private static final int MAGIC_NUMBER = 8800;
    /**
     * Идентификатор позиции в формате UUID.
     */
    private String uuid;
    /**
     * Идентификатор товара в формате UUID.
     */
    @Nullable
    private final String productUuid;
    /**
     * Код товара.
     */
    @Nullable
    private String productCode;
    /**
     * Тип товара.
     */
    @Nullable
    private ProductType productType;
    /**
     * Название.
     */
    private final String name;
    /**
     * Единица измерения.
     */
    @NonNull
    private Measure measure;
    /**
     * Ставка НДС.
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
     * (Для маркированных товаров при частичной продаже это количество проданного сейчас товара по данной марке)
     */
    private BigDecimal quantity;
    /**
     * Штрихкод, по которому товар был найден.
     */
    @Nullable
    private String barcode;
    /**
     * Последовательность символов, идентифицирующая маркированный товар.
     * Бывает разных форматов.
     */
    @Nullable
    private Mark mark;
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
     * Признак способа расчёта.
     * <p>
     * Указывается для каждой позиции чека.
     * <p>
     * Значение по умолчанию – [Полный расчёт]{@link ru.evotor.framework.receipt.position.SettlementMethod.FullSettlement}.
     */
    @NonNull
    private SettlementMethod settlementMethod = new SettlementMethod.FullSettlement();

    /**
     * Реквизиты агента
     */
    @Nullable
    private AgentRequisites agentRequisites;

    /**
     * Данные об импорте продукции
     */
    @Nullable
    private ImportationData importationData;

    /**
     * Акциз
     * Тег 1229
     */
    @FiscalRequisite(tag = FiscalTags.EXCISE)
    @Nullable
    private BigDecimal excise;

    /**
     * Классификационный код (Номенклатурный код)
     * Значение будет записано в тег 1162 только для немаркированных товаров.
     * Строка штрихкода в формате EAN-13
     */
    @FiscalRequisite(tag = FiscalTags.PRODUCT_CODE)
    @Nullable
    private String classificationCode;

    /**
     * Тип и сумма льготы для лекарственных препаратов
     * Значения будут записаны в тэг 1191
     */
    @FiscalRequisite(tag = FiscalTags.MEDICINE_PREFERENTIAL_REQUISITE)
    @Nullable
    private PreferentialMedicine preferentialMedicine;

    /**
     * Частичное выбытие 1191
     * <p>
     * Доступно только для следующих типов товара:
     * - лекарства {@link ProductType#MEDICINE_MARKED}
     * - духи {@link ProductType#PERFUME_MARKED}
     * - альтернативный табак {@link ProductType#TOBACCO_PRODUCTS_MARKED}
     * <p>
     * Также см. {@link #quantity}
     */
    @FiscalRequisite(tag = FiscalTags.PARTIAL_REALIZATION)
    @Nullable
    private PartialRealization partialRealization;

    /**
     * Признак подакцизности товара
     * На основании этого флага будет вычислен признак предмета расчета (тег 1212)
     */
    @Nullable
    private Boolean isExcisable;

    /**
     * Данные об онлайн-проверке марки
     * Значения будут записаны в тэг 1265
     */
    @Nullable
    private MarksCheckingInfo marksCheckingInfo;

    /**
     * Признак возрастного ограничения товара
     */
    @Nullable
    private Boolean isAgeLimited;

    /**
     * Признак, если был пропущен ввод кода маркировки
     */
    @Nullable
    private Boolean isMarkSkipped;

    /**
     * Признак времени запрета продажи,
     * интервал времени, когда позицию продавать нельзя
     */
    @Nullable
    private TimeRange saleBanTime;

    public Position(
            String uuid,
            @Nullable String productUuid,
            @Nullable String productCode,
            ProductType productType,
            String name,
            @NonNull Measure measure,
            @Nullable TaxNumber taxNumber,
            BigDecimal price,
            BigDecimal priceWithDiscountPosition,
            BigDecimal quantity,
            @Nullable String barcode,
            @Nullable Mark mark,
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
        this.measure = measure;
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
                position.getMeasure(),
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
        this.importationData = position.getImportationData();
        this.excise = position.getExcise();
        this.classificationCode = position.getClassificationCode();
        this.preferentialMedicine = position.getPreferentialMedicine();
        this.partialRealization = position.getPartialRealization();
        this.isExcisable = position.isExcisable;
        this.marksCheckingInfo = position.getMarksCheckingInfo();
        this.isAgeLimited = position.isAgeLimited;
        this.isMarkSkipped = position.isMarkSkipped;
        this.saleBanTime = position.saleBanTime;
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
     * @return Единица измерения
     */
    @NonNull
    public Measure getMeasure() {
        return measure;
    }

    /**
     * @return Наименование единицы измерения.
     * @deprecated Используйте @link{getMeasure}
     */
    public String getMeasureName() {
        return measure.getName();
    }

    /**
     * @return Точность единицы измерения.
     * @deprecated Используйте @link{getMeasure}
     */
    public int getMeasurePrecision() {
        return measure.getPrecision();
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
     * (Для маркированных товаров, при частичной реализации, это количество проданного сейчас товара по данной марке)
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
    public Mark getMark() {
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

    /**
     * @return Данные об импорте продукции
     */
    @Nullable
    public ImportationData getImportationData() {
        return importationData;
    }

    /**
     * @return Акциз. Тег 1229
     */
    @FiscalRequisite(tag = FiscalTags.EXCISE)
    @Nullable
    public BigDecimal getExcise() {
        return excise;
    }

    /**
     * @return Классификационный код. Тег 1162 для обычного товара.
     */
    @FiscalRequisite(tag = FiscalTags.PRODUCT_CODE)
    @Nullable
    public String getClassificationCode() {
        return classificationCode;
    }

    /**
     * @return Льгота для лекарственных препаратов
     */
    @FiscalRequisite(tag = FiscalTags.MEDICINE_PREFERENTIAL_REQUISITE)
    @Nullable
    public PreferentialMedicine getPreferentialMedicine() {
        return preferentialMedicine;
    }

    /**
     * @return Частичное выбытие 1191
     */
    @FiscalRequisite(tag = FiscalTags.PARTIAL_REALIZATION)
    @Nullable
    public PartialRealization getPartialRealization() {
        return partialRealization;
    }

    /**
     * @return Признак подакцизности товара.
     * На основании этого флага будет вычислен признак предмета расчета (тег 1212)
     */
    @Nullable
    public Boolean getIsExcisable() {
        return isExcisable;
    }

    /**
     * @return Признак возрастного ограничения товара
     */
    @Nullable
    public Boolean getIsAgeLimited() {
        return isAgeLimited;
    }

    /**
     * @return true, если был пропущен ввод кода маркировки
     */
    @Nullable
    public Boolean getIsMarkSkipped() {
        return isMarkSkipped;
    }

    @Nullable
    public TimeRange getSaleBanTime() {
        return saleBanTime;
    }

    /**
     * @return Данные об онлайн-проверке марки
     * Значения будут записаны в тег 1265
     */
    @Nullable
    public MarksCheckingInfo getMarksCheckingInfo() {
        return marksCheckingInfo;
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

        if (!Objects.equals(measure, position.measure)) return false;
        if (!Objects.equals(uuid, position.uuid)) return false;
        if (!Objects.equals(productUuid, position.productUuid))
            return false;
        if (!Objects.equals(productCode, position.productCode))
            return false;
        if (productType != position.productType) return false;
        if (!Objects.equals(name, position.name)) return false;
        if (taxNumber != position.taxNumber) return false;
        if ((price != null ? price : BigDecimal.ZERO).compareTo(position.price != null ? position.price : BigDecimal.ZERO) != 0)
            return false;
        if ((priceWithDiscountPosition != null ? priceWithDiscountPosition : BigDecimal.ZERO)
                .compareTo(position.priceWithDiscountPosition != null ? position.priceWithDiscountPosition : BigDecimal.ZERO) != 0)
            return false;
        if (!exceptQuantity && (quantity != null ? quantity : BigDecimal.ZERO).compareTo(position.quantity != null ? position.quantity : BigDecimal.ZERO) != 0)
            return false;
        if (!Objects.equals(barcode, position.barcode))
            return false;
        if (!Objects.equals(mark, position.mark)) return false;
        if ((alcoholByVolume != null ? alcoholByVolume : BigDecimal.ZERO)
                .compareTo(position.alcoholByVolume != null ? position.alcoholByVolume : BigDecimal.ZERO) != 0)
            return false;
        if (!Objects.equals(alcoholProductKindCode, position.alcoholProductKindCode))
            return false;
        if ((tareVolume != null ? tareVolume : BigDecimal.ZERO).compareTo(position.tareVolume != null ? position.tareVolume : BigDecimal.ZERO) != 0)
            return false;
        if (!Objects.equals(extraKeys, position.extraKeys))
            return false;
        if (!Objects.equals(attributes, position.attributes))
            return false;
        if (!settlementMethod.equals(position.settlementMethod))
            return false;
        if (!Objects.equals(agentRequisites, position.agentRequisites))
            return false;
        if (!Objects.equals(importationData, position.importationData))
            return false;
        if (!Objects.equals(excise, position.excise)) {
            return false;
        }
        if (!Objects.equals(classificationCode, position.classificationCode)) {
            return false;
        }
        if (!Objects.equals(preferentialMedicine, position.preferentialMedicine))
            return false;
        if (!Objects.equals(partialRealization, position.partialRealization))
            return false;
        if (!Objects.equals(isExcisable, position.isExcisable))
            return false;
        if (!Objects.equals(marksCheckingInfo, position.marksCheckingInfo))
            return false;
        if (!Objects.equals(isAgeLimited, position.isAgeLimited))
            return false;
        if (!Objects.equals(isMarkSkipped, position.isMarkSkipped))
            return false;
        if (!Objects.equals(saleBanTime, position.saleBanTime))
            return false;
        return Objects.equals(subPositions, position.subPositions);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (productUuid != null ? productUuid.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (measure.hashCode());
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
        result = 31 * result + (settlementMethod.hashCode());
        result = 31 * result + (agentRequisites != null ? agentRequisites.hashCode() : 0);
        result = 31 * result + (importationData != null ? importationData.hashCode() : 0);
        result = 31 * result + (excise != null ? excise.hashCode() : 0);
        result = 31 * result + (classificationCode != null ? classificationCode.hashCode() : 0);
        result = 31 * result + (preferentialMedicine != null ? preferentialMedicine.hashCode() : 0);
        result = 31 * result + (partialRealization != null ? partialRealization.hashCode() : 0);
        result = 31 * result + (isExcisable != null ? isExcisable.hashCode() : 0);
        result = 31 * result + (marksCheckingInfo != null ? marksCheckingInfo.hashCode() : 0);
        result = 31 * result + (isAgeLimited != null ? isAgeLimited.hashCode() : 0);
        result = 31 * result + (isMarkSkipped != null ? isMarkSkipped.hashCode() : 0);
        result = 31 * result + (saleBanTime != null ? saleBanTime.hashCode() : 0);
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
                ", measure='" + measure + '\'' +
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
                ", importationData=" + importationData +
                ", excise=" + excise +
                ", classificationCode=" + classificationCode +
                ", preferentialMedicine=" + preferentialMedicine +
                ", partial=" + partialRealization +
                ", isExcisable=" + isExcisable +
                ", marksCheckingInfo=" + marksCheckingInfo +
                ", isAgeLimited=" + isAgeLimited +
                ", isMarkSkipped=" + isMarkSkipped +
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
        dest.writeString(this.measure.getName());
        dest.writeInt(this.measure.getPrecision());
        dest.writeInt(this.taxNumber == null ? -1 : this.taxNumber.ordinal());
        dest.writeSerializable(this.price);
        dest.writeSerializable(this.priceWithDiscountPosition);
        dest.writeSerializable(this.quantity);
        dest.writeString(this.barcode);
        writeRawMark(dest, this.mark);
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

    private void writeRawMark(Parcel dest, Mark mark) {
        String rawMark;
        if (mark instanceof Mark.RawMark) {
            rawMark = ((Mark.RawMark) mark).getValue();
        } else if (mark instanceof Mark.MarkByFiscalTags) {
            rawMark = ((Mark.MarkByFiscalTags) mark).getProductCode();
        } else {
            rawMark = null;
        }
        dest.writeString(rawMark);
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
        //ImportationData
        dest.writeBundle(this.importationData != null ? this.importationData.toBundle() : null);
        dest.writeSerializable(this.excise);
        dest.writeString(this.classificationCode);
        //Preferential medicine
        dest.writeBundle(this.preferentialMedicine != null ? this.preferentialMedicine.toBundle() : null);
        // Mark
        dest.writeParcelable(this.mark, flags);
        // Partial realization
        dest.writeBundle(this.partialRealization != null ? this.partialRealization.toBundle() : null);
        dest.writeInt(this.measure.getCode());
        dest.writeSerializable(this.isExcisable);
        dest.writeBundle(this.marksCheckingInfo != null ? this.marksCheckingInfo.toBundle() : null);
        dest.writeSerializable(this.isAgeLimited);
        dest.writeSerializable(this.isMarkSkipped);
        dest.writeBundle(this.saleBanTime != null ? this.saleBanTime.toBundle() : null);
    }

    protected Position(Parcel in) {
        this.uuid = in.readString();
        this.productUuid = in.readString();
        this.productCode = in.readString();
        int tmpProductType = in.readInt();

        if (tmpProductType >= ProductType.values().length) {
            throw new IntegrationLibraryParsingException(Position.class);
        } else {
            this.productType = tmpProductType == -1 ? null : ProductType.values()[tmpProductType];
        }

        this.name = in.readString();
        String measureName = in.readString();
        int measurePrecision = in.readInt();
        int tmpTaxNumber = in.readInt();
        this.taxNumber = tmpTaxNumber == -1 ? null : TaxNumber.values()[tmpTaxNumber];
        this.price = (BigDecimal) in.readSerializable();
        this.priceWithDiscountPosition = (BigDecimal) in.readSerializable();
        this.quantity = (BigDecimal) in.readSerializable();
        this.barcode = in.readString();
        String markString = in.readString();
        Mark markFromParcel;
        if (markString == null || markString.isEmpty()) {
            markFromParcel = null;
        } else {
            markFromParcel = new Mark.RawMark(markString);
        }
        this.mark = markFromParcel;
        this.alcoholByVolume = (BigDecimal) in.readSerializable();
        this.alcoholProductKindCode = (Long) in.readValue(Long.class.getClassLoader());
        this.tareVolume = (BigDecimal) in.readSerializable();
        this.extraKeys = new HashSet<>(Arrays.asList(in.createTypedArray(ExtraKey.CREATOR)));
        this.subPositions = in.createTypedArrayList(Position.CREATOR);
        readAdditionalFields(in, measureName, measurePrecision);
    }

    private void readAdditionalFields(Parcel in, String measureName, int measurePrecision) {
        int measureCode = Measure.UNKNOWN_MEASURE_CODE;
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
        if (version >= 1) {
            readAttributesField(in);
        }
        if (version >= 2) {
            readSettlementMethodField(in);
        }
        if (version >= 3) {
            readAgentRequisitesField(in);
        }
        if (version >= 4) {
            readImportationData(in);
            this.excise = (BigDecimal) in.readSerializable();
        }
        if (version >= 5) {
            this.classificationCode = in.readString();
        }
        if (version >= 6) {
            readPreferentialMedicine(in);
        }
        if (version >= 7) {
            readMark(in);
        }
        if (version >= 8) {
            readPartialRealization(in);
        }
        if (version >= 9) {
            measureCode = in.readInt();
        }
        this.measure = new Measure(
                measureName,
                measurePrecision,
                measureCode
        );
        if (version >= 10) {
            this.isExcisable = (Boolean) in.readSerializable();
        }
        if (version >= 11) {
            readMarksCheckingInfo(in);
        }
        if (version >= 12) {
            this.isAgeLimited = (Boolean) in.readSerializable();
        }
        if (version >= 13) {
            this.isMarkSkipped = (Boolean) in.readSerializable();
        }
        if (version >= 14) {
            this.saleBanTime = TimeRange.from(in.readBundle(TimeRange.class.getClassLoader()));
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

    private void readImportationData(Parcel in) {
        this.importationData = ImportationData.Companion.from(in.readBundle(ImportationData.class.getClassLoader()));
    }

    private void readPreferentialMedicine(Parcel in) {
        this.preferentialMedicine = PreferentialMedicine.Companion.from(in.readBundle(PreferentialMedicine.class.getClassLoader()));
    }

    private void readMark(Parcel in) {
        this.mark = in.readParcelable(Mark.class.getClassLoader());
    }

    private void readPartialRealization(Parcel in) {
        this.partialRealization = PartialRealization.Companion.from(in.readBundle(PartialRealization.class.getClassLoader()));
    }

    private void readMarksCheckingInfo(Parcel in) {
        this.marksCheckingInfo = MarksCheckingInfo.Companion.from(in.readBundle(MarksCheckingInfo.class.getClassLoader()));
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
                    product.getMeasure(),
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
            builder.position.classificationCode = product.getClassificationCode();
            builder.position.isExcisable = product.isExcisable();
            builder.position.isAgeLimited = product.isAgeLimited();
            return builder;
        }

        public static Builder newInstance(
                @Nullable String uuid,
                @Nullable String productUuid,
                @NonNull String name,
                @NonNull Measure measure,
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
                            measure,
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

        private final Position position;

        @Deprecated
        public Builder(Position position) {
            this.position = new Position(position);
        }

        @Nullable
        private Mark.RawMark createRawMark(@Nullable String mark) {
            if (mark == null || mark.isEmpty()) {
                return null;
            }
            return new Mark.RawMark(mark);
        }

        /**
         * @deprecated Используйте {@link #toAlcoholMarked(Mark, BigDecimal, Long, BigDecimal)}
         */
        @Deprecated
        public Builder toAlcoholMarked(
                @NonNull String mark,
                @NonNull BigDecimal alcoholByVolume,
                @NonNull Long alcoholProductKindCode,
                @NonNull BigDecimal tareVolume
        ) {
            position.productType = ProductType.ALCOHOL_MARKED;
            setAlcoParams(
                    createRawMark(mark),
                    alcoholByVolume,
                    alcoholProductKindCode,
                    tareVolume
            );
            return this;
        }

        public Builder toAlcoholMarked(
                @NonNull Mark mark,
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

        public Builder toBeerMarkedKeg(
                @NonNull Mark mark,
                @NonNull BigDecimal alcoholByVolume,
                @NonNull Long alcoholProductKindCode,
                @NonNull BigDecimal tareVolume
        ) {
            position.productType = ProductType.BEER_MARKED_KEG;
            setAlcoParams(
                    mark,
                    alcoholByVolume,
                    alcoholProductKindCode,
                    tareVolume
            );
            return this;
        }

        public Builder toAntisepticMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.ANTISEPTIC_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setAntisepticParams(mark);
            return this;
        }

        public Builder toDietarySupplementsMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.DIETARY_SUPPLEMENTS_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setDietarySupplementsParams(mark);
            return this;
        }

        public Builder toBeerMarked(
                @NonNull Mark mark,
                @NonNull BigDecimal alcoholByVolume,
                @NonNull Long alcoholProductKindCode,
                @NonNull BigDecimal tareVolume
        ) {
            position.productType = ProductType.BEER_MARKED;
            setAlcoParams(
                    mark,
                    alcoholByVolume,
                    alcoholProductKindCode,
                    tareVolume
            );
            return this;
        }

        /**
         * @deprecated Используйте {@link #toTobaccoMarked(Mark)}
         */
        @Deprecated
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
            setTobaccoParams(createRawMark(mark));
            return this;
        }

        public Builder toTobaccoMarked(
                @NonNull Mark mark
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

        /**
         * @deprecated Используйте {@link #toShoesMarked(Mark)}
         */
        @Deprecated
        public Builder toShoesMarked(
                @NonNull String mark
        ) {
            position.productType = ProductType.SHOES_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setShoesParams(createRawMark(mark));
            return this;
        }

        public Builder toShoesMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.SHOES_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setShoesParams(mark);
            return this;
        }

        /**
         * @deprecated Используйте {@link #toMedicineMarked(Mark)}
         */
        @Deprecated
        public Builder toMedicineMarked(
                @NonNull String mark
        ) {
            position.productType = ProductType.MEDICINE_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setMedicineParams(createRawMark(mark));
            return this;
        }

        public Builder toMedicineMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.MEDICINE_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setMedicineParams(mark);
            return this;
        }

        /**
         * @deprecated Используйте {@link #toTyresMarked(Mark)}
         */
        @Deprecated
        public Builder toTyresMarked(
                @NonNull String mark
        ) {
            position.productType = ProductType.TYRES_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setTyresParams(createRawMark(mark));
            return this;
        }

        public Builder toTyresMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.TYRES_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setTyresParams(mark);
            return this;
        }

        /**
         * @deprecated Используйте {@link #toPerfumeMarked(Mark)}
         */
        @Deprecated
        public Builder toPerfumeMarked(
                @NonNull String mark
        ) {
            position.productType = ProductType.PERFUME_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setPerfumesParams(createRawMark(mark));
            return this;
        }

        public Builder toPerfumeMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.PERFUME_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setPerfumesParams(mark);
            return this;
        }

        /**
         * @deprecated Используйте {@link #toPhotosMarked(Mark)}
         */
        @Deprecated
        public Builder toPhotosMarked(
                @NonNull String mark
        ) {
            position.productType = ProductType.PHOTOS_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setPhotosParams(createRawMark(mark));
            return this;
        }

        public Builder toPhotosMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.PHOTOS_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setPhotosParams(mark);
            return this;
        }

        /**
         * @deprecated Используйте {@link #toLightIndustryMarked(Mark)}
         */
        @Deprecated
        public Builder toLightIndustryMarked(
                @NonNull String mark
        ) {
            position.productType = ProductType.LIGHT_INDUSTRY_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setLightIndustryParams(createRawMark(mark));
            return this;
        }

        public Builder toLightIndustryMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.LIGHT_INDUSTRY_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setLightIndustryParams(mark);
            return this;
        }

        /**
         * @deprecated Используйте {@link #toTobaccoProductsMarked(Mark)}
         */
        @Deprecated
        public Builder toTobaccoProductsMarked(
                @NonNull String mark
        ) {
            position.productType = ProductType.TOBACCO_PRODUCTS_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setTobaccoProductsParams(createRawMark(mark));
            return this;
        }

        public Builder toTobaccoProductsMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.TOBACCO_PRODUCTS_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setTobaccoProductsParams(mark);
            return this;
        }

        public Builder toDairyMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.DAIRY_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setDairyParams(mark);
            return this;
        }

        public Builder toWaterMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.WATER_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setWaterParams(mark);
            return this;
        }

        public Builder toBikeMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.BIKE_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setBikeParams(mark);
            return this;
        }

        public Builder toJewelryMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.JEWELRY_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setJewelryParams(mark);
            return this;
        }

        public Builder toFurMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.FUR_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setFurParams(mark);
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

        public Builder toLotteryTicket() {
            position.productType = ProductType.LOTTERY_TICKET;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            return this;
        }

        public Builder toLotteryPrize() {
            position.productType = ProductType.LOTTERY_PRIZE;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            return this;
        }

        public Builder toJuiceMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.JUICE_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setJuiceParams(mark);
            return this;
        }

        public Builder toWheelchairsMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.WHEELCHAIRS_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setWheelchairsParams(mark);
            return this;
        }

        public Builder toMedicalDevicesMarked(
                @NonNull Mark mark
        ) {
            position.productType = ProductType.MEDICAL_DEVICES_MARKED;
            setAlcoParams(
                    null,
                    null,
                    null,
                    null
            );
            setMedicalDevicesParams(mark);
            return this;
        }


        /**
         * Частичная реализация для позиции доступна только если тип товара является одним из:
         * <p>
         * лекарства {@link ProductType#MEDICINE_MARKED}
         * духи {@link ProductType#PERFUME_MARKED}
         * альтернативный табак {@link ProductType#TOBACCO_PRODUCTS_MARKED}
         *
         * @param quantityInPackage количество товара в упаковке всего
         */
        public Builder toPartialRealization(
                @NonNull BigDecimal quantityInPackage
        ) {
            position.partialRealization = new PartialRealization(
                    quantityInPackage
            );
            return this;
        }

        public Builder toMarksCheckingInfo(
                @NonNull String checkId,
                @NonNull Long timestamp
        ) {
            position.marksCheckingInfo = new MarksCheckingInfo(
                    checkId,
                    timestamp
            );
            return this;
        }

        /**
         * Признак подакцизности товара <br>
         * На основании этого флага будет вычислен признак предмета расчета (тег 1212),
         * должно выставляться значение 'true', если тип товара является: <br>
         * {@link ProductType#ALCOHOL_MARKED} <br>
         * {@link ProductType#ALCOHOL_NOT_MARKED} <br>
         * {@link ProductType#TOBACCO_MARKED} <br>
         * {@link ProductType#TOBACCO_PRODUCTS_MARKED} <br>
         * <br>
         * Опционально указывается для типа товара {@link ProductType#NORMAL}
         *
         * @param isExcisable булевое значение, является ли товар акцизным
         */
        public Builder setIsExcisable(Boolean isExcisable) {
            position.isExcisable = isExcisable;
            return this;
        }

        private void setAlcoParams(
                Mark mark,
                BigDecimal alcoholByVolume,
                Long alcoholProductKindCode,
                BigDecimal tareVolume
        ) {
            position.mark = mark;
            position.alcoholByVolume = alcoholByVolume;
            position.alcoholProductKindCode = alcoholProductKindCode;
            position.tareVolume = tareVolume;
        }

        private void setShoesParams(Mark mark) {
            position.mark = mark;
        }

        private void setTobaccoParams(Mark mark) {
            position.mark = mark;
        }

        private void setMedicineParams(Mark mark) {
            position.mark = mark;
        }

        private void setTyresParams(Mark mark) {
            position.mark = mark;
        }

        private void setPerfumesParams(Mark mark) {
            position.mark = mark;
        }

        private void setPhotosParams(Mark mark) {
            position.mark = mark;
        }

        private void setLightIndustryParams(Mark mark) {
            position.mark = mark;
        }

        private void setTobaccoProductsParams(Mark mark) {
            position.mark = mark;
        }

        private void setDairyParams(Mark mark) {
            position.mark = mark;
        }

        private void setWaterParams(Mark mark) {
            position.mark = mark;
        }

        private void setBikeParams(Mark mark) {
            position.mark = mark;
        }

        private void setJewelryParams(Mark mark) {
            position.mark = mark;
        }

        private void setFurParams(Mark mark) {
            position.mark = mark;
        }

        private void setAntisepticParams(Mark mark) {
            position.mark = mark;
        }

        public void setDietarySupplementsParams(Mark mark) {
            position.mark = mark;
        }

        public void setJuiceParams(Mark mark) {
            position.mark = mark;
        }

        private void setWheelchairsParams(Mark mark) {
            position.mark = mark;
        }

        private void setMedicalDevicesParams(Mark mark) {
            position.mark = mark;
        }

        private void setBeerParams(Mark mark) {
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

        public Builder setMeasure(Measure measure) {
            position.measure = measure;
            return this;
        }

        public Builder setPriceWithDiscountPosition(BigDecimal priceWithDiscountPosition) {
            position.priceWithDiscountPosition = priceWithDiscountPosition;
            return this;
        }

        public Builder setMark(String mark) {
            position.mark = createRawMark(mark);
            return this;
        }

        public Builder setMark(Mark mark) {
            position.mark = mark;
            return this;
        }

        public Builder setExtraKeys(Set<ExtraKey> extraKeys) {
            position.extraKeys = extraKeys;
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

        public Builder setImportationData(@Nullable ImportationData importationData) {
            position.importationData = importationData;
            return this;
        }

        public Builder setExcise(@Nullable BigDecimal excise) {
            position.excise = excise;
            return this;
        }

        public Builder setPreferentialMedicine(@Nullable PreferentialMedicine preferentialMedicine) {
            position.preferentialMedicine = preferentialMedicine;
            return this;
        }

        public Builder setProductCode(@Nullable String productCode) {
            position.productCode = productCode;
            return this;
        }

        public Builder setClassificationCode(@Nullable String classificationCode) {
            position.classificationCode = classificationCode;
            return this;
        }

        /**
         * Частичная реализация для позиции доступна только если тип товара является одним из:
         * <p>
         * лекарства {@link ProductType#MEDICINE_MARKED}
         * духи {@link ProductType#PERFUME_MARKED}
         * альтернативный табак {@link ProductType#TOBACCO_PRODUCTS_MARKED}
         *
         * @param partialRealization частичная реализация
         */
        public Builder setPartialRealization(@Nullable PartialRealization partialRealization) {
            position.partialRealization = partialRealization;
            return this;
        }

        public Builder setMarksCheckingInfo(@Nullable MarksCheckingInfo marksCheckingInfo) {
            position.marksCheckingInfo = marksCheckingInfo;
            return this;
        }

        public Builder setIsAgeLimited(Boolean isAgeLimited) {
            position.isAgeLimited = isAgeLimited;
            return this;
        }

        public Builder setIsMarkSkipped(@Nullable Boolean isMarkSkipped) {
            position.isMarkSkipped = isMarkSkipped;
            return this;
        }

        public Builder setSaleBanTime(@Nullable TimeRange saleBanTime) {
            position.saleBanTime = saleBanTime;
            return this;
        }

        public Position build() {
            return new Position(position);
        }
    }

}