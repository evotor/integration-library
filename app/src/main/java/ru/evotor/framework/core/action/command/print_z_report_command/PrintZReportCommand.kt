package ru.evotor.framework.core.action.command.print_z_report_command

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl

/**
 * Команда снятия Z отчета
 */
class PrintZReportCommand() : IBundlable {

    fun process(activity: Activity, callback: IntegrationManagerCallback) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, activity.applicationContext)
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(activity.applicationContext)
                .call(NAME,
                        componentNameList[0],
                        this,
                        activity,
                        callback,
                        Handler(Looper.getMainLooper())
                )
    }

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {
        const val NAME_PERMISSION = "ru.evotor.permission.PRINT_Z_REPORT"
        const val NAME = "evo.v2.zreport.print"

        fun create(bundle: Bundle?): PrintZReportCommand? {
            if (bundle == null) {
                return null
            }
            return PrintZReportCommand()
        }
    }
}