package ru.evotor.framework.core.action.command.print_z_report_command


sealed class PrintZReportCommandErrorData {
    class KktError(val kktErrorCode: Int?, val kktErrorDescription: String?) : PrintZReportCommandErrorData()
}