package ru.evotor.framework.core.action.command.print_cash_command

import android.os.Bundle
import ru.evotor.IBundlable

class PrintCashIncomeCommandResult : IBundlable {
    override fun toBundle(): Bundle =
        Bundle()

    companion object {

        /**
         * Поле "получатель" не может быть пустым
         */
        const val ERROR_CODE_EMPTY_PARTNER_NAME = -1

        /**
         * Поле "основание" не может быть пустым
         */
        const val ERROR_CODE_EMPTY_DESCRIPTION = -2

        /**
         * Сумма не может быть равна 0
         */
        const val ERROR_CODE_ZERO_SUM = -3

        /**
         * Ошибка при регистрации документа внесения
         */
        const val ERROR_CODE_DOCUMENT_NOT_REGISTERED = -4

        /**
         * Ошибка при работе с ККМ
         */
        const val ERROR_CODE_KKM_ERROR = -5

        fun create(bundle: Bundle?): PrintCashIncomeCommandResult? {
            return if (bundle == null) {
                null
            } else {
                PrintCashIncomeCommandResult()
            }
        }
    }
}
