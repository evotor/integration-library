package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Nullable;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import ru.evotor.framework.ParcelableUtils;

/**
 * Печатная группа – элемент кассового чека, содержащий данные об организации, которая осуществляет торговую операцию.
 */
public class PrintGroup implements Parcelable {

    /**
     * Текущая версия объекта PrintGroup.
     */
    private static final int VERSION = 2;

    private static final String DEFAULT_PRINT_GROUP_IDENTIFIER = "46dd89f0-3a54-470a-a166-ad01fa34b86a";

    public static final PrintGroup DEFAULT = new PrintGroup(DEFAULT_PRINT_GROUP_IDENTIFIER, Type.CASH_RECEIPT, null, null, null, null, true, null, null);

    /**
     * Идентификатор печатной группы.
     */
    private String identifier;
    /**
     * Тип печатной группы (фискальный, енвд и др.)
     */
    private Type type;
    /**
     * Название организации, на которую зарегестрированная касса.
     */
    private String orgName;
    /**
     * ИНН организации, на которую зарегестрированная касса.
     */
    private String orgInn;
    /**
     * Адрес организации, на которую зарегестрированная касса.
     */
    private String orgAddress;
    /**
     * Система налогообложения, которая применялась при расчёте.
     */
    private TaxationSystem taxationSystem;

    /**
     * Флаг, указывающий необходимость печати чека.
     */
    private boolean shouldPrintReceipt;

    /**
     * Реквизиты покупателя.
     */
    @Nullable
    private Purchaser purchaser;

    /**
     * Аттрибуты маркированных лекарств
     */
    @Nullable
    private MedicineAttribute medicineAttribute;

    @Deprecated
    public PrintGroup(
            String identifier,
            Type type,
            String orgName,
            String orgInn,
            String orgAddress,
            TaxationSystem taxationSystem,
            boolean shouldPrintReceipt
    ) {
        this(identifier, type, orgName, orgInn, orgAddress, taxationSystem, shouldPrintReceipt, null, null);
    }

    public PrintGroup(
            String identifier,
            Type type,
            String orgName,
            String orgInn,
            String orgAddress,
            TaxationSystem taxationSystem,
            boolean shouldPrintReceipt,
            @Nullable Purchaser purchaser,
            @Nullable MedicineAttribute medicineAttribute
    ) {
        this.identifier = identifier;
        this.type = type;
        this.orgName = orgName;
        this.orgInn = orgInn;
        this.orgAddress = orgAddress;
        this.taxationSystem = taxationSystem;
        this.shouldPrintReceipt = shouldPrintReceipt;
        this.purchaser = purchaser;
        this.medicineAttribute = medicineAttribute;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgInn() {
        return orgInn;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public TaxationSystem getTaxationSystem() {
        return taxationSystem;
    }

    public boolean isShouldPrintReceipt() {
        return shouldPrintReceipt;
    }

    @Nullable
    public Purchaser getPurchaser() {
        return purchaser;
    }

    @Nullable
    public MedicineAttribute getMedicineAttribute() {
        return medicineAttribute;
    }

    public enum Type {
        /**
         * Кассовый чек, напечатанный средствами ККМ
         */
        CASH_RECEIPT,
        /**
         * квитанция
         */
        INVOICE,
        /**
         * ЕНВД чек, напечатанный строками
         */
        STRING_UTII
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, final int flags) {
        dest.writeString(this.identifier);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.orgName);
        dest.writeString(this.orgInn);
        dest.writeString(this.orgAddress);
        dest.writeInt(this.taxationSystem == null ? -1 : this.taxationSystem.ordinal());
        dest.writeInt(this.shouldPrintReceipt ? 1 : 0);

        ParcelableUtils.writeExpand(dest, VERSION, new Function1<Parcel, Unit>() {
            @Override
            public Unit invoke(Parcel parcel) {
                /* version = 1*/
                parcel.writeParcelable(PrintGroup.this.purchaser, flags);
                /* version = 2*/
                parcel.writeParcelable(PrintGroup.this.medicineAttribute, flags);
                return Unit.INSTANCE;
            }
        });
    }

    protected PrintGroup(Parcel in) {
        this.identifier = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
        this.orgName = in.readString();
        this.orgInn = in.readString();
        this.orgAddress = in.readString();
        int tmpTaxationSystem = in.readInt();
        this.taxationSystem = tmpTaxationSystem == -1 ? null : TaxationSystem.values()[tmpTaxationSystem];
        try {
            this.shouldPrintReceipt = in.readInt() == 1;
        } catch (Exception e) {
            return;
        }

        ParcelableUtils.readExpand(in, VERSION, new Function2<Parcel, Integer, Unit>() {
            @Override
            public Unit invoke(Parcel parcel, Integer version) {
                if (version >= 1) {
                    PrintGroup.this.purchaser = parcel.readParcelable(Purchaser.class.getClassLoader());
                }

                if (version >= 2) {
                    PrintGroup.this.medicineAttribute = parcel.readParcelable(MedicineAttribute.class.getClassLoader());
                }

                return Unit.INSTANCE;
            }
        });

    }

    public static final Parcelable.Creator<PrintGroup> CREATOR = new Parcelable.Creator<PrintGroup>() {
        @Override
        public PrintGroup createFromParcel(Parcel source) {
            return new PrintGroup(source);
        }

        @Override
        public PrintGroup[] newArray(int size) {
            return new PrintGroup[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrintGroup that = (PrintGroup) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null)
            return false;
        if (type != that.type) return false;
        if (orgName != null ? !orgName.equals(that.orgName) : that.orgName != null) return false;
        if (orgInn != null ? !orgInn.equals(that.orgInn) : that.orgInn != null) return false;
        if (orgAddress != null ? !orgAddress.equals(that.orgAddress) : that.orgAddress != null)
            return false;
        if (shouldPrintReceipt != that.shouldPrintReceipt) return false;
        if (taxationSystem != that.taxationSystem) return false;
        if (purchaser != null ? !purchaser.equals(that.purchaser) : that.purchaser != null)
            return false;

        return medicineAttribute != null ? medicineAttribute.equals(that.medicineAttribute) : that.medicineAttribute == null;
    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (orgInn != null ? orgInn.hashCode() : 0);
        result = 31 * result + (orgAddress != null ? orgAddress.hashCode() : 0);
        result = 31 * result + (taxationSystem != null ? taxationSystem.hashCode() : 0);
        result = 31 * result + (shouldPrintReceipt ? 1 : 0);
        result = 31 * result + (purchaser != null ? purchaser.hashCode() : 0);
        result = 31 * result + (medicineAttribute != null ? medicineAttribute.hashCode() : 0);

        return result;
    }

    public boolean isDefault() {
        return DEFAULT_PRINT_GROUP_IDENTIFIER.equals(identifier);
    }

}
