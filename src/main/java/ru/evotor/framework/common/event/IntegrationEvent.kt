package ru.evotor.framework.common.event

import android.os.Bundle
import ru.evotor.IBundlable

abstract class IntegrationEvent internal constructor() : IBundlable {

    override fun toBundle() = Bundle().apply {
        putString(KEY_CLASS_NAME, javaClass.canonicalName)
    }

    abstract class Result internal constructor() : IBundlable

    companion object {
        private const val FROM_METHOD_NAME = "from"

        private const val KEY_CLASS_NAME = "CLASS_NAME"

        fun from(bundle: Bundle?) = bundle?.let {
            Class
                    .forName(KEY_CLASS_NAME)
                    .getMethod(FROM_METHOD_NAME, Bundle::class.java)
                    .invoke(it) as IntegrationEvent?
        }
    }
}