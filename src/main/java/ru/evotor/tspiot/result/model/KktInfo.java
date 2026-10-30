package ru.evotor.tspiot.result.model;

import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;

public class KktInfo extends BaseTsPioTObject {

    /** Версия KktInfo */
    private final static int VERSION = 3;

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

    /** Предельное время обработки запроса проверки GTIN/EAN на стороне ТС ПИоТ */
    @Nullable private final Integer lmEANCheckTimeout;

    public KktInfo(
            String tspiotId,
            String kktSerial,
            String fnSerial,
            String kktInn,
            int codesCheckTimeOut,
            @Nullable LmChzInfo lmChzInfo,
            @Nullable Integer lmEANCheckTimeout
    ) {
        this.tspiotId = tspiotId;
        this.kktSerial = kktSerial;
        this.fnSerial = fnSerial;
        this.kktInn = kktInn;
        this.codesCheckTimeOut = codesCheckTimeOut;
        this.lmChzInfo = lmChzInfo;
        this.lmEANCheckTimeout = lmEANCheckTimeout;
    }

    private KktInfo(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 3);

        this.tspiotId = local.readString();
        this.kktSerial = local.readString();
        this.fnSerial = local.readString();
        this.kktInn = local.readString();
        this.codesCheckTimeOut = local.readInt();
        this.lmChzInfo = local.readTypedObject(LmChzInfo.CREATOR);
        this.lmEANCheckTimeout = version >= 2 ? Utils.readInteger(local) : null;

        endLocalParcelRead(local, version, 3);
    }

    public String getTspiotId() { return tspiotId; }

    public String getKktSerial() {return kktSerial; }

    public String getFnSerial() { return fnSerial; }

    public String getKktInn() { return kktInn; }

    public int getCodesCheckTimeOut() { return codesCheckTimeOut; }

    @Nullable
    public LmChzInfo getLmChzInfo() { return lmChzInfo; }

    @Nullable
    public Integer getLmEANCheckTimeout() { return lmEANCheckTimeout; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        writeToParcel(parcel, local -> {
            local.writeString(tspiotId);
            local.writeString(kktSerial);
            local.writeString(fnSerial);
            local.writeString(kktInn);
            local.writeInt(codesCheckTimeOut);
            local.writeTypedObject(lmChzInfo, flags);
            local.writeValue(lmEANCheckTimeout);
        });
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
                "LmEANCheckTimeout: " + Utils.toString(lmEANCheckTimeout) + "\n" +
                "LmChzInfo: " + Utils.toString(lmChzInfo);
    }
}
