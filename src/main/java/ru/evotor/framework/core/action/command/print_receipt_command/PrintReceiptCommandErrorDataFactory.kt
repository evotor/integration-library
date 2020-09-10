package ru.evotor.framework.core.action.command.print_receipt_command

import android.os.Bundle
import ru.evotor.framework.core.Error


object PrintReceiptCommandErrorDataFactory {
    private const val KEY_EXT_TYPE = "EXT_TYPE"
    private const val EXT_TYPE_KKT_ERROR = "KKT_ERROR"

    @JvmStatic
    fun create(data: Bundle?): PrintReceiptCommandErrorData? {
        return when (data?.getString(KEY_EXT_TYPE, null)) {
            EXT_TYPE_KKT_ERROR -> KktErrorFactory.create(data)
            else -> null
        }
    }

    fun toData(data: PrintReceiptCommandErrorData): Bundle {
        return when (data) {
            is PrintReceiptCommandErrorData.KktError -> KktErrorFactory.toData(data)
        }
    }

    private object KktErrorFactory {
        private const val KEY_KKT_ERROR_CODE = "KKT_ERROR_CODE"
        private const val KEY_KKT_ERROR_DESCRIPTION = "KKT_ERROR_DESCRIPTION"

        fun create(data: Bundle): PrintReceiptCommandErrorData.KktError {
            return PrintReceiptCommandErrorData.KktError(
                    kktErrorCode = data.optInt(KEY_KKT_ERROR_CODE),
                    kktErrorDescription = data.getString(KEY_KKT_ERROR_DESCRIPTION, null)
            )
        }

        fun toData(data: PrintReceiptCommandErrorData.KktError): Bundle {
            return Bundle().apply {
                putString(KEY_EXT_TYPE, EXT_TYPE_KKT_ERROR)
                data.kktErrorCode?.let { putInt(KEY_KKT_ERROR_CODE, it) }
                putString(KEY_KKT_ERROR_DESCRIPTION, data.kktErrorDescription)
            }
        }

        private fun Bundle.optInt(key: String): Int? {
            return if (containsKey(key)) {
                getInt(key)
            } else {
                null
            }
        }
    }
}