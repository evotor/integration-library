package ru.evotor.tspiot.result.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.evotor.tspiot.Utils;

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

    @Nullable private final LmChzInfo lmChzInfo;

    public KktInfo(
            String tspiotId,
            String kktSerial,
            String fnSerial,
            String kktInn,
            int codesCheckTimeOut,
            @Nullable LmChzInfo lmChzInfo
    ) {
        this.tspiotId = tspiotId;
        this.kktSerial = kktSerial;
        this.fnSerial = fnSerial;
        this.kktInn = kktInn;
        this.codesCheckTimeOut = codesCheckTimeOut;
        this.lmChzInfo = lmChzInfo;
    }

    private KktInfo(Parcel parcel) {
        int version = parcel.readInt();
        this.tspiotId = parcel.readString();
        this.kktSerial = parcel.readString();
        this.fnSerial = parcel.readString();
        this.kktInn = parcel.readString();
        this.codesCheckTimeOut = parcel.readInt();
        this.lmChzInfo = parcel.readTypedObject(LmChzInfo.CREATOR);
    }

    public String getTspiotId() { return tspiotId; }

    public String getKktSerial() {return kktSerial; }

    public String getFnSerial() { return fnSerial; }

    public String getKktInn() { return kktInn; }

    public int getCodesCheckTimeOut() { return codesCheckTimeOut; }

    @Nullable
    public LmChzInfo getLmChzInfo() { return lmChzInfo; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeString(tspiotId);
        parcel.writeString(kktSerial);
        parcel.writeString(fnSerial);
        parcel.writeString(kktInn);
        parcel.writeInt(codesCheckTimeOut);
        parcel.writeTypedObject(lmChzInfo, flags);
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
        return "TsPioTId: " + Utils.toString(tspiotId) + "\n" +
                "KktSerial: " + Utils.toString(kktSerial) + "\n" +
                "FnSerial: " + Utils.toString(fnSerial) + "\n" +
                "KktInn: " + Utils.toString(kktInn) + "\n" +
                "CodesCheckTimeout: " + Utils.toString(codesCheckTimeOut) + "\n" +
                "LmChzInfo: " + Utils.toString(lmChzInfo);
    }
}
