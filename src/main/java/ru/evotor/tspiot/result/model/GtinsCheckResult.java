package ru.evotor.tspiot.result.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class GtinsCheckResult implements Parcelable {

    /** Версия GtinsCheckResult */
    private final static int VERSION = 1;

    @NonNull private final List<GtinCheckResult> gtinsCheckList;

    private GtinsCheckResult(Parcel parcel) {
        int version = parcel.readInt();
        List<GtinCheckResult> gtinsCheckList = parcel.createTypedArrayList(GtinCheckResult.CREATOR);
        this.gtinsCheckList = gtinsCheckList == null ? new ArrayList<>() : gtinsCheckList;
    }

    public GtinsCheckResult(@NonNull List<GtinCheckResult> gtinsCheckList) {
        this.gtinsCheckList = gtinsCheckList;
    }

    @NonNull
    public List<GtinCheckResult> getGtinsCheckList() { return gtinsCheckList; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeTypedList(gtinsCheckList);
    }

    public static Creator<GtinsCheckResult> CREATOR = new Creator<>() {
        @Override
        public GtinsCheckResult createFromParcel(Parcel parcel) {
            return new GtinsCheckResult(parcel);
        }

        @Override
        public GtinsCheckResult[] newArray(int size) {
            return new GtinsCheckResult[size];
        }
    };
}
