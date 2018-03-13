package ru.evotor.devices.commons.paysystem;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class PayInfo implements Parcelable {

    // цена
    private final BigDecimal price;

    public PayInfo(BigDecimal price) {
        this.price = price;
    }

    private PayInfo(Parcel parcel) {
        price = (BigDecimal) parcel.readSerializable();
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(price);
    }

    public static final Creator<PayInfo> CREATOR = new Creator<PayInfo>() {

        public PayInfo createFromParcel(Parcel in) {
            return new PayInfo(in);
        }

        public PayInfo[] newArray(int size) {
            return new PayInfo[size];
        }
    };

}
