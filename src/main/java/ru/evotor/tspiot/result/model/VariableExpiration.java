package ru.evotor.tspiot.result.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import ru.evotor.tspiot.Utils;

public class VariableExpiration implements Parcelable {

    /** Версия VariableExpiration */
    private final static int VERSION = 1;

    private final int degrees;

    private final String date;

    private VariableExpiration(Parcel parcel) {
        int version = parcel.readInt();
        this.degrees = parcel.readInt();
        this.date = parcel.readString();
    }

    public VariableExpiration(int degrees, String date) {
        this.degrees = degrees;
        this.date = date;
    }

    public int getDegrees() { return degrees; }

    public String getDate() { return date; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        parcel.writeInt(degrees);
        parcel.writeString(date);
    }

    public static final Creator<VariableExpiration> CREATOR = new Creator<VariableExpiration>() {
        @Override
        public VariableExpiration createFromParcel(Parcel parcel) {
            return new VariableExpiration(parcel);
        }

        @Override
        public VariableExpiration[] newArray(int i) {
            return new VariableExpiration[i];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Degrees: " + Utils.toString(degrees) + "\n" +
                "Date: " + Utils.toString(date);
    }
}
