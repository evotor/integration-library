package ru.evotor.tspiot.result.model.online;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.VariableExpirations;
import ru.evotor.tspiot.result.model.base.BaseCodeCheck;

public class OnlineCodeCheck extends BaseCodeCheck {

    /**
     * Версия OnlineCodeCheck
     */
    private final static int VERSION = 2;

    /**
     * Дата и время истечения срока годности
     */
    @Nullable
    private final Date expireDate;

    /**
     * Дата производства продукции
     */
    @Nullable
    private final Date productionDate;

    /**
     * Переменный вес продукции (в граммах)
     */
    @Nullable
    private final Integer productWeight;

    /**
     * Производственный ветеринарный сопроводительный документ
     */
    @Nullable
    private final String prVetDocument;

    /**
     * Признак, определяющий, что запрос направлен владельцем кода (определяется по аутентификационному токену)
     */
    @Nullable
    private final Boolean isOwner;

    /**
     * Сообщение об ошибке
     */
    @Nullable
    private final String message;

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
    @Nullable
    private final Integer errorCode;

    /**
     * Признак контроля прослеживаемости в товарной группе
     */
    @Nullable
    private final Boolean isTracking;

    /**
     * Признак использования причин выбытия, разрешающих продажу КМ
     */
    @Nullable
    private final Integer eliminationState;

    /**
     * Признак принадлежности табачной продукции к «серой зоне»
     */
    @Nullable
    private final Boolean grayZone;

    /**
     * Количество единиц товара в потребительской упаковке / Фактический объём / Фактический вес
     */
    @Nullable
    private final Integer innerUnitCount;

    /**
     * Счётчик проданного и возвращённого товара
     */
    @Nullable
    private final Integer soldUnitCount;

    /**
     * Тип упаковки
     */
    @Nullable
    private final String packageType;

    /**
     * КИ агрегата
     */
    @Nullable
    private final String parent;

    /**
     * ИНН производителя
     */
    @Nullable
    private final String producerInn;

    /**
     * Номер производственной серии
     */
    @Nullable
    private final String productionSerialNumber;

    /**
     * Номер производственной партии
     */
    @Nullable
    private final String productionBatchNumber;

    /**
     * Заводской серийный номер
     */
    @Nullable
    private final String factorySerialNumber;

    /**
     * Ёмкость КИГУ
     */
    @Nullable
    private final Integer packageQuantity;

    private OnlineCodeCheck(Parcel parcel) {
        super(parcel);

        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 2);

        this.expireDate = (Date) local.readSerializable();
        this.productionDate = (Date) local.readSerializable();
        this.productWeight = Utils.readInteger(local);
        this.prVetDocument = local.readString();
        this.isOwner = Utils.readBoolean(local);
        this.message = local.readString();
        this.errorCode = Utils.readInteger(local);
        this.isTracking = local.readInt() == 1;
        this.eliminationState = Utils.readInteger(local);
        this.grayZone = Utils.readBoolean(local);
        this.innerUnitCount = Utils.readInteger(local);
        this.soldUnitCount = Utils.readInteger(local);
        this.packageType = local.readString();
        this.parent = local.readString();
        this.producerInn = local.readString();
        this.productionSerialNumber = local.readString();
        this.productionBatchNumber = local.readString();
        this.factorySerialNumber = local.readString();
        this.packageQuantity = Utils.readInteger(local);

        endLocalParcelRead(local, version, 2);
    }

    public OnlineCodeCheck(
            String cis,
            @Nullable Boolean found,
            @Nullable Boolean valid,
            @Nullable String printView,
            @Nullable String gtin,
            @Nullable int[] groupIds,
            @Nullable Boolean verified,
            @Nullable Boolean realizable,
            @Nullable Boolean utilized,
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
            @Nullable Boolean isTracking,
            @Nullable Boolean sold,
            @Nullable Integer eliminationState,
            @Nullable Integer mrp,
            @Nullable Integer smp,
            @Nullable Boolean grayZone,
            @Nullable Integer innerUnitCount,
            @Nullable Integer soldUnitCount,
            @Nullable String packageType,
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
    public Boolean getGrayZone() {
        return grayZone;
    }

    @Nullable
    public Boolean getOwner() {
        return isOwner;
    }

    @Nullable
    public Boolean isTracking() {
        return isTracking;
    }

    @Nullable
    public Date getExpireDate() {
        return expireDate;
    }

    @Nullable
    public Date getProductionDate() {
        return productionDate;
    }

    @Nullable
    public Integer getEliminationState() {
        return eliminationState;
    }

    @Nullable
    public Integer getErrorCode() {
        return errorCode;
    }

    @Nullable
    public Integer getInnerUnitCount() {
        return innerUnitCount;
    }

    @Nullable
    public Integer getPackageQuantity() {
        return packageQuantity;
    }

    @Nullable
    public Integer getProductWeight() {
        return productWeight;
    }

    @Nullable
    public Integer getSoldUnitCount() {
        return soldUnitCount;
    }

    @Nullable
    public String getFactorySerialNumber() {
        return factorySerialNumber;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    public String getPackageType() {
        return packageType;
    }

    @Nullable
    public String getParent() {
        return parent;
    }

    @Nullable
    public String getProducerInn() {
        return producerInn;
    }

    @Nullable
    public String getProductionBatchNumber() {
        return productionBatchNumber;
    }

    @Nullable
    public String getProductionSerialNumber() {
        return productionSerialNumber;
    }

    @Nullable
    public String getPrVetDocument() {
        return prVetDocument;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.write(parcel, flags);

        parcel.writeInt(VERSION);
        writeToParcel(parcel, local -> {
            local.writeSerializable(expireDate);
            local.writeSerializable(productionDate);
            local.writeValue(productWeight);
            local.writeString(prVetDocument);
            local.writeValue(isOwner);
            local.writeString(message);
            local.writeValue(errorCode);
            local.writeValue(isTracking);
            local.writeValue(eliminationState);
            local.writeValue(grayZone);
            local.writeValue(innerUnitCount);
            local.writeValue(soldUnitCount);
            local.writeString(packageType);
            local.writeString(parent);
            local.writeString(producerInn);
            local.writeString(productionSerialNumber);
            local.writeString(productionBatchNumber);
            local.writeString(factorySerialNumber);
            local.writeValue(packageQuantity);
        });
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
