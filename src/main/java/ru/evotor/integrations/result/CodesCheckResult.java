package ru.evotor.integrations.result;

import android.os.Parcel;
import android.os.Parcelable;

public class CodesCheckResult implements Parcelable {

    private final CodesCheck[] codesChecks;

    private CodesCheckResult(Parcel parcel) {
        this.codesChecks = parcel.createTypedArray(CodesCheck.CREATOR);
    }

    public CodesCheckResult(CodesCheck[] codesChecks) {
        this.codesChecks = codesChecks;
    }

    public CodesCheck[] getCodesChecks() { return codesChecks; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedArray(codesChecks, flags);
    }

    public static Creator<CodesCheckResult> CREATOR = new Creator<>() {
        @Override
        public CodesCheckResult createFromParcel(Parcel parcel) {
            return new CodesCheckResult(parcel);
        }

        @Override
        public CodesCheckResult[] newArray(int i) {
            return new CodesCheckResult[0];
        }
    };
}
