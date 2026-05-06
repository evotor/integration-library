package ru.evotor.tspiot.result.model.online;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.VariableExpirations;
import ru.evotor.tspiot.result.model.base.BaseCodeCheck;

public class OnlineCodeCheck extends BaseCodeCheck implements Parcelable {

    /** Версия OnlineCodeCheck */
    private final static int VERSION = 1;

    /** Дата и время истечения срока годности */
    @Nullable private final Date expireDate;

    /** Дата производства продукции */
    @Nullable private final Date productionDate;

    /** Переменный вес продукции (в граммах) */
    @Nullable private final Integer productWeight;

    /** Производственный ветеринарный сопроводительный документ */
    @Nullable private final String prVetDocument;

    /** Признак, определяющий, что запрос направлен владельцем кода (определяется по аутентификационному токену) */
    @Nullable private final Boolean isOwner;

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

    /** Признак использования причин выбытия, разрешающих продажу КМ */
    @Nullable private final Integer eliminationState;

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

    private OnlineCodeCheck(Parcel parcel) {
        super(parcel);

        int version = parcel.readInt();
        this.expireDate = (Date) parcel.readSerializable();
        this.productionDate = (Date) parcel.readSerializable();
        this.productWeight = Utils.readInteger(parcel);
        this.prVetDocument = parcel.readString();
        this.isOwner = Utils.readBoolean(parcel);
        this.message = parcel.readString();
        this.errorCode = Utils.readInteger(parcel);
        this.isTracking = parcel.readInt() == 1;
        this.eliminationState = Utils.readInteger(parcel);
        this.grayZone = Utils.readBoolean(parcel);
        this.innerUnitCount = Utils.readInteger(parcel);
        this.soldUnitCount = Utils.readInteger(parcel);
        this.packageType = parcel.readString();
        this.parent = parcel.readString();
        this.producerInn = parcel.readString();
        this.productionSerialNumber = parcel.readString();
        this.productionBatchNumber = parcel.readString();
        this.factorySerialNumber = parcel.readString();
        this.packageQuantity = Utils.readInteger(parcel);
    }

    public OnlineCodeCheck(
            String cis,
            Boolean found,
            Boolean valid,
            String printView,
            String gtin,
            int[] groupIds,
            Boolean verified,
            Boolean realizable,
            Boolean utilized,
            @Nullable Date expireDate,
            @Nullable VariableExpirations variableExpirations,
            @Nullable Date productionDate,
            @Nullable Integer productWeight,
            @Nullable String prVetDocument,
            @Nullable Boolean isOwner,
            @Nullable Boolean isBlocked,
            @Nullable String[] ogvs,
            @Nullable String message,
            @Nullable Integer errorCode,
            Boolean isTracking,
            Boolean sold,
            @Nullable Integer eliminationState,
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
        super(
                cis,
                found,
                valid,
                printView,
                gtin,
                groupIds,
                verified,
                realizable,
                utilized,
                variableExpirations,
                isBlocked,
                ogvs,
                sold,
                mrp,
                smp
        );

        this.expireDate = expireDate;
        this.productionDate = productionDate;
        this.productWeight = productWeight;
        this.prVetDocument = prVetDocument;
        this.isOwner = isOwner;
        this.message = message;
        this.errorCode = errorCode;
        this.isTracking = isTracking;
        this.eliminationState = eliminationState;
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

    @Nullable
    public Boolean getGrayZone() { return grayZone; }

    @Nullable
    public Boolean getOwner() { return isOwner; }

    public boolean isTracking() { return isTracking; }

    @Nullable
    public Date getExpireDate() { return expireDate; }

    @Nullable
    public Date getProductionDate() { return productionDate; }

    @Nullable
    public Integer getEliminationState() { return eliminationState; }

    @Nullable
    public Integer getErrorCode() { return errorCode; }

    @Nullable
    public Integer getInnerUnitCount() { return innerUnitCount; }

    @Nullable
    public Integer getPackageQuantity() { return packageQuantity; }

    @Nullable
    public Integer getProductWeight() { return productWeight; }

    @Nullable
    public Integer getSoldUnitCount() { return soldUnitCount; }

    @Nullable
    public String getFactorySerialNumber() { return factorySerialNumber; }

    @Nullable
    public String getMessage() { return message; }

    public String getPackageType() { return packageType; }

    @Nullable
    public String getParent() { return parent; }

    @Nullable
    public String getProducerInn() { return producerInn; }

    @Nullable
    public String getProductionBatchNumber() { return productionBatchNumber; }

    @Nullable
    public String getProductionSerialNumber() { return productionSerialNumber; }

    @Nullable
    public String getPrVetDocument() { return prVetDocument; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.write(parcel, flags);

        parcel.writeInt(VERSION);
        parcel.writeSerializable(expireDate);
        parcel.writeSerializable(productionDate);
        parcel.writeValue(productWeight);
        parcel.writeString(prVetDocument);
        parcel.writeValue(isOwner);
        parcel.writeString(message);
        parcel.writeValue(errorCode);
        parcel.writeInt(isTracking ? 1 : 0);
        parcel.writeValue(eliminationState);
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

    @NonNull
    @Override
    public String toString() {
        return super.toString() + "ExpireDate: " + Utils.toString(expireDate) + "\n" +
                "ProductionDate: " + Utils.toString(productionDate) + "\n" +
                "ProductWeight: " + Utils.toString(productWeight) + "\n" +
                "PrVetDocument: " + Utils.toString(prVetDocument) + "\n" +
                "IsOwner: " + Utils.toString(isOwner) + "\n" +
                "Message: " + Utils.toString(message) + "\n" +
                "ErrorCode: " + Utils.toString(errorCode) + "\n" +
                "IsTracking: " + Utils.toString(isTracking) + "\n" +
                "EliminationState: " + Utils.toString(eliminationState) + "\n" +
                "IsGrayZone: " + Utils.toString(grayZone) + "\n" +
                "InnerUnitCount: " + Utils.toString(innerUnitCount) + "\n" +
                "SoldUnitCount: " + Utils.toString(soldUnitCount) + "\n" +
                "PackageType: " + Utils.toString(packageType) + "\n" +
                "Parent: " + Utils.toString(parent) + "\n" +
                "ProducerInn: " + Utils.toString(producerInn) + "\n" +
                "ProductionSerialNumber: " + Utils.toString(productionSerialNumber) + "\n" +
                "ProductionBatchNumber: " + Utils.toString(productionBatchNumber) + "\n" +
                "FactorySerialNumber: " + Utils.toString(factorySerialNumber) + "\n" +
                "PackageQuantity: " + Utils.toString(packageQuantity) + "\n";
    }

    public final static Creator<OnlineCodeCheck> CREATOR = new Creator<>() {
        @Override
        public OnlineCodeCheck createFromParcel(Parcel parcel) {
            return new OnlineCodeCheck(parcel);
        }

        @Override
        public OnlineCodeCheck[] newArray(int size) {
            return new OnlineCodeCheck[size];
        }
    };
}
