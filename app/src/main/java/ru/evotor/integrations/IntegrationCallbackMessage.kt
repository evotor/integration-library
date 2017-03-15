package ru.evotor.integrations

import android.os.Handler
import android.os.Message

/**
 * Created by nixan on 09.03.17.
 */

sealed class IntegrationCallbackMessage(val resultCode: Int) {

    class Success : IntegrationCallbackMessage(100)

    class Error : IntegrationCallbackMessage(200)

    class InProgress : IntegrationCallbackMessage(300)

    companion object {

        fun fromMessage(message: Message): IntegrationCallbackMessage {
            return when (message.what) {
                100 -> Success()
                200 -> Error()
                300 -> InProgress()
                else -> throw UnknownResultException()
            }
        }

        fun toMessage(callbackMessage: IntegrationCallbackMessage): Message {
            return Message.obtain().apply {
                what = callbackMessage.resultCode
            }
        }

    }

    class UnknownResultException : Exception()
}

class IntegrationCallbackHandler(val onSuccess: () -> Unit, val onError: () -> Unit, val onInProgress: () -> Unit) : Handler() {

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val callbackMessage = IntegrationCallbackMessage.fromMessage(msg)
        when (callbackMessage) {
            is IntegrationCallbackMessage.Success -> onSuccess.invoke()
            is IntegrationCallbackMessage.Error -> onError.invoke()
            is IntegrationCallbackMessage.InProgress -> onInProgress.invoke()
        }
    }
}
