package ru.evotor.framework.payment;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Аккаунт в рамках платёжной системы
 */
public class PaymentAccount implements Parcelable {

    /**
     * Описание аккаунта, которое можно отобразить пользователю
     */
    @Nullable
    private final String userDescription;
    /**
     * Идентификатор аккаунта в рамках платёжной системы
     */
    @Nullable
    private final String accountId;

    public PaymentAccount(@Nullable String userDescription, @Nullable String accountId) {
        this.userDescription = userDescription;
        this.accountId = accountId;
    }

    @Nullable
    public String getUserDescription() {
        return userDescription;
    }

    @Nullable
    public String getAccountId() {
        return accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentAccount that = (PaymentAccount) o;

        if (userDescription != null ? !userDescription.equals(that.userDescription) : that.userDescription != null)
            return false;
        return accountId != null ? accountId.equals(that.accountId) : that.accountId == null;

    }

    @Override
    public int hashCode() {
        int result = userDescription != null ? userDescription.hashCode() : 0;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userDescription);
        dest.writeString(this.accountId);
    }

    protected PaymentAccount(Parcel in) {
        this.userDescription = in.readString();
        this.accountId = in.readString();
    }

    public static final Creator<PaymentAccount> CREATOR = new Creator<PaymentAccount>() {
        @Override
        public PaymentAccount createFromParcel(Parcel source) {
            return new PaymentAccount(source);
        }

        @Override
        public PaymentAccount[] newArray(int size) {
            return new PaymentAccount[size];
        }
    };
}
