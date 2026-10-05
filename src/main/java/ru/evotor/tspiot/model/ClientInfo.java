package ru.evotor.tspiot.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import ru.evotor.tspiot.Utils;

public class ClientInfo implements Parcelable {

    /** Версия ClientInfo */
    private static final int VERSION = 1;

    /** Версия протокола */
    @NotNull private final ApiVersion apiVersion;

    /** Наименование ПМСР (кассового ПО) */
    @NotNull private final String name;

    /** Версия ПМСР (кассового ПО) */
    @NotNull private final String version;

    /** Идентификатор ПМСР (кассового ПО) в реестре ГИС МТ */
    @NotNull private final String id;

    /** Идентификатор версии библиотеки */
    @Nullable private final String lastKey;

    /** Контрольная сумма/ЭЦП исполняемого файла ПМСР (кассового ПО) */
    @NotNull private final String token;

    /** ФИО пользователя ТС ПИоТ */
    @Nullable private final String userName;

    private ClientInfo(Parcel parcel) {
        int version = parcel.readInt();
        this.apiVersion = ApiVersion.createFromParcel(parcel);
        this.name = Objects.requireNonNull(parcel.readString());
        this.version = Objects.requireNonNull(parcel.readString());
        this.id = Objects.requireNonNull(parcel.readString());
        this.lastKey = parcel.readString();
        this.token = Objects.requireNonNull(parcel.readString());
        this.userName = parcel.readString();
    }

    public ClientInfo(
            @NotNull ApiVersion apiVersion,
            @NotNull String name,
            @NotNull String version,
            @NotNull String id,
            @Nullable String lastKey,
            @NotNull String token,
            @Nullable String userName
    ) {
        this.apiVersion = apiVersion;
        this.name = name;
        this.version = version;
        this.id = id;
        this.lastKey = lastKey;
        this.token = token;
        this.userName = userName;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        ApiVersion.writeToParcel(parcel, apiVersion);
        parcel.writeString(name);
        parcel.writeString(version);
        parcel.writeString(id);
        parcel.writeString(lastKey);
        parcel.writeString(token);
        parcel.writeString(userName);
    }

    @NotNull
    public ApiVersion getApiVersion() { return apiVersion; }

    @NotNull
    public String getName() { return name; }

    @NotNull
    public String getVersion() { return version; }

    @NotNull
    public String getId() { return id; }

    @Nullable
    public String getLastKey() { return lastKey; }

    @NotNull
    public String getToken() { return token; }

    @Nullable
    public String getUserName() { return userName; }

    public static Creator<ClientInfo> CREATOR = new Creator<ClientInfo>() {
        @Override
        public ClientInfo createFromParcel(Parcel parcel) {
            return new ClientInfo(parcel);
        }

        @Override
        public ClientInfo[] newArray(int size) {
            return new ClientInfo[size];
        }
    };

    public enum ApiVersion {
        V1, V2, V3;

        /** Версия ApiVersion */
        public static final int VERSION = 1;

        public static void writeToParcel(Parcel parcel, ApiVersion apiVersion) {
            parcel.writeInt(VERSION);
            parcel.writeString(apiVersion.name());
        }

        static ApiVersion createFromParcel(Parcel parcel) throws IllegalArgumentException {
            int version = parcel.readInt();
            String name = parcel.readString();

            return ApiVersion.valueOf(name);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiVersion: " + Utils.toString(apiVersion.name()) + "\n" +
                "Name: " + Utils.toString(name) + "\n" +
                "Version: " + Utils.toString(version) + "\n" +
                "Id: " + Utils.toString(id) + "\n" +
                "LastKey: " + Utils.toString(lastKey) + "\n" +
                "Token: " + Utils.toString(token) + "\n" +
                "UserName: " + Utils.toString(userName) + "\n";
    }
}
