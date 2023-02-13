package ru.evotor.framework.product

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

class CreateProductEvent : IntegrationEvent() {
    override fun toBundle() = Bundle()

    class Result() : IntegrationEvent.Result() {

        override fun toBundle() = Bundle()

        companion object {

            @JvmStatic
            fun from(bundle: Bundle?) = bundle?.let {
                Result()
            }
        }
    }

    companion object {

        @JvmStatic
        fun from() = CreateProductEvent()
    }
}
