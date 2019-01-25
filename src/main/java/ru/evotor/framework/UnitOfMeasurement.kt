package ru.evotor.framework

import ru.evotor.query.FilterBuilder

sealed class UnitOfMeasurement(val name: String, val type: Type) {
    class ConventionalUnit : UnitOfMeasurement(NAME, Type.ITEM_COUNTING_UNIT) {
        companion object {
            const val NAME = "ед"
        }
    }

    class Piece : UnitOfMeasurement(NAME, Type.ITEM_COUNTING_UNIT) {
        companion object {
            const val NAME = "шт"
        }
    }

    class Packaging : UnitOfMeasurement(NAME, Type.ITEM_COUNTING_UNIT) {
        companion object {
            const val NAME = "упак"
        }
    }

    class Kit : UnitOfMeasurement(NAME, Type.ITEM_COUNTING_UNIT) {
        companion object {
            const val NAME = "компл"
        }
    }

    class Kilogram : UnitOfMeasurement(NAME, Type.MASS_UNIT) {
        companion object {
            const val NAME = "кг"
        }
    }

    class Meter : UnitOfMeasurement(NAME, Type.DISTANCE_UNIT) {
        companion object {
            const val NAME = "м"
        }
    }

    class SquareMeter : UnitOfMeasurement(NAME, Type.AREA_UNIT) {
        companion object {
            const val NAME = "м2"
        }
    }

    class CubicMeter : UnitOfMeasurement(NAME, Type.VOLUME_UNIT) {
        companion object {
            const val NAME = "м3"
        }
    }

    class Liter : UnitOfMeasurement(NAME, Type.VOLUME_UNIT) {
        companion object {
            const val NAME = "л"
        }
    }

    class Ruble : UnitOfMeasurement(NAME, Type.MONEY_UNIT) {
        companion object {
            const val NAME = "₽"
        }
    }

    class Custom(name: String, type: Type = Type.ITEM_COUNTING_UNIT) : UnitOfMeasurement(name, type)

    enum class Type {
        ITEM_COUNTING_UNIT,
        MASS_UNIT,
        DISTANCE_UNIT,
        AREA_UNIT,
        VOLUME_UNIT,
        MONEY_UNIT
    }

    class FullFilter<Q, S : FilterBuilder.SortOrder<S>, R> internal constructor(
            columnName: String,
            columnType: String
    ) : FilterBuilder.Inner<Q, S, R>() {
        val name = addFieldFilter<String>(columnName)
        val type = addFieldFilter<Type>(columnType)

        class SortOrder<S : FilterBuilder.SortOrder<S>> internal constructor(
                columnName: String,
                columnType: String
        ) : FilterBuilder.Inner.SortOrder<S>() {
            val name = addFieldSorter(columnName)
            val type = addFieldSorter(columnType)
        }
    }

    class NameOnlyFilter<Q, S : FilterBuilder.SortOrder<S>, R> internal constructor(
            columnName: String
    ) : FilterBuilder.Inner<Q, S, R>() {
        val name = addFieldFilter<String>(columnName)

        class SortOrder<S : FilterBuilder.SortOrder<S>> internal constructor(
                columnName: String
        ) : FilterBuilder.Inner.SortOrder<S>() {
            val name = addFieldSorter(columnName)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UnitOfMeasurement) return false

        if (type != other.type) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}