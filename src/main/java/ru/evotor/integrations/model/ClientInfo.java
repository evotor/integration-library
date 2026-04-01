package ru.evotor.integrations.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Информация об интеграционном приложении (ПСМР, стороннее кассовое ПО)
 */
public class ClientInfo implements Parcelable {

    /** Версия ClientInfo */
    private final static int VERSION = 1;

    /** Наименование приложения */
    public final String name;

    /** Версия приложения */
    public final String version;

    /** Контрольная сумма/ЭЦП исполняемого файла ПМСР (кассового ПО) */
    public final String token;

    private ClientInfo(Parcel parcel) {
        int version = parcel.readInt();
        this.name = parcel.readString();
        this.version = parcel.readString();
        this.token = parcel.readString();
    }

    public ClientInfo(String name, String version, String token) {
        this.name = name;
        this.version = version;
        this.token = token;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeString(name);
        parcel.writeString(version);
        parcel.writeString(token);
    }

    public static final Creator<ClientInfo> CREATOR = new Creator<>() {
        @Override
        public ClientInfo createFromParcel(Parcel parcel) {
            return new ClientInfo(parcel);
        }

        @Override
        public ClientInfo[] newArray(int i) {
            return new ClientInfo[i];
        }
    };
}
