package ru.evotor.framework.users.service


import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

class UserWasChangedEvent : IntegrationEvent() {
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
        fun from() = UserWasChangedEvent()
    }
}
