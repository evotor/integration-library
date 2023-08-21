package ru.evotor.framework.core.action.command.print_z_report_command

import android.os.Bundle

import ru.evotor.IBundlable

class PrintZReportCommandResult(
    val notPrinted: Boolean = false
) : IBundlable {

    override fun toBundle(): Bundle {
        return Bundle().also {
            it.putBoolean(KEY_NOT_PRINTED, notPrinted)
        }
    }

    companion object {

        private const val KEY_NOT_PRINTED = "notPrinted"

        /**
         * Нужна синхронизация даты/времени ККМ и терминала
         */
        const val ERROR_CODE_DATETIME_SYNC_REQUIRED = -1

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
         * Ошибка печати
         */
        const val ERROR_CODE_PRINT_DOCUMENT_FAILED = -5

        /**
         * Пользователь не найден на терминале
         */
        const val ERROR_CODE_USER_NOT_FOUND = -6

        /**
         * Предыдущий фискальный документ не был допечатан
         */
        const val ERROR_CODE_PREVIOUS_DOCUMENT_NOT_PRINTED = -7


        @JvmStatic
        fun create(bundle: Bundle?): PrintZReportCommandResult? {
            return bundle?.let {
                PrintZReportCommandResult(
                    notPrinted = it.getBoolean(KEY_NOT_PRINTED, false)
                )
            }
        }
    }
}
