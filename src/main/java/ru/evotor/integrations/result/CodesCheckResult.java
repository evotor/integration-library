package ru.evotor.integrations.result;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CodesCheckResult implements Parcelable {

    private final List<CodesCheck> codesChecks;

    private CodesCheckResult(Parcel parcel) {
        this.codesChecks = parcel.createTypedArrayList(CodesCheck.CREATOR);
    }

    public CodesCheckResult(List<CodesCheck> codesChecks) {
        this.codesChecks = codesChecks;
    }

    public List<CodesCheck> getCodesChecks() { return codesChecks; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(codesChecks);
    }

    public static Creator<CodesCheckResult> CREATOR = new Creator<>() {
        @Override
        public CodesCheckResult createFromParcel(Parcel parcel) {
            return new CodesCheckResult(parcel);
        }

        @Override
        public CodesCheckResult[] newArray(int i) {
            return new CodesCheckResult[i];
        }
    };
}
