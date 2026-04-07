package ru.evotor.tspiot.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class KktInfo implements Parcelable {

    /** Версия KktInfo */
    private final static int VERSION = 1;

    /** Идентификатор ТС ПИоТ */
    private final String tspiotId;

    /** ЗН ККТ */
    private final String kktSerial;

    /** ЗН ФН */
    private final String fnSerial;

    /** ИНН ККТ */
    private final String kktInn;

    /** Время проверки кода в ТС ПИоТ (В миллисекундах) */
    private final int codesCheckTimeOut;

    public KktInfo(String tspiotId, String kktSerial, String fnSerial, String kktInn, int codesCheckTimeOut) {
        this.tspiotId = tspiotId;
        this.kktSerial = kktSerial;
        this.fnSerial = fnSerial;
        this.kktInn = kktInn;
        this.codesCheckTimeOut = codesCheckTimeOut;
    }

    private KktInfo(Parcel parcel) {
        int version = parcel.readInt();
        this.tspiotId = parcel.readString();
        this.kktSerial = parcel.readString();
        this.fnSerial = parcel.readString();
        this.kktInn = parcel.readString();
        this.codesCheckTimeOut = parcel.readInt();
    }

    public String getTspiotId() { return tspiotId; }

    public String getKktSerial() {return kktSerial; }

    public String getFnSerial() { return fnSerial; }

    public String getKktInn() { return kktInn; }

    public int getCodesCheckTimeOut() { return codesCheckTimeOut; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        parcel.writeString(tspiotId);
        parcel.writeString(kktSerial);
        parcel.writeString(fnSerial);
        parcel.writeString(kktInn);
        parcel.writeInt(codesCheckTimeOut);
    }

    public static final Creator<KktInfo> CREATOR = new Creator<>() {

        public KktInfo createFromParcel(Parcel in) {
            return new KktInfo(in);
        }

        public KktInfo[] newArray(int size) {
            return new KktInfo[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "TsPioTId: " + tspiotId + "\n" +
                "KktSerial: " + kktSerial + "\n" +
                "FnSerial: " + fnSerial + "\n" +
                "KktInn: " + kktInn + "\n" +
                "CodesCheckTimeout: " + codesCheckTimeOut + "\n";
    }
}
