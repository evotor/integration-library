package ru.evotor.framework.payment;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

public class PaymentPurpose implements Parcelable {

    @Nullable
    private final String identifier;
    @NonNull
    private final BigDecimal total;
    @Nullable
    private final String accountId;

    public PaymentPurpose(@Nullable String identifier, @NonNull BigDecimal total, @Nullable String accountId) {
        this.identifier = identifier;
        this.total = total;
        this.accountId = accountId;
    }

    @Nullable
    public String getIdentifier() {
        return identifier;
    }

    @NonNull
    public BigDecimal getTotal() {
        return total;
    }

    @Nullable
    public String getAccountId() {
        return accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentPurpose that = (PaymentPurpose) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null)
            return false;
        if (!total.equals(that.total)) return false;
        return accountId != null ? accountId.equals(that.accountId) : that.accountId == null;

    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + total.hashCode();
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeString(this.total.toPlainString());
        dest.writeString(this.accountId);
    }

    protected PaymentPurpose(Parcel in) {
        this.identifier = in.readString();
        this.total = new BigDecimal(in.readString());
        this.accountId = in.readString();
    }

    public static final Creator<PaymentPurpose> CREATOR = new Creator<PaymentPurpose>() {
        @Override
        public PaymentPurpose createFromParcel(Parcel source) {
            return new PaymentPurpose(source);
        }

        @Override
        public PaymentPurpose[] newArray(int size) {
            return new PaymentPurpose[size];
        }
    };
}
