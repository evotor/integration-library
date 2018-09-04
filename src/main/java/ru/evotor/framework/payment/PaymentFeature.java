package ru.evotor.framework.payment;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.framework.Utils;

/**
 * Признак способа расчета для позиции
 */
public class PaymentFeature implements Parcelable {

    /**
     * Тип признака способа расчета для позиции
     */
    @NonNull
    private final PaymentFeatureType paymentFeatureType;

    /**
     * Кол-во расчетных денег
     */
    @Nullable
    private final BigDecimal amount;

    public PaymentFeature(@NonNull PaymentFeatureType paymentFeatureType, @Nullable BigDecimal amount) {
        this.paymentFeatureType = paymentFeatureType;
        this.amount = amount;
    }

    @NonNull
    public PaymentFeatureType getPaymentFeatureType() {
        return paymentFeatureType;
    }

    @Nullable
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentFeature that = (PaymentFeature) o;

        if (paymentFeatureType != that.paymentFeatureType) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = paymentFeatureType.hashCode();
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.paymentFeatureType.name());
        dest.writeSerializable(this.amount);
    }

    protected PaymentFeature(Parcel in) {
        this.paymentFeatureType = Utils.safeValueOf(PaymentFeatureType.class, in.readString(), PaymentFeatureType.UNKNOWN);
        this.amount = (BigDecimal) in.readSerializable();
    }

    public static final Creator<PaymentFeature> CREATOR = new Creator<PaymentFeature>() {
        @Override
        public PaymentFeature createFromParcel(Parcel source) {
            return new PaymentFeature(source);
        }

        @Override
        public PaymentFeature[] newArray(int size) {
            return new PaymentFeature[size];
        }
    };

    @Override
    public String toString() {
        return "PaymentSystem{" +
                "paymentFeatureType=" + paymentFeatureType +
                ", amount='" + amount + '\'' +
                '}';
    }
}
