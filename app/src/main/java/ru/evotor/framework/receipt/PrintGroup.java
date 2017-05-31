package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by a.kuznetsov on 30/04/2017.
 */

public class PrintGroup implements Parcelable {
    private String identifier;
    private Type type;
    private String orgName;
    private String orgInn;
    private String orgAddress;
    private TaxationSystem taxationSystem;

    public PrintGroup(
            String identifier,
            Type type,
            String orgName,
            String orgInn,
            String orgAddress,
            TaxationSystem taxationSystem
    ) {
        this.identifier = identifier;
        this.type = type;
        this.orgName = orgName;
        this.orgInn = orgInn;
        this.orgAddress = orgAddress;
        this.taxationSystem = taxationSystem;
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
}
