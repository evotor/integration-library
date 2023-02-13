package ru.evotor.framework.users.service

import android.os.Bundle
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction

/**
 * Служба для работы со сменой пользователя
 */
abstract class ChangeUserService : IntegrationServiceV2() {

    override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_USER_CHANGED -> handleEvent(UserWasChangedEvent())
        else -> null
    }

    @RequiresIntentAction(ACTION_USER_CHANGED)
    open fun handleEvent(event: UserWasChangedEvent): Nothing? = null

    companion object {

        /**
         * Чтобы подписать службу на получение запроса, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.intent.event.users.USER_CHANGED`.
         */
        const val ACTION_USER_CHANGED = "ru.evotor.intent.event.users.USER_CHANGED"

        const val PERMISSION = "ru.evotor.permission.CHANGE_USER"
    }
}
