package ru.evotor.framework.payment;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

/**
 * Оплата, списком которых может быть оплачен чек. Содержит сумму и цель оплаты (платёжную систему и аккаунт).
 */
public class PaymentPurpose implements Parcelable {

    /**
     * Собственный идентификатор оплаты
     */
    @Nullable
    private final String identifier;
    /**
     * Идентификатор платёжной системы
     */
    @Nullable
    private final String paymentSystemId;
    /**
     * Сумма к оплате
     */
    @NonNull
    private final BigDecimal total;
    /**
     * Идентификатор аккаунта в рамках платёжной системы
     */
    @Nullable
    private final String accountId;
    /**
     * Сообщение, которое будет показано пользователю в момент оплаты
     */
    @Nullable
    private final String userMessage;

    public PaymentPurpose(@Nullable String identifier, @Nullable String paymentSystemId, @NonNull BigDecimal total, @Nullable String accountId, @Nullable String userMessage) {
        this.identifier = identifier;
        this.paymentSystemId = paymentSystemId;
        this.total = total;
        this.accountId = accountId;
        this.userMessage = userMessage;
    }

    @Nullable
    public String getIdentifier() {
        return identifier;
    }

    @Nullable
    public String getPaymentSystemId() {
        return paymentSystemId;
    }

    @NonNull
    public BigDecimal getTotal() {
        return total;
    }

    @Nullable
    public String getAccountId() {
        return accountId;
    }

    @Nullable
    public String getUserMessage() {
        return userMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentPurpose that = (PaymentPurpose) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null)
            return false;
        if (paymentSystemId != null ? !paymentSystemId.equals(that.paymentSystemId) : that.paymentSystemId != null)
            return false;
        if (!total.equals(that.total)) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null)
            return false;
        return userMessage != null ? userMessage.equals(that.userMessage) : that.userMessage == null;

    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (paymentSystemId != null ? paymentSystemId.hashCode() : 0);
        result = 31 * result + total.hashCode();
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (userMessage != null ? userMessage.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeString(this.paymentSystemId);
        dest.writeString(this.total.toPlainString());
        dest.writeString(this.accountId);
        dest.writeString(this.userMessage);
    }

    protected PaymentPurpose(Parcel in) {
        this.identifier = in.readString();
        this.paymentSystemId = in.readString();
        this.total = new BigDecimal(in.readString());
        this.accountId = in.readString();
        this.userMessage = in.readString();
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
