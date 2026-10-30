package ru.evotor.tspiot.result.model;

import android.os.Parcel;

import androidx.annotation.NonNull;

import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;

public class VariableExpiration extends BaseTsPioTObject {

    /** Версия VariableExpiration */
    private final static int VERSION = 2;

    private final int degrees;

    /** Информация о сроках годности в зависимости от условий хранения
     * возвращается из карточки товара в НКМТ в формате: "индекс>": <дата>.
     * Формат даты: yyyy-MMdd HH:mm:ss.SSSZ.
     * Параметр возвращается только для товарной группы «Молочная продукция» */
    private final String date;

    private VariableExpiration(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 2);

        this.degrees = local.readInt();
        this.date = local.readString();

        endLocalParcelRead(local, version, 2);
    }

    public VariableExpiration(int degrees, String date) {
        this.degrees = degrees;
        this.date = date;
    }

    public int getDegrees() { return degrees; }

    public String getDate() { return date; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        writeToParcel(parcel, local -> {
            local.writeInt(degrees);
            local.writeString(date);
        });
    }

    public static final Creator<VariableExpiration> CREATOR = new Creator<VariableExpiration>() {
        @Override
        public VariableExpiration createFromParcel(Parcel parcel) {
            return new VariableExpiration(parcel);
        }

        @Override
        public VariableExpiration[] newArray(int i) {
            return new VariableExpiration[i];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Degrees: " + Utils.toString(degrees) + "\n" +
                "Date: " + Utils.toString(date);
    }
}
