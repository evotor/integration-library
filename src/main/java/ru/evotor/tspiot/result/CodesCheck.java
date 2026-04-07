package ru.evotor.tspiot.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class CodesCheck implements Parcelable {

    /** Версия CodesCheck */
    private final static int VERSION = 1;

    private final int code;

    /** Текстовое описание результата выполнения метода */
    @Nullable private final String description;

    /** Результат проверки марок*/
    private final List<CodeCheck> codes;

    /** Уникальный идентификатор запроса */
    private final String reqId;

    /** Дата и время формирования запроса (в UTC) */
    private final long reqTimestamp;

    /** Признак проверки марки в оффлайн режиме. */
    private final boolean isCheckedOffline;

    /** Версия ЛМ ЧЗ */
    @Nullable private final String version;

    @Nullable private final String inst;

    private CodesCheck(Parcel parcel) {
        int version = parcel.readInt();
        this.code = parcel.readInt();
        this.description = parcel.readString();
        this.codes = parcel.createTypedArrayList(CodeCheck.CREATOR);
        this.reqId = parcel.readString();
        this.reqTimestamp = parcel.readLong();
        this.isCheckedOffline = parcel.readInt() == 1;
        this.version = parcel.readString();
        this.inst = parcel.readString();
    }

    public CodesCheck(
        int code,
        @Nullable String description,
        List<CodeCheck> codes,
        String reqId,
        long reqTimestamp,
        boolean isCheckedOffline,
        @Nullable String version,
        @Nullable String inst
    ) {
        this.code = code;
        this.description = description;
        this.codes = codes;
        this.reqId = reqId;
        this.reqTimestamp = reqTimestamp;
        this.isCheckedOffline = isCheckedOffline;
        this.version = version;
        this.inst = inst;
    }

    public int getCode() { return code; }

    @Nullable
    public String getDescription() { return description; }

    public List<CodeCheck> getCodes() { return codes; }

    public String getReqId() { return reqId; }

    public long getReqTimestamp() { return reqTimestamp; }

    public boolean isCheckedOffline() { return isCheckedOffline; }

    @Nullable
    public String getVersion() { return version; }

    @Nullable
    public String getInst() { return inst; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        parcel.writeInt(code);
        parcel.writeString(description);
        parcel.writeTypedList(codes);
        parcel.writeString(reqId);
        parcel.writeLong(reqTimestamp);
        parcel.writeInt(isCheckedOffline ? 1 : 0);
        parcel.writeString(version);
        parcel.writeString(inst);
    }

    public static final Creator<CodesCheck> CREATOR = new Creator<>() {
        @Override
        public CodesCheck createFromParcel(Parcel parcel) {
            return new CodesCheck(parcel);
        }

        @Override
        public CodesCheck[] newArray(int i) {
            return new CodesCheck[i];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Code: " + code + "\n" +
                "Description: " + description + "\n" +
                "Codes: " + (codes != null ? codes.toString() : "[]") + "\n" +
                "ReqId: " + reqId + "\n" +
                "ReqTimestamp: " + reqTimestamp + "\n" +
                "IsCheckedOffline: " + isCheckedOffline + "\n" +
                "Version: " + version + "\n" +
                "Inst: " + inst + "\n";
    }
}
