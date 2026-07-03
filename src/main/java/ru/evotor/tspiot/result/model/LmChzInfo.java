package ru.evotor.tspiot.result.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import ru.evotor.tspiot.Utils;

public class LmChzInfo implements Parcelable {

    private final static String VERSION_DEFAULT = "";
    private final static String STATUS_DEFAULT = "";
    private final static String TOKEN_DEFAULT = "";
    private final static String EXP_DATE_DEFAULT = "";
    private final static String IP_DEFAULT = "";
    private final static String LOGIN_DEFAULT = "";
    private final static String PASS_DEFAULT = "";

    /** Версия LmChzInfo */
    private final static int VERSION = 1;

    /** Версия СПО «Локальный модуль» «Честный ЗНАК» */
    @NonNull private final String version;

    /**
     * <ul>
     *  Возможные значения:
     *     <ul>
     *         <li>not_configured – не отконфигурирован;</li>
     *         <li>initialization – инициализация;</li>
     *         <li>ready – готов к работе;</li>
     *         <li>sync_error – ошибка синхронизации.</li>
     *     </ul>
     * </ul>
     */
    @NonNull private final String status;

    /**
     * Дата и время последней синхронизации по всем базам данных.
     * Если система не была инициализирована, то значение по умолчанию равно нулю. UnixTime в (мс)
     */
    private final long lastSync;

    /** Токен ЛМ ЧЗ. */
    @NonNull private final String token;

    /**
     * Дата и время истечения срока действия токена в формате ISO 8601
     * (например, 2025-03- 22T10:30:00Z). Время указано в UTC.
     */
    @NonNull private final String expDate;

    /** IP-адрес установки ЛМ ЧЗ */
    @NonNull private final String ip;

    /** Порт ЛМ ЧЗ */
    private final int port;

    /** Логин для авторизации в ЛМ ЧЗ */
    @NonNull private final String login;

    /** Пароль для авторизации в ЛМ ЧЗ */
    @NonNull private final String pass;

    private LmChzInfo(@NonNull Parcel parcel) {
        int classVersion = parcel.readInt();
        version = readParcelString(parcel, VERSION_DEFAULT);
        status = readParcelString(parcel, STATUS_DEFAULT);
        lastSync = parcel.readLong();
        token = readParcelString(parcel, TOKEN_DEFAULT);
        expDate = readParcelString(parcel, EXP_DATE_DEFAULT);
        ip = readParcelString(parcel, IP_DEFAULT);
        port = parcel.readInt();
        login = readParcelString(parcel, LOGIN_DEFAULT);
        pass = readParcelString(parcel, PASS_DEFAULT);
    }

    private @NonNull String readParcelString(@NonNull Parcel parcel, @NonNull String defaultString) {
        String parcelString = parcel.readString();
        return parcelString == null ? defaultString : parcelString;
    }

    public LmChzInfo(
            @NonNull
            String version,
            @NonNull
            String status,
            long lastSync,
            @NonNull
            String token,
            @NonNull
            String expDate,
            @NonNull
            String ip,
            int port,
            @NonNull
            String login,
            @NonNull
            String pass
    ) {
        this.version = version;
        this.status = status;
        this.lastSync = lastSync;
        this.token = token;
        this.expDate = expDate;
        this.ip = ip;
        this.port = port;
        this.login = login;
        this.pass = pass;
    }

    public int getPort() { return port; }

    public long getLastSync() { return lastSync; }

    @NonNull
    public String getExpDate() { return expDate; }

    @NonNull
    public String getIp() { return ip; }

    @NonNull
    public String getLogin() { return login; }

    @NonNull
    public String getPass() { return pass; }

    @NonNull
    public String getStatus() { return status; }

    @NonNull
    public String getToken() { return token; }

    @NonNull
    public String getVersion() { return version; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        parcel.writeString(version);
        parcel.writeString(status);
        parcel.writeLong(lastSync);
        parcel.writeString(token);
        parcel.writeString(expDate);
        parcel.writeString(ip);
        parcel.writeInt(port);
        parcel.writeString(login);
        parcel.writeString(pass);
    }

    @NonNull
    @Override
    public String toString() {
        return "Version: " + Utils.toString(version) + "\n" +
                "Status : " + Utils.toString(status) + "\n" +
                "Last sync: " + Utils.toString(lastSync) + "\n" +
                "Token: " + Utils.toString(token) + "\n" +
                "Exp date: " + Utils.toString(expDate) + "\n" +
                "Ip: " + Utils.toString(ip) + "\n" +
                "Port: " + Utils.toString(port) + "\n" +
                "Login: " + Utils.toString(login) + "\n" +
                "Pass: " + Utils.toString(pass);
    }

    public final static Creator<LmChzInfo> CREATOR = new Creator<>() {
        @Override
        public LmChzInfo createFromParcel(Parcel parcel) {
            return new LmChzInfo(parcel);
        }

        @Override
        public LmChzInfo[] newArray(int size) {
            return new LmChzInfo[size];
        }
    };
}
