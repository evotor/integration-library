package ru.evotor.framework.provider;

import ru.evotor.framework.core.DoNotUseThis;

@DoNotUseThis()
public final class UnitOfMeasurementContract {
    private UnitOfMeasurementContract() {

    }

    public static final int VARIATION_ID_CUSTOM = 0;
    public static final int VARIATION_ID_CONVENTIONAL_UNIT = 1;
    public static final int VARIATION_ID_PIECE = 2;
    public static final int VARIATION_ID_PACKAGING = 3;
    public static final int VARIATION_ID_KIT = 4;
    public static final int VARIATION_ID_KILOGRAM = 5;
    public static final int VARIATION_ID_METER = 6;
    public static final int VARIATION_ID_SQUARE_METER = 7;
    public static final int VARIATION_ID_CUBIC_METER = 8;
    public static final int VARIATION_ID_LITER = 9;
}