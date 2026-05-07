package ru.evotor.tspiot.result.model.offline;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.base.BaseCodesCheck;

public class OfflineCodesCheck extends BaseCodesCheck implements Parcelable {

    /** Версия OfflineCodesCheck */
    private final static int VERSION = 1;

    /** Версия базы "чёрного списка", на которой выполнялась проверка КИ */
    private final String version;

    /** Идентификатор экземпляра ПО ЛМ ЧЗ */
    private final String inst;

    /** Результат проверки марок*/
    private final List<OfflineCodeCheck> codes;

    private OfflineCodesCheck(Parcel parcel) {
        super(parcel);

        int classVersion = parcel.readInt();
        this.version = parcel.readString();
        this.inst = parcel.readString();
        this.codes = parcel.createTypedArrayList(OfflineCodeCheck.CREATOR);
    }

    public OfflineCodesCheck(
            int code,
            String reqId,
            long reqTimestamp,
            String version,
            String inst,
            List<OfflineCodeCheck> codes
    ) {
        super(code, reqId, reqTimestamp);

        this.version = version;
        this.inst = inst;
        this.codes = codes;
    }

    public String getInst() { return inst; }

    public String getVersion() { return version; }

    public List<OfflineCodeCheck> getCodes() { return codes; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.write(parcel, flags);

        parcel.writeInt(VERSION);
        parcel.writeString(version);
        parcel.writeString(inst);
        parcel.writeTypedList(codes);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());

        builder.append("Version: ").append(Utils.toString(version)).append("\n");
        builder.append("Inst: ").append(Utils.toString(inst)).append("\n");
        codes.forEach(offlineCodeCheck -> {
            builder.append("OfflineCodeCheck: ").append(offlineCodeCheck).append("\n");
        });

        return builder.toString();
    }

    public final static Creator<OfflineCodesCheck> CREATOR = new Creator<>() {
        @Override
        public OfflineCodesCheck createFromParcel(Parcel parcel) {
            return new OfflineCodesCheck(parcel);
        }

        @Override
        public OfflineCodesCheck[] newArray(int size) {
            return new OfflineCodesCheck[size];
        }
    };
}
