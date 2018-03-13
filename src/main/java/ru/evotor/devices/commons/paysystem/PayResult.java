package ru.evotor.devices.commons.paysystem;

import android.os.Parcel;

import ru.evotor.devices.commons.result.AbstractInnerParcelable;

public class PayResult extends AbstractInnerParcelable {

    // ррн проведённой операции
    private final String rrn;
    // строки банковского чека для печати
    private final String[] slip;

    public PayResult(String rrn, String[] slip) {
        this.rrn = rrn;
        this.slip = slip;
    }

    public String getRrn() {
        return rrn;
    }

    public String[] getSlip() {
        return slip;
    }

    public void writeToParcel(Parcel parcel) {
        parcel.writeString(rrn);
        parcel.writeInt(slip == null ? -1 : slip.length);
        if (slip != null) {
            parcel.writeStringArray(slip);
        }
    }

    public PayResult(Parcel parcel) {
        rrn = parcel.readString();
        int slipLength = parcel.readInt();
        if (slipLength >= 0) {
            slip = new String[slipLength];
            parcel.readStringArray(slip);
        } else {
            slip = null;
        }
    }


}
