package ru.evotor.framework.core.action.command.print_receipt_command


sealed class PrintReceiptCommandErrorData {
    class KktError(val kktErrorCode: Int?, val kktErrorDescription: String?) : PrintReceiptCommandErrorData()
}