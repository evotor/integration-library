package ru.evotor.framework.core.action.command.print_session_opening_report_command

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ActivityStarter
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl

/**
 * Команда открытия смены POS и ККТ.
 * @param userUuid Идентификатор сотрудника в формате `uuid4`, от лица которого будет произведена операция. Если передано null, то будет выбран текущий авторизованный сотрудник.
 */
class PrintSessionOpeningReportCommand(
    val userUuid: String? = null
) : IBundlable {

    fun process(context: Context, callback: IntegrationManagerCallback) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(
            NAME,
            context.applicationContext
        )
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(context.applicationContext)
            .call(
                NAME,
                componentNameList[0],
                this,
                ActivityStarter(context),
                callback,
                Handler(Looper.getMainLooper())
            )
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_USER_UUID, userUuid)
        }
    }

    companion object {
        /**
         * Разрешение для открытия смены POS и ККТ.
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
        const val NAME_PERMISSION = "ru.evotor.permission.PRINT_SESSION_OPENING_REPORT"
        const val NAME = "evo.v2.session.opening.print"

        private const val KEY_USER_UUID = "userUuid"

        fun create(bundle: Bundle?): PrintSessionOpeningReportCommand? {
            if (bundle == null) {
                return null
            }
            return PrintSessionOpeningReportCommand(
                userUuid = getUserUuid(bundle)
            )
        }

        internal fun getUserUuid(bundle: Bundle): String? {
            return bundle.getString(KEY_USER_UUID, null)
        }
    }
}
