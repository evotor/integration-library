package ru.evotor.framework.inventory;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by a.kuznetsov on 30/04/2017.
 */

public class Measure implements Parcelable {
    private final String uuid;
    private final String name;
    private final int precision;

    public Measure(String uuid, String name, int precision) {
        this.uuid = uuid;
        this.name = name;
        this.precision = precision;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getPrecision() {
        return precision;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.name);
        dest.writeInt(this.precision);
    }

    protected Measure(Parcel in) {
        this.uuid = in.readString();
        this.name = in.readString();
        this.precision = in.readInt();
    }

    public static final Parcelable.Creator<Measure> CREATOR = new Parcelable.Creator<Measure>() {
        @Override
        public Measure createFromParcel(Parcel source) {
            return new Measure(source);
        }

        @Override
        public Measure[] newArray(int size) {
            return new Measure[size];
        }
    };
}
