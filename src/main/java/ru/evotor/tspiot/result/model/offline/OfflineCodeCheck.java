package ru.evotor.tspiot.result.model.offline;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import ru.evotor.tspiot.result.model.VariableExpirations;
import ru.evotor.tspiot.result.model.base.BaseCodeCheck;

public class OfflineCodeCheck extends BaseCodeCheck implements Parcelable {

    private OfflineCodeCheck(Parcel parcel) {
        super(parcel);
    }

    public OfflineCodeCheck(
            String cis,
            boolean found,
            boolean valid,
            String printView,
            String gtin,
            boolean verified,
            boolean realizable,
            boolean utilized,
            @Nullable VariableExpirations variableExpirations,
            @Nullable Boolean isBlocked,
            boolean sold,
            @Nullable Integer mrp,
            @Nullable Integer smp
    ) {
        super(
                cis,
                found,
                valid,
                printView,
                gtin,
                null,
                verified,
                realizable,
                utilized,
                variableExpirations,
                isBlocked,
                null,
                sold,
                mrp,
                smp
        );
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) { super.write(parcel, flags); }

    public final static Creator<OfflineCodeCheck> CREATOR = new Creator<OfflineCodeCheck>() {
        @Override
        public OfflineCodeCheck createFromParcel(Parcel parcel) {
            return new OfflineCodeCheck(parcel);
        }

        @Override
        public OfflineCodeCheck[] newArray(int size) {
            return new OfflineCodeCheck[size];
        }
    };
}
