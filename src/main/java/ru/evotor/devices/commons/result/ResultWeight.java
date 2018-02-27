package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.scales.Weight;

public class ResultWeight extends AbstractResult implements Parcelable {

    private final Weight result;
    private final ErrorDescription errorDescription;

    public ResultWeight(Weight result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultWeight(Parcel parcel) {
        result = new Weight(parcel);
        errorDescription = new ErrorDescription(parcel);
    }

    public Weight getResult() throws DeviceServiceException {
        this.unpackException();
        return result;
    }

    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (result != null) {
            result.writeToParcel(parcel);
        } else {
            new Weight(null, false, false).writeToParcel(parcel);
        }
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultWeight> CREATOR = new Creator<ResultWeight>() {

        public ResultWeight createFromParcel(Parcel in) {
            return new ResultWeight(in);
        }

        public ResultWeight[] newArray(int size) {
            return new ResultWeight[size];
        }
    };

}
