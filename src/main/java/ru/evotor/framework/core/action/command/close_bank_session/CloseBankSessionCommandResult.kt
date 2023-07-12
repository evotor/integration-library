package ru.evotor.framework.core.action.command.close_bank_session

import android.os.Bundle
import ru.evotor.IBundlable

class CloseBankSessionCommandResult(
    val slipLines: Array<String>
) : IBundlable {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putStringArray(KEY_SLIP_LINES, slipLines)
        }
    }

    companion object {

        /**
         * ККМ в данный момент выполняет другую операцию
         */
        const val ERROR_CODE_KKM_IS_BUSY = -1

        /**
         * Нет авторизованного пользователя на терминале
         */
        const val ERROR_CODE_NO_AUTHENTICATED_USER = -2

        /**
         * У приложения нет необходимого разрешения (permission)
         */
        const val ERROR_CODE_NO_PERMISSION = -3

        /**
         * Ошибка закрытия смены банковского терминала
         */
        const val ERROR_CODE_CLOSE_PINPAD_SESSION_ERROR = -4

        /**
         * Пользователь не найден на терминале
         */
        const val ERROR_CODE_USER_NOT_FOUND = -5

        /**
         * Платежная система по умолчанию или с заданным accountId не найдена
         */
        const val ERROR_CODE_PINPAD_NOT_FOUND = -6

        private const val KEY_SLIP_LINES = "slipLines"

        @JvmStatic
        fun create(bundle: Bundle?): CloseBankSessionCommandResult? {
            return bundle?.let {
                CloseBankSessionCommandResult(
                    it.getStringArray(KEY_SLIP_LINES) ?: emptyArray()
                )
            }
        }
    }
}
