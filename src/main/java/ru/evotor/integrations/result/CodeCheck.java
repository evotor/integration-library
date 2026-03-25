package ru.evotor.integrations.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CodeCheck implements Parcelable {

    /** КИ / КиЗ из запроса */
    private final String cis;

    /** Признак наличия кода в ГИС МТ */
    private final boolean found;

    /** Результат проверки валидности структуры КИ / КиЗ */
    private final boolean valid;

    /** КИ без крипто-подписи / КиЗ */
    private final String printView;

    /** Код товара */
    private final String gtin;

    /** Массив идентификаторов товарных групп */
    private final int[] groupIds;

    /** Результат проверки крипто-подписи КМ */
    private final boolean verified;

    /** Признак возможности реализации КИ / КиЗ */
    private final boolean realizable;

    /** Признак нанесения КИ / КиЗ на упаковку */
    private final boolean utilized;

    /** Дата и время истечения срока годности */
    @Nullable private final String expireDate;

    /** Информация о вариативном сроке годности */
    @Nullable private final VariableExpirations variableExpirations;

    /** Дата производства продукции */
    @Nullable private final String productionDate;

    /** Переменный вес продукции (в граммах) */
    @Nullable private final Integer productWeight;

    /** Производственный ветеринарный сопроводительный документ */
    @Nullable private final String prVetDocument;

    /** Признак, определяющий, что запрос направлен владельцем кода (определяется по аутентификационному токену) */
    @Nullable private final Boolean isOwner;

    /** Признак того, что розничная продажа продукции заблокирована по решению ОГВ */
    @Nullable private final Boolean isBlocked;

    /** Органы государственной власти, установившие блокировку на КИ */
    private final OGVS[] ogvs;

    /** Сообщение об ошибке */
    @Nullable private final String message;

    /**
     * Код ошибки
     * <ul>
     *     Возможные значения:
     *     <ul>
     *         <li>0 — ошибки отсутствуют</li>
     *         <li>1 — ошибка валидации КМ</li>
     *         <li>2 — КМ не содержит GTIN</li>
     *         <li>3 — КМ не содержит серийный номер</li>
     *         <li>4 — КМ содержит недопустимые символы</li>
     *         <li>5 — ошибка верификации крипто-подписи КМ (формат крипто-подписи не соответствует типу КМ)</li>
     *         <li>6 — ошибка верификации крипто-подписи КМ (криптоподпись невалидная)</li>
     *         <li>7 — ошибка верификации крипто-подписи КМ (крипто-ключ не валиден)</li>
     *         <li>8 — КМ не прошел верификацию в стране эмитента</li>
     *         <li>9 — Найденные AI в КМ не поддерживаются</li>
     *         <li>10 — КМ не найден в ГИС МТ</li>
     *         <li>11 — КМ не найден в трансгране</li>
     *     </ul>
     * </ul>
     */
    @Nullable private final Integer errorCode;

    /** Признак контроля прослеживаемости в товарной группе */
    private final boolean isTracking;

    /** Признак продажи товара */
    private final boolean sold;

    /** Признак использования причин выбытия, разрешающих продажу КМ */
    @Nullable private final EliminationState eliminationState;

    /** Максимальная розничная цена */
    @Nullable private final Integer mrp;

    /** Единая минимальная цена (ЕМЦ) */
    @Nullable private final Integer smp;

    /** Признак принадлежности табачной продукции к «серой зоне» */
    @Nullable private final Boolean grayZone;

    /**
     * Количество единиц товара в потребительской упаковке / Фактический объём / Фактический вес
     */
    @Nullable private final Integer innerUnitCount;

    /** Счётчик проданного и возвращённого товара */
    @Nullable private final Integer soldUnitCount;

    /** Тип упаковки */
    private final String packageType;

    /** КИ агрегата */
    @Nullable private final String parent;

    /** ИНН производителя */
    @Nullable private final String producerInn;

    /** Номер производственной серии */
    @Nullable private final String productionSerialNumber;

    /** Номер производственной партии */
    @Nullable private final String productionBatchNumber;

    /** Заводской серийный номер */
    @Nullable private final String factorySerialNumber;

    /** Ёмкость КИГУ */
    @Nullable private final Integer packageQuantity;

    private CodeCheck(Parcel parcel) {
        this.cis = parcel.readString();
        this.found = parcel.readInt() == 1;
        this.valid = parcel.readInt() == 1;
        this.printView = parcel.readString();
        this.gtin = parcel.readString();
        this.groupIds = parcel.createIntArray();
        this.verified = parcel.readInt() == 1;
        this.realizable = parcel.readInt() == 1;
        this.utilized = parcel.readInt() == 1;
        this.expireDate = parcel.readString();
        this.variableExpirations = parcel.readTypedObject(VariableExpirations.CREATOR);
        this.productionDate = parcel.readString();
        this.productWeight = readInteger(parcel);
        this.prVetDocument = parcel.readString();
        this.isOwner = readBoolean(parcel);
        this.isBlocked = readBoolean(parcel);
        this.ogvs = parcel.createTypedArray(OGVS.CREATOR);
        this.message = parcel.readString();
        this.errorCode = readInteger(parcel);
        this.isTracking = parcel.readInt() == 1;
        this.sold = parcel.readInt() == 1;
        String eliminationStateName = parcel.readString();
        this.eliminationState = eliminationStateName != null ? EliminationState.valueOf(eliminationStateName) : null;
        this.mrp = readInteger(parcel);
        this.smp = readInteger(parcel);
        this.grayZone = readBoolean(parcel);
        this.innerUnitCount = readInteger(parcel);
        this.soldUnitCount = readInteger(parcel);
        this.packageType = parcel.readString();
        this.parent = parcel.readString();
        this.producerInn = parcel.readString();
        this.productionSerialNumber = parcel.readString();
        this.productionBatchNumber = parcel.readString();
        this.factorySerialNumber = parcel.readString();
        this.packageQuantity = readInteger(parcel);
    }

    public CodeCheck(
            String cis,
            Boolean found,
            Boolean valid,
            String printView,
            String gtin,
            int[] groupIds,
            Boolean verified,
            Boolean realizable,
            Boolean utilized,
            @Nullable String expireDate,
            @Nullable VariableExpirations variableExpirations,
            @Nullable String productionDate,
            @Nullable Integer productWeight,
            @Nullable String prVetDocument,
            @Nullable Boolean isOwner,
            @Nullable Boolean isBlocked,
            @Nullable OGVS[] ogvs,
            @Nullable String message,
            @Nullable Integer errorCode,
            Boolean isTracking,
            Boolean sold,
            @Nullable EliminationState eliminationState,
            @Nullable Integer mrp,
            @Nullable Integer smp,
            @Nullable Boolean grayZone,
            @Nullable Integer innerUnitCount,
            @Nullable Integer soldUnitCount,
            String packageType,
            @Nullable String parent,
            @Nullable String producerInn,
            @Nullable String productionSerialNumber,
            @Nullable String productionBatchNumber,
            @Nullable String factorySerialNumber,
            @Nullable Integer packageQuantity
    ) {
        this.cis = cis;
        this.found = found;
        this.valid = valid;
        this.printView = printView;
        this.gtin = gtin;
        this.groupIds = groupIds;
        this.verified = verified;
        this.realizable = realizable;
        this.utilized = utilized;
        this.expireDate = expireDate;
        this.variableExpirations = variableExpirations;
        this.productionDate = productionDate;
        this.productWeight = productWeight;
        this.prVetDocument = prVetDocument;
        this.isOwner = isOwner;
        this.isBlocked = isBlocked;
        this.ogvs = ogvs == null ? new OGVS[0] : ogvs;
        this.message = message;
        this.errorCode = errorCode;
        this.isTracking = isTracking;
        this.sold = sold;
        this.eliminationState = eliminationState;
        this.mrp = mrp;
        this.smp = smp;
        this.grayZone = grayZone;
        this.innerUnitCount = innerUnitCount;
        this.soldUnitCount = soldUnitCount;
        this.packageType = packageType;
        this.parent = parent;
        this.producerInn = producerInn;
        this.productionSerialNumber = productionSerialNumber;
        this.productionBatchNumber = productionBatchNumber;
        this.factorySerialNumber = factorySerialNumber;
        this.packageQuantity = packageQuantity;
    }

    public String getCis() { return cis; }

    public boolean isFound() { return found; }

    @Nullable
    public Boolean getGrayZone() { return grayZone; }

    @Nullable
    public Boolean getOwner() { return isOwner; }

    public boolean isRealizable() { return realizable; }

    public boolean isSold() { return sold; }

    public boolean isTracking() { return isTracking; }

    public boolean isUtilized() { return utilized; }

    public boolean isValid() { return valid; }

    public boolean isVerified() { return verified; }

    @Nullable
    public EliminationState getEliminationState() { return eliminationState; }

    public int[] getGroupIds() { return groupIds; }

    @Nullable
    public Integer getErrorCode() { return errorCode; }

    @Nullable
    public Integer getInnerUnitCount() { return innerUnitCount; }

    @Nullable
    public Integer getMrp() { return mrp; }

    @Nullable
    public Integer getPackageQuantity() { return packageQuantity; }

    @Nullable
    public Integer getProductWeight() { return productWeight; }

    @Nullable
    public Integer getSmp() { return smp; }

    @Nullable
    public Integer getSoldUnitCount() { return soldUnitCount; }

    @Nullable
    public VariableExpirations getVariableExpirations() { return variableExpirations; }

    @Nullable
    public OGVS[] getOgvs() { return ogvs; }

    @Nullable
    public String getExpireDate() { return expireDate; }

    @Nullable
    public String getFactorySerialNumber() { return factorySerialNumber; }

    public String getGtin() { return gtin; }

    @Nullable
    public String getMessage() { return message; }

    public String getPackageType() { return packageType; }

    @Nullable
    public String getParent() { return parent; }

    public String getPrintView() { return printView; }

    @Nullable
    public String getProducerInn() { return producerInn; }

    @Nullable
    public String getProductionBatchNumber() { return productionBatchNumber; }

    @Nullable
    public String getProductionDate() { return productionDate; }

    @Nullable
    public String getProductionSerialNumber() { return productionSerialNumber; }

    @Nullable
    public String getPrVetDocument() { return prVetDocument; }

    @Nullable
    public Boolean getBlocked() { return isBlocked; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(cis);
        parcel.writeInt(found ? 1 : 0);
        parcel.writeInt(valid ? 1 : 0);
        parcel.writeString(printView);
        parcel.writeString(gtin);
        parcel.writeIntArray(groupIds);
        parcel.writeInt(verified ? 1 : 0);
        parcel.writeInt(realizable ? 1 : 0);
        parcel.writeInt(utilized ? 1 : 0);
        parcel.writeString(expireDate);
        parcel.writeTypedObject(this.variableExpirations, flags);
        parcel.writeString(productionDate);
        parcel.writeValue(productWeight);
        parcel.writeString(prVetDocument);
        parcel.writeValue(isOwner);
        parcel.writeValue(isBlocked);
        parcel.writeTypedArray(ogvs, flags);
        parcel.writeString(message);
        parcel.writeValue(errorCode);
        parcel.writeInt(isTracking ? 1 : 0);
        parcel.writeInt(sold ? 1 : 0);
        parcel.writeString(eliminationState != null ? eliminationState.name() : null);
        parcel.writeValue(mrp);
        parcel.writeValue(smp);
        parcel.writeValue(grayZone);
        parcel.writeValue(innerUnitCount);
        parcel.writeValue(soldUnitCount);
        parcel.writeString(packageType);
        parcel.writeString(parent);
        parcel.writeString(producerInn);
        parcel.writeString(productionSerialNumber);
        parcel.writeString(productionBatchNumber);
        parcel.writeString(factorySerialNumber);
        parcel.writeValue(packageQuantity);
    }

    public static final Creator<CodeCheck> CREATOR = new Creator<>() {
        @Override
        public CodeCheck createFromParcel(Parcel parcel) {
            return new CodeCheck(parcel);
        }

        @Override
        public CodeCheck[] newArray(int i) {
            return new CodeCheck[i];
        }
    };

    /**
     * <ul>
     *     <li>RAR — Росалкогольтабакконтроль;</li>
     *     <li>FTS — ФТС России;</li>
     *     <li>FNS — ФНС России;</li>
     *     <li>RSHN — Россельхознадзор;</li>
     *     <li>RPN — Роспотребнадзор;</li>
     *     <li>MVD — МВД России;</li>
     *     <li>RZN — Росздравнадзор</li>
     * </ul>
     */
    public enum OGVS implements Parcelable {
        RAR, FTS, FNS, RSHN, RPN, MVD, RZN;

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.name());
        }

        public static Creator<OGVS> CREATOR = new Creator<>() {
            @Override
            public OGVS createFromParcel(Parcel parcel) {
                try {
                    return OGVS.valueOf(parcel.readString());
                } catch (Exception ex) {
                    return null;
                }
            }

            @Override
            public OGVS[] newArray(int i) {
                return new OGVS[0];
            }
        };
    }

    /**
     * <ul>
     *     <li>«BY_SAMPLES» («Продажа по образцам»)</li>
     *     <li>«DISTANCE» («Дистанционная продажа»)</li>
     *     <li>«OWN_USE» («Использование для собственных нужд»)</li>
     *     <li>«PRODUCTION_USE» («Использование для производственных целей»)</li>
     * </ul>
     */
    public enum EliminationState {
        BY_SAMPLES, DISTANCE, OWN_USE, PRODUCTION_USE
    }

    @Nullable
    private Integer readInteger(Parcel parcel) {
        try {
            return (Integer) parcel.readValue(Integer.class.getClassLoader());
        } catch (Exception ex) {
            return null;
        }
    }

    @Nullable
    private Boolean readBoolean(Parcel parcel) {
        try {
            return (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        } catch (Exception ex) {
            return null;
        }
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cis: ").append(cis).append("\n");
        builder.append("IsFound: ").append(found).append("\n");
        builder.append("IsValid: ").append(valid).append("\n");
        builder.append("PrintView").append(printView).append("\n");
        builder.append("Gtin: ").append(gtin).append("\n");
        builder.append("GroupIds: ");
        if (groupIds == null || groupIds.length == 0) {
            builder.append("null\n");
        } else {
            for (int i = 0; i < groupIds.length; i++) {
                builder.append(groupIds[i]);

                if (i != groupIds.length - 1) {
                    builder.append(", ");
                }
            }
            builder.append("\n");
        }
        builder.append("IsVerified: ").append(verified).append("\n");
        builder.append("IsRealizable: ").append(realizable).append("\n");
        builder.append("IsUtilized: ").append(utilized).append("\n");
        builder.append("ExpireDate: ").append(expireDate).append("\n");
        builder.append("VariableExpirations: ");
        if (variableExpirations == null || variableExpirations.getExpirations().length == 0) {
            builder.append("null\n");
        } else {
            VariableExpiration[] expirations = variableExpirations.getExpirations();
            for (int i = 0; i < expirations.length; i++) {
                int first = expirations[i].getDegrees();
                String second = expirations[i].getDate();
                builder.append(first).append(":").append(second);

                if (i != variableExpirations.getExpirations().length - 1) {
                    builder.append(", ");
                }
            }
            builder.append("\n");
        }
        builder.append("ProductionDate: ").append(productionDate).append("\n");
        builder.append("ProductWeight: ").append(productWeight).append("\n");
        builder.append("PrVetDocument: ").append(prVetDocument).append("\n");
        builder.append("IsOwner: ").append(isOwner).append("\n");
        builder.append("IsBlocked: ").append(isBlocked).append("\n");
        builder.append("Ogvs: ");
        if (ogvs.length == 0) {
            builder.append("null\n");
        } else {
            for (int i = 0; i < ogvs.length; i++) {
                builder.append(ogvs[i]);

                if (i != ogvs.length - 1) {
                    builder.append(", ");
                }
            }
            builder.append("\n");
        }
        builder.append("Message: ").append(message).append("\n");
        builder.append("ErrorCode: ").append(errorCode).append("\n");
        builder.append("IsTracking: ").append(isTracking).append("\n");
        builder.append("IsSold: ").append(sold).append("\n");
        builder.append("EliminationState: ").append(eliminationState).append("\n");
        builder.append("Mrp: ").append(mrp).append("\n");
        builder.append("Smp: ").append(smp).append("\n");
        builder.append("IsGrayZone: ").append(grayZone).append("\n");
        builder.append("InnerUnitCount: ").append(innerUnitCount).append("\n");
        builder.append("SoldUnitCount: ").append(soldUnitCount).append("\n");
        builder.append("PackageType: ").append(packageType).append("\n");
        builder.append("Parent: ").append(parent).append("\n");
        builder.append("ProducerInn: ").append(producerInn).append("\n");
        builder.append("ProductionSerialNumber: ").append(productionSerialNumber).append("\n");
        builder.append("ProductionBatchNumber: ").append(productionBatchNumber).append("\n");
        builder.append("FactorySerialNumber: ").append(factorySerialNumber).append("\n");
        builder.append("PackageQuantity: ").append(packageQuantity).append("\n");
        return builder.toString();
    }
}
