package ru.evotor.devices.commons.service;

import android.os.Parcel;

import ru.evotor.devices.commons.result.AbstractInnerParcelable;

public class DeviceInfo extends AbstractInnerParcelable {

    private final String vendor;
    private final String model;
    private final String connectionDescription;
    private final String userDescription;
    private final String additionalInfoJson;

    public DeviceInfo(String vendor, String model, String connectionDescription, String userDescription, String additionalInfoJson) {
        this.vendor = vendor;
        this.model = model;
        this.connectionDescription = connectionDescription;
        this.userDescription = userDescription;
        this.additionalInfoJson = additionalInfoJson;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModel() {
        return model;
    }

    public String getConnectionDescription() {
        return connectionDescription;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public String getAdditionalInfoJson() {
        return additionalInfoJson;
    }

    public void writeToParcel(Parcel parcel) {
        parcel.writeString(vendor);
        parcel.writeString(model);
        parcel.writeString(connectionDescription);
        parcel.writeString(userDescription);
        parcel.writeString(additionalInfoJson);
    }

    public DeviceInfo(Parcel parcel) {
        vendor = parcel.readString();
        model = parcel.readString();
        connectionDescription = parcel.readString();
        userDescription = parcel.readString();
        additionalInfoJson = parcel.readString();
    }

}
