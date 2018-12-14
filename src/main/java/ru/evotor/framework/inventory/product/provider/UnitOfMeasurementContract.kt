package ru.evotor.framework.inventory.product.provider

object UnitOfMeasurementContract {
    const val COLUMN_VARIATION_ID = "UNIT_OF_MEASUREMENT_VARIATION_ID"
    const val COLUMN_TYPE = "UNIT_OF_MEASUREMENT_TYPE"
    const val COLUMN_NAME = "UNIT_OF_MEASUREMENT_NAME"
    const val COLUMN_PRECISION = "UNIT_OF_MEASUREMENT_PRECISION"

    const val VARIATION_ID_CUSTOM = 0
    const val VARIATION_ID_CONVENTIONAL_UNIT = 1
    const val VARIATION_ID_PIECE = 2
    const val VARIATION_ID_PACKAGING = 3
    const val VARIATION_ID_KIT = 4
    const val VARIATION_ID_KILOGRAM = 5
    const val VARIATION_ID_METER = 6
    const val VARIATION_ID_SQUARE_METER = 7
    const val VARIATION_ID_CUBIC_METER = 8
    const val VARIATION_ID_LITER = 9
}