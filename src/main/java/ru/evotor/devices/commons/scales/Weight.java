package ru.evotor.devices.commons.scales;

import android.os.Parcel;

import java.math.BigDecimal;

import ru.evotor.devices.commons.result.AbstractInnerParcelable;

public class Weight extends AbstractInnerParcelable {

    // вес, возвращённый весами
    private final BigDecimal weightInGrams;
    // поддерживали ли весы флаг стабильности при последнем взвешивании
    private final boolean supportStable;
    // было ли последнее взвешивание стабильным
    private final boolean stable;

    public Weight(BigDecimal weightInGrams, boolean supportStable, boolean stable) {
        this.weightInGrams = weightInGrams;
        this.supportStable = supportStable;
        this.stable = stable;
    }

    public BigDecimal getWeightInGrams() {
        return weightInGrams;
    }

    public boolean isSupportStable() {
        return supportStable;
    }

    public boolean isStable() {
        return stable;
    }

    public void writeToParcel(Parcel parcel) {
        parcel.writeSerializable(weightInGrams);
        parcel.writeInt(supportStable ? 1 : 0);
        parcel.writeInt(stable ? 1 : 0);
    }

    public Weight(Parcel parcel) {
        weightInGrams = (BigDecimal) parcel.readSerializable();
        supportStable = parcel.readInt() == 1;
        stable = parcel.readInt() == 1;
    }

}
