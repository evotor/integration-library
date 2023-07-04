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
         * Отсутствует необходимое обновление для работы по ФФД 1.2.
         * Чтобы его получить, установите из Эвотор.Маркета приложение «Маркировка»,
         * если торгуете маркированным товаром, или «Смарт-терминал Плюс» в остальных случаях.
         */
        const val ERROR_CODE_FFD_12_FRAUD_DETECTED = -1

        /**
         * ККМ в данный момент выполняет другую операцию
         */
        const val ERROR_CODE_KKM_IS_BUSY = -2

        /**
         * Нет авторизованного пользователя на терминале
         */
        const val ERROR_CODE_NO_AUTHENTICATED_USER = -3

        /**
         * У приложения нет необходимого разрешения (permission)
         */
        const val ERROR_CODE_NO_PERMISSION = -4

        /**
         * Ошибка закрытия смены банковского терминала
         */
        const val ERROR_CODE_CLOSE_PINPAD_SESSION_ERROR = -5

        /**
         * Пользователь не найден на терминале
         */
        const val ERROR_CODE_USER_NOT_FOUND = -6

        /**
         * Платежная система с заданным accountId не найдена
         */
        const val ERROR_CODE_PINPAD_NOT_FOUND = -7

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
