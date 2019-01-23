package ru.evotor.framework.provider;

import ru.evotor.framework.core.DoNotUseThis;

@DoNotUseThis()
public final class QuantityContract {
    private QuantityContract() {

    }

    public interface Columns {
        String QUANTITY_UNSCALED_VALUE = "QUANTITY_UNSCALED_VALUE";
        String QUANTITY_SCALE = "QUANTITY_SCALE";
    }

    public interface UnitOfMeasurementColumns {
        String UNIT_OF_MEASUREMENT_VARIATION_ID = "UNIT_OF_MEASUREMENT_VARIATION_ID";
        String UNIT_OF_MEASUREMENT_TYPE = "UNIT_OF_MEASUREMENT_TYPE";
        String UNIT_OF_MEASUREMENT_NAME = "UNIT_OF_MEASUREMENT_NAME";
    }

    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_CUSTOM = 0;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_CONVENTIONAL_UNIT = 1;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_PIECE = 2;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_PACKAGING = 3;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_KIT = 4;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_KILOGRAM = 5;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_METER = 6;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_SQUARE_METER = 7;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_CUBIC_METER = 8;
    public static final int UNIT_OF_MEASUREMENT_VARIATION_ID_LITER = 9;
}
