package ru.evotor.tspiot.result.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import java.util.List;
import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.offline.OfflineCodesCheck;
import ru.evotor.tspiot.result.model.online.OnlineCodesCheck;

public class CodesCheckResult extends BaseTsPioTObject {

    /** Версия CodesCheckResult */
    private final static int VERSION = 2;

    @Nullable private final List<OnlineCodesCheck> onlineCodesChecks;

    @Nullable private final List<OfflineCodesCheck> offlineCodesChecks;

    private CodesCheckResult(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 2);

        this.onlineCodesChecks = local.createTypedArrayList(OnlineCodesCheck.CREATOR);
        this.offlineCodesChecks = local.createTypedArrayList(OfflineCodesCheck.CREATOR);

        endLocalParcelRead(local, version, 2);
    }

    public CodesCheckResult(@Nullable List<OnlineCodesCheck> onlineCodesChecks, @Nullable List<OfflineCodesCheck> offlineCodesChecks) {
        this.onlineCodesChecks = onlineCodesChecks;
        this.offlineCodesChecks = offlineCodesChecks;
    }

    @Nullable
    public List<OnlineCodesCheck> getOnlineCodesChecks() { return onlineCodesChecks; }

    @Nullable
    public List<OfflineCodesCheck> getOfflineCodesChecks() { return offlineCodesChecks; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        writeToParcel(parcel, local -> {
            local.writeTypedList(onlineCodesChecks);
            local.writeTypedList(offlineCodesChecks);
        });
    }

    public static Parcelable.Creator<CodesCheckResult> CREATOR = new Parcelable.Creator<>() {
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
