package ru.evotor.framework.core.action.command.clear_receipt

import android.os.Bundle
import ru.evotor.IBundlable

class ClearReceiptCommandResult : IBundlable {
    companion object {
        /**
         * Неопознанная ошибка
         */
        const val CODE_UNIDENTIFIED_ERROR = -1

        /**
         * Некорректные входные параметры
         */
        const val CODE_INVALID_INPUT = -2

        /**
         * Сотрудник не авторизован
         */
        const val CODE_EMPLOYEE_NOT_AUTHORIZED = -3

        /**
         * Чек с таким UUID не найден
         */
        const val CODE_RECEIPT_NOT_FOUND = -4

        /**
         * Чек в процессе обработки, очистка невозможна
         */
        const val CODE_RECEIPT_IS_PROCESSING = -5

        /**
         * Чек уже закрыт, очистка невозможна
         */
        const val CODE_RECEIPT_IS_CLOSED = -6
    }

    override fun toBundle(): Bundle {
        return Bundle()
    }
}
