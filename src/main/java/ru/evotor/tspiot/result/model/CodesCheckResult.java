package ru.evotor.tspiot.result.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import java.util.List;
import ru.evotor.tspiot.result.model.offline.OfflineCodesCheck;
import ru.evotor.tspiot.result.model.online.OnlineCodesCheck;

public class CodesCheckResult implements Parcelable {

    /** Версия CodesCheckResult */
    private final static int VERSION = 1;

    @Nullable private final List<OnlineCodesCheck> onlineCodesChecks;

    @Nullable private final List<OfflineCodesCheck> offlineCodesChecks;

    private CodesCheckResult(Parcel parcel) {
        int version = parcel.readInt();
        this.onlineCodesChecks = parcel.createTypedArrayList(OnlineCodesCheck.CREATOR);
        this.offlineCodesChecks = parcel.createTypedArrayList(OfflineCodesCheck.CREATOR);
    }

    public CodesCheckResult(@Nullable List<OnlineCodesCheck> onlineCodesChecks, @Nullable List<OfflineCodesCheck> offlineCodesChecks) {
        this.onlineCodesChecks = onlineCodesChecks;
        this.offlineCodesChecks = offlineCodesChecks;
    }

    @Nullable
    public List<OnlineCodesCheck> getOnlineCodesChecks() { return onlineCodesChecks; }

    @Nullable
    public List<OfflineCodesCheck> getOfflineCodesChecks() { return offlineCodesChecks; }

    public boolean isOffline() { return offlineCodesChecks != null && !offlineCodesChecks.isEmpty(); }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeTypedList(onlineCodesChecks);
        parcel.writeTypedList(offlineCodesChecks);
    }

    public static Creator<CodesCheckResult> CREATOR = new Creator<>() {
        @Override
        public CodesCheckResult createFromParcel(Parcel parcel) {
            return new CodesCheckResult(parcel);
        }

        @Override
        public CodesCheckResult[] newArray(int size) {
            return new CodesCheckResult[size];
        }
    };
}
