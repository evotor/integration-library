package ru.evotor.tspiot.result.model.base;

import android.os.Parcel;

import androidx.annotation.NonNull;

import ru.evotor.tspiot.Utils;

public abstract class BaseCodesCheck {

    /** Версия BaseCodesCheck */
    private final static int VERSION = 1;

    protected final int code;

    /** Уникальный идентификатор запроса */
    protected final String reqId;

    /** Дата и время формирования запроса (в UTC) */
    protected final long reqTimestamp;

    protected BaseCodesCheck(Parcel parcel) {
        int version = parcel.readInt();
        this.code = parcel.readInt();
        this.reqId = parcel.readString();
        this.reqTimestamp = parcel.readLong();
    }

    public BaseCodesCheck(
            int code,
            String reqId,
            long reqTimestamp
    ) {
        this.code = code;
        this.reqId = reqId;
        this.reqTimestamp = reqTimestamp;
    }

    protected final void write(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeInt(code);
        parcel.writeString(reqId);
        parcel.writeLong(reqTimestamp);
    }

    public int getCode() { return code; }

    public String getReqId() { return reqId; }

    public long getReqTimestamp() { return reqTimestamp; }

    @NonNull
    @Override
    public String toString() {
        return "Code: " + Utils.toString(code) + "\n" +
                "ReqId: " + Utils.toString(reqId) + "\n" +
                "ReqTimestamp: " + Utils.toString(reqTimestamp) + "\n";
    }
}
