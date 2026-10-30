package ru.evotor.tspiot.result.model;

import android.os.Parcel;

import androidx.annotation.NonNull;

import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;

import java.util.ArrayList;
import java.util.List;

public class GtinsCheckResult extends BaseTsPioTObject {

    /** Версия GtinsCheckResult */
    private final static int VERSION = 2;

    @NonNull private final List<GtinCheckResult> gtinsCheckList;

    private GtinsCheckResult(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 2);

        List<GtinCheckResult> gtinsCheckList = local.createTypedArrayList(GtinCheckResult.CREATOR);
        this.gtinsCheckList = gtinsCheckList == null ? new ArrayList<>() : gtinsCheckList;

        endLocalParcelRead(local,version, 2);
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
        writeToParcel(parcel, local -> {
            local.writeTypedList(gtinsCheckList);
        });
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
