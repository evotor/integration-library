package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Описание характеристик печатной группы - отдельного печатного документа в рамках одного чека
 */
public class PrintGroup implements Parcelable {
    /**
     * Идентификатор печатной группы
     */
    private String identifier;
    /**
     * Тип печатной группы (фискальный, енвд и др.)
     */
    private Type type;
    /**
     * Название организации
     */
    private String orgName;
    /**
     * ИНН организации
     */
    private String orgInn;
    /**
     * Адрес организации
     */
    private String orgAddress;
    /**
     * Система налогооблажения
     */
    private TaxationSystem taxationSystem;

    /**
     * Печатать/не печатать чек
     */
    private boolean shouldPrintReceipt;

    public PrintGroup(
            String identifier,
            Type type,
            String orgName,
            String orgInn,
            String orgAddress,
            TaxationSystem taxationSystem,
            boolean shouldPrintReceipt
    ) {
        this.identifier = identifier;
        this.type = type;
        this.orgName = orgName;
        this.orgInn = orgInn;
        this.orgAddress = orgAddress;
        this.taxationSystem = taxationSystem;
        this.shouldPrintReceipt = shouldPrintReceipt;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.orgName);
        dest.writeString(this.orgInn);
        dest.writeString(this.orgAddress);
        dest.writeInt(this.taxationSystem == null ? -1 : this.taxationSystem.ordinal());
        dest.writeInt(this.shouldPrintReceipt ? 1 : 0);
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
        return taxationSystem == that.taxationSystem;
    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (orgInn != null ? orgInn.hashCode() : 0);
        result = 31 * result + (orgAddress != null ? orgAddress.hashCode() : 0);
        result = 31 * result + (taxationSystem != null ? taxationSystem.hashCode() : 0);
        return result;
    }
}
