package ru.evotor.framework.core.action.command.print_z_report_command

import android.os.Bundle

import ru.evotor.IBundlable

class PrintZReportCommandResult : IBundlable {

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {

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


        @JvmStatic
        fun create(bundle: Bundle?): PrintZReportCommandResult? {
            return bundle?.let { PrintZReportCommandResult() }
        }
    }
}
