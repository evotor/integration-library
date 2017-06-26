package ru.evotor.framework.payment;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.framework.Utils;

public class PaymentSystem implements Parcelable {

    private final PaymentType paymentType;
    private final String userDescription;

    public PaymentSystem(PaymentType paymentType, String userDescription) {
        this.paymentType = paymentType;
        this.userDescription = userDescription;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentSystem another = (PaymentSystem) o;

        if (paymentType != another.paymentType) {
            return false;
        }

        if (userDescription != null ? !userDescription.equals(another.userDescription) : another.userDescription != null)
            return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = userDescription != null ? userDescription.hashCode() : 0;
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userDescription);
        dest.writeString(this.paymentType.name());
    }

    protected PaymentSystem(Parcel in) {
        this.userDescription = in.readString();
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
}
