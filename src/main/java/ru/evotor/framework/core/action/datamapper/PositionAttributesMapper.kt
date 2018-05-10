package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.domain.AttributeValue


object PositionAttributesMapper {

    @JvmStatic
    fun fromBundle(attributes: Bundle?): Map<String, AttributeValue>? {
        attributes?.let {
            return HashMap<String, AttributeValue>().apply {
                attributes.keySet().forEach { this[it] = attributes.getParcelable(it) }
            }
        } ?: return null
    }


    @JvmStatic
    fun toBundle(attributes: Map<String, AttributeValue>?): Bundle? {
        attributes?.let {
            return Bundle().apply {
                for (key in attributes.keys) {
                    this.putParcelable(key, attributes[key])
                }
            }
        } ?: return null
    }

}
