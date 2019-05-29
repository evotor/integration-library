package ru.evotor.framework.common.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.IntegrationLibraryMappingException

abstract class IntegrationEvent internal constructor() : IBundlable {

    override fun toBundle(): Bundle {
        val className = javaClass.canonicalName
        return Bundle().apply {
            putString(KEY_CLASS_NAME, className)
        }
    }

    abstract class Result internal constructor() : IBundlable

    companion object {
        private const val FROM_METHOD_NAME = "from"

        private const val KEY_CLASS_NAME = "CLASS_NAME"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            val className = it.getString(KEY_CLASS_NAME)
                    ?: throw IntegrationLibraryMappingException(IntegrationEvent::class.java.name)
            val eventClass = Class.forName(className)
            val methodFrom = eventClass.getMethod(FROM_METHOD_NAME, Bundle::class.java)
            methodFrom.invoke(null, it) as IntegrationEvent?
        }
    }
}