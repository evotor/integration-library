package ru.evotor.framework.core.action.command.print_z_report_command

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ICanStartActivity
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl

/**
 * Команда снятия и печати Z-отчёта.
 */
class PrintZReportCommand() : IBundlable {

    fun process(context: Context, callback: IntegrationManagerCallback) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, context.applicationContext)
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(context.applicationContext)
                .call(NAME,
                        componentNameList[0],
                        this,
                        ICanStartActivity { context.startActivity(it.apply { it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }) },
                        callback,
                        Handler(Looper.getMainLooper())
                )
    }

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {
        /**
         * Разрешение для снятия и печати Z-отчёта.
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
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