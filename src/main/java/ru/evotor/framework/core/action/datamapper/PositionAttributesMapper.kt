package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.inventory.AttributeValue


object PositionAttributesMapper {

    @JvmStatic
    fun fromBundle(attributes: Bundle?): Map<String, AttributeValue>? {
        attributes?.let { bundle ->
            bundle.classLoader = AttributeValue::class.java.classLoader
            return HashMap<String, AttributeValue>().apply {
                attributes.keySet().forEach { key ->
                    val value = attributes.getParcelable(key) as AttributeValue?
                    value?.let {
                        this[key] = it
                    }
                }
            }
        } ?: return null
    }


    @JvmStatic
    fun toBundle(attributes: Map<String, AttributeValue>?): Bundle? {
        attributes?.let {
            return Bundle().apply {
                attributes.keys.forEach {
                    this.putParcelable(it, attributes[it])
                }
            }
        } ?: return null
    }

}
