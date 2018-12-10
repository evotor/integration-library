package ru.evotor.framework.inventory

sealed class UnitOfMeasurement(val name: String, val accuracy: Int) {
    class KG : UnitOfMeasurement(KG.NAME, 1) {
        companion object {
            const val NAME = "кг"
        }
    }
}