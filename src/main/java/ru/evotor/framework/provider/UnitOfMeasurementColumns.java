package ru.evotor.framework.provider;

import ru.evotor.framework.core.DoNotUseThis;

@DoNotUseThis()
public interface UnitOfMeasurementColumns extends MultiVariationEntityColumns {
    String VARIATION_ID = "UNIT_OF_MEASUREMENT_VARIATION_ID";
    int VARIATION_ID_CUSTOM = 0;
    int VARIATION_ID_CONVENTIONAL_UNIT = 1;
    int VARIATION_ID_PIECE = 2;
    int VARIATION_ID_PACKAGING = 3;
    int VARIATION_ID_KIT = 4;
    int VARIATION_ID_KILOGRAM = 5;
    int VARIATION_ID_METER = 6;
    int VARIATION_ID_SQUARE_METER = 7;
    int VARIATION_ID_CUBIC_METER = 8;
    int VARIATION_ID_LITER = 9;
    String TYPE = "UNIT_OF_MEASUREMENT_TYPE";
    String NAME = "UNIT_OF_MEASUREMENT_NAME";
}
