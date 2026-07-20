package ru.evotor.tspiot.result.model.offline;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.VariableExpirations;
import ru.evotor.tspiot.result.model.base.BaseCodeCheck;

public class OfflineCodeCheck extends BaseCodeCheck implements Parcelable {

    /**
     * Версия OfflineCodeCheck
     */
    private final static int VERSION = 1;

    /**
     * Признак, определяющий, что GTIN из КИ находится в «сером списке GTIN»
     */
    @Nullable
    private final Boolean isGreyGtin;

    private OfflineCodeCheck(Parcel parcel) {
        super(parcel);

        Boolean isGreyGtin = null;
        if (parcel.dataAvail() > 0) {
            int version = parcel.readInt();
            if (version >= 1) {
                isGreyGtin = Utils.readBoolean(parcel);
            }
        }
        this.isGreyGtin = isGreyGtin;
    }

    public OfflineCodeCheck(
            String cis,
            @Nullable Boolean found,
            @Nullable Boolean valid,
            @Nullable String printView,
            @Nullable String gtin,
            @Nullable Boolean verified,
            @Nullable Boolean realizable,
            @Nullable Boolean utilized,
            @Nullable VariableExpirations variableExpirations,
            @Nullable Boolean isBlocked,
            @Nullable Boolean sold,
            @Nullable Integer mrp,
            @Nullable Integer smp
    ) {
        this(
                cis,
                found,
                valid,
                printView,
                gtin,
                verified,
                realizable,
                utilized,
                variableExpirations,
                isBlocked,
                sold,
                mrp,
                smp,
                null
        );
    }

    public OfflineCodeCheck(
            String cis,
            @Nullable Boolean found,
            @Nullable Boolean valid,
            @Nullable String printView,
            @Nullable String gtin,
            @Nullable Boolean verified,
            @Nullable Boolean realizable,
            @Nullable Boolean utilized,
            @Nullable VariableExpirations variableExpirations,
            @Nullable Boolean isBlocked,
            @Nullable Boolean sold,
            @Nullable Integer mrp,
            @Nullable Integer smp,
            @Nullable Boolean isGreyGtin
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
        this.isGreyGtin = isGreyGtin;
    }

    @Nullable
    public Boolean isGreyGtin() {
        return isGreyGtin;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.write(parcel, flags);
        parcel.writeInt(VERSION);
        parcel.writeValue(isGreyGtin);
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + "IsGreyGtin: " + Utils.toString(isGreyGtin) + "\n";
    }

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
