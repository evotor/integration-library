package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by a.kuznetsov on 02/05/2017.
 */

public class Tax implements Parcelable {

    private final TaxNumber taxNumber;
    private final BigDecimal taxRatePercent;
    private final BigDecimal value;

    public Tax(TaxNumber taxNumber, BigDecimal taxRatePercent, BigDecimal value) {
        this.taxNumber = taxNumber;
        this.taxRatePercent = taxRatePercent;
        this.value = value;
    }

    public TaxNumber getTaxNumber() {
        return taxNumber;
    }

    public BigDecimal getTaxRatePercent() {
        return taxRatePercent;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tax tax = (Tax) o;

        if (taxNumber != tax.taxNumber) return false;
        if (taxRatePercent != null ? !taxRatePercent.equals(tax.taxRatePercent) : tax.taxRatePercent != null)
            return false;
        return value != null ? value.equals(tax.value) : tax.value == null;

    }

    @Override
    public int hashCode() {
        int result = taxNumber != null ? taxNumber.hashCode() : 0;
        result = 31 * result + (taxRatePercent != null ? taxRatePercent.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.taxNumber == null ? -1 : this.taxNumber.ordinal());
        dest.writeSerializable(this.taxRatePercent);
        dest.writeSerializable(this.value);
    }

    protected Tax(Parcel in) {
        int tmpTaxNumber = in.readInt();
        this.taxNumber = tmpTaxNumber == -1 ? null : TaxNumber.values()[tmpTaxNumber];
        this.taxRatePercent = (BigDecimal) in.readSerializable();
        this.value = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<Tax> CREATOR = new Parcelable.Creator<Tax>() {
        @Override
        public Tax createFromParcel(Parcel source) {
            return new Tax(source);
        }

        @Override
        public Tax[] newArray(int size) {
            return new Tax[size];
        }
    };
}
