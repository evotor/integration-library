package ru.evotor.framework.payment;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import ru.evotor.framework.Utils;

/**
 * Платёжная система
 */
public class PaymentSystem implements Parcelable {

    /**
     * Тип платёжной системы (наличные, электронные, пр.)
     */
    @NonNull
    private final PaymentType paymentType;
    /**
     * Описание платёжной системы, которое может быть показано пользователю
     */
    @NonNull
    private final String userDescription;
    /**
     * Постоянный идентификатор платёжной системы
     */
    @NonNull
    private final String paymentSystemId;

    public PaymentSystem(@NonNull PaymentType paymentType, @NonNull String userDescription, @NonNull String paymentSystemId) {
        this.paymentType = paymentType;
        this.userDescription = userDescription;
        this.paymentSystemId = paymentSystemId;
    }

    @NonNull
    public String getUserDescription() {
        return userDescription;
    }

    @NonNull
    public PaymentType getPaymentType() {
        return paymentType;
    }

    @NonNull
    public String getPaymentSystemId() {
        return paymentSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentSystem that = (PaymentSystem) o;

        if (paymentType != that.paymentType) return false;
        if (!userDescription.equals(that.userDescription)) return false;
        return paymentSystemId.equals(that.paymentSystemId);

    }

    @Override
    public int hashCode() {
        int result = paymentType.hashCode();
        result = 31 * result + userDescription.hashCode();
        result = 31 * result + paymentSystemId.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userDescription);
        dest.writeString(this.paymentSystemId);
        dest.writeString(this.paymentType.name());
    }

    protected PaymentSystem(Parcel in) {
        this.userDescription = in.readString();
        this.paymentSystemId = in.readString();
        this.paymentType = Utils.safeValueOf(PaymentType.class, in.readString(), PaymentType.UNKNOWN);
    }

    public static final Creator<PaymentSystem> CREATOR = new Creator<PaymentSystem>() {
        @Override
        public PaymentSystem createFromParcel(Parcel source) {
            return new PaymentSystem(source);
        }

        @Override
        public PaymentSystem[] newArray(int size) {
            return new PaymentSystem[size];
        }
    };

    @Override
    public String toString() {
        return "PaymentSystem{" +
                "paymentType=" + paymentType +
                ", userDescription='" + userDescription + '\'' +
                ", paymentSystemId='" + paymentSystemId + '\'' +
                '}';
    }
}
