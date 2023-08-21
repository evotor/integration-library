package ru.evotor.framework.core.action.command.print_unprinted_document

import android.os.Bundle
import ru.evotor.IBundlable

class PrintUnprintedDocumentCommandResult: IBundlable {

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {

        /**
         * ККМ в данный момент выполняет другую операцию
         */
        const val ERROR_CODE_KKM_IS_BUSY = -1

        /**
         * Ошибка печати
         */
        const val ERROR_CODE_PRINT_DOCUMENT_FAILED = -2

        @JvmStatic
        fun create(bundle: Bundle?): PrintUnprintedDocumentCommandResult? {
            return bundle?.let {
                PrintUnprintedDocumentCommandResult()
            }
        }
    }
}
