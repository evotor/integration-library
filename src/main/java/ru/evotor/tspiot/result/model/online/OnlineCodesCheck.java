package ru.evotor.tspiot.result.model.online;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.base.BaseCodesCheck;

public class OnlineCodesCheck extends BaseCodesCheck implements Parcelable {

    /** Версия OnlineCodesCheck */
    private final static int VERSION = 1;

    /** Текстовое описание результата выполнения метода */
    @Nullable
    private final String description;

    /** Результат проверки марок*/
    private final List<OnlineCodeCheck> codes;

    private OnlineCodesCheck(Parcel parcel) {
        super(parcel);

        int version = parcel.readInt();
        this.description = parcel.readString();
        this.codes = parcel.createTypedArrayList(OnlineCodeCheck.CREATOR);
    }

    public OnlineCodesCheck(
            int code,
            @Nullable String description,
            List<OnlineCodeCheck> codes,
            String reqId,
            long reqTimestamp
    ) {
        super(code, reqId, reqTimestamp);

        this.description = description;
        this.codes = codes;
    }

    @Nullable
    public String getDescription() { return description; }

    public List<OnlineCodeCheck> getCodes() { return codes; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.write(parcel, flags);

        parcel.writeInt(VERSION);
        parcel.writeString(description);
        parcel.writeTypedList(codes);
    }

    @NonNull
    @Override
    public String toString() {
        Utils.ListToString<OnlineCodeCheck> callback = code -> "OnlineCodeCheck: " + code + "\n";

        return super.toString() +
                "Description: " + Utils.toString(description) + "\n" +
                "OnlineCodeChecks: " + Utils.toString(codes, callback) + "\n";
    }

    public final static Creator<OnlineCodesCheck> CREATOR = new Creator<>() {
        @Override
        public OnlineCodesCheck createFromParcel(Parcel parcel) {
            return new OnlineCodesCheck(parcel);
        }

        @Override
        public OnlineCodesCheck[] newArray(int size) {
            return new OnlineCodesCheck[size];
        }
    };
}
