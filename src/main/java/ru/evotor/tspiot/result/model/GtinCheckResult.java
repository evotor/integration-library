package ru.evotor.tspiot.result.model;

import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;
import java.util.Objects;

public class GtinCheckResult extends BaseTsPioTObject {

    /** Версия GtinCheckResult */
    private final static int VERSION = 2;

    /** GTIN/EAN, считанный из линейного штрихкода. */
    @NonNull private final String gtin;

    /** true — продажу по GTIN/EAN необходимо заблокировать, требуется DataMatrix; false — продажа разрешена. */
    private final boolean blocked;

    /** Текст для отображения оператору или логирования. */
    @Nullable private final String message;

    private GtinCheckResult(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 2);

        this.gtin = Objects.requireNonNull(local.readString());
        this.blocked = local.readInt() == 1;
        this.message = local.readString();

        endLocalParcelRead(local, version, 2);
    }

    public GtinCheckResult(@NonNull String gtin, boolean blocked, @Nullable String message) {
        this.gtin = gtin;
        this.blocked = blocked;
        this.message = message;
    }

    @NonNull
    public String getGtin() { return gtin; }

    public boolean isBlocked() { return blocked; }

    @Nullable
    public String getMessage() { return message; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        writeToParcel(parcel, local -> {
            local.writeString(gtin);
            local.writeInt(blocked ? 1 : 0);
            local.writeString(message);
        });
    }

    public static Creator<GtinCheckResult> CREATOR = new Creator<>() {
        @Override
        public GtinCheckResult createFromParcel(Parcel parcel) {
            return new GtinCheckResult(parcel);
        }

        @Override
        public GtinCheckResult[] newArray(int size) {
            return new GtinCheckResult[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Gtin: " + Utils.toString(gtin) + "\n" +
                "IsBlocked: " + blocked + "\n" +
                "Message: " + Utils.toString(message) + "\n";
    }
}
