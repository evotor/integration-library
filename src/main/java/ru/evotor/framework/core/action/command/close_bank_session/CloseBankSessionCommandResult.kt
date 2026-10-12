package ru.evotor.framework.core.action.command.close_bank_session

import android.os.Bundle
import ru.evotor.IBundlable
import java.util.Date

/** Результат операции закрытия банковской смены.
 * @param slipLines Массив строк чека;
 * @param resultCode Код результата операции, при "0" операция считается успешной;
 * @param errorTitle Заголовок ошибки в случае, если она произошла ([resultCode] отличен от "0"). Может быть пустым;
 * @param errorMessage Описание ошибки в случае, если она произошла ([resultCode] отличен от "0"). Может быть пустым;
 * @param datetime Дата операции;
 * @param terminalId ID терминала. Может быть пустым. */
class CloseBankSessionCommandResult(
    val slipLines: Array<String>,
    val resultCode: String?,
    val errorTitle: String?,
    val errorMessage: String?,
    val datetime: Date?,
    val terminalId: String?
) : IBundlable {
    override fun toBundle(): Bundle {
        return Bundle().apply {
            putStringArray(KEY_SLIP_LINES, slipLines)
            putString(KEY_RESULT_CODE, resultCode)
            putString(KEY_ERROR_TITLE, errorTitle)
            putString(KEY_ERROR_MESSAGE, errorMessage)
            putSerializable(KEY_DATETIME, datetime)
            putString(KEY_TERMINAL_ID, terminalId)
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
        private const val KEY_RESULT_CODE = "resultCode"
        private const val KEY_ERROR_TITLE = "errorTitle"
        private const val KEY_ERROR_MESSAGE = "errorMessage"
        private const val KEY_DATETIME = "datetime"
        private const val KEY_TERMINAL_ID = "terminalId"

        @JvmStatic
        fun create(bundle: Bundle?): CloseBankSessionCommandResult? {
            return bundle?.let {
                CloseBankSessionCommandResult(
                    it.getStringArray(KEY_SLIP_LINES) ?: emptyArray(),
                    it.getString(KEY_RESULT_CODE),
                    it.getString(KEY_ERROR_TITLE),
                    it.getString(KEY_ERROR_MESSAGE),
                    it.getSerializable(KEY_DATETIME) as Date?,
                    it.getString(KEY_TERMINAL_ID)
                )
            }
        }
    }
}
