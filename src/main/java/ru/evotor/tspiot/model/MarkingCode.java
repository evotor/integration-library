package ru.evotor.tspiot.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import ru.evotor.tspiot.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MarkingCode implements Parcelable {

    /** Версия MarkingCode */
    private static final int VERSION = 1;

    /** Код маркировки */
    @NotNull
    private final String cis;

    /** Опциональный параметр идентификатора товарной группы */
    @Nullable private final Integer productType;

    public MarkingCode(@NotNull String cis, @Nullable Integer productType) {
        this.cis = cis;
        this.productType = productType;
    }

    private MarkingCode(Parcel parcel) {
        int version = parcel.readInt();
        this.cis = Objects.requireNonNull(parcel.readString());
        this.productType = Utils.readInteger(parcel);
    }

    @NotNull
    public String getCis() { return cis; }

    @Nullable
    public Integer getProductType() { return productType; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        parcel.writeString(cis);
        parcel.writeValue(productType);
    }

    public static Creator<MarkingCode> CREATOR = new Creator<>() {
        @Override
        public MarkingCode createFromParcel(Parcel parcel) {
            return new MarkingCode(parcel);
        }

        @Override
        public MarkingCode[] newArray(int i) {
            return new MarkingCode[i];
        }
    };
}
