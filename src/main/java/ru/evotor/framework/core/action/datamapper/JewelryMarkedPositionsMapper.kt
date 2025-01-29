package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import java.math.BigDecimal


object JewelryMarkedPositionsMapper {

    @JvmStatic
    fun fromBundle(positions: Bundle?): Map<String, BigDecimal>? {
        positions?.let { bundle ->
            bundle.classLoader = BigDecimal::class.java.classLoader
            return HashMap<String, BigDecimal>().apply {
                positions.keySet().forEach { key ->
                    val value = positions.getSerializable(key) as BigDecimal?
                    value?.let {
                        this[key] = it
                    }
                }
            }
        } ?: return null
    }


    @JvmStatic
    fun toBundle(positions: Map<String, BigDecimal>?): Bundle? {
        positions?.let {
            return Bundle().apply {
                positions.keys.forEach {
                    this.putSerializable(it, positions[it])
                }
            }
        } ?: return null
    }

}
