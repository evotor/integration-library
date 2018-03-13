package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.paysystem.PayResult;

public class ResultPayResult extends AbstractResult implements Parcelable {

    private final PayResult result;
    private final ErrorDescription errorDescription;

    public ResultPayResult(PayResult result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultPayResult(Parcel parcel) {
        result = new PayResult(parcel);
        errorDescription = new ErrorDescription(parcel);
    }

    public PayResult getResult() throws DeviceServiceException {
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
            new PayResult(null, null).writeToParcel(parcel);
        }
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultPayResult> CREATOR = new Creator<ResultPayResult>() {

        public ResultPayResult createFromParcel(Parcel in) {
            return new ResultPayResult(in);
        }

        public ResultPayResult[] newArray(int size) {
            return new ResultPayResult[size];
        }
    };

}
