package ru.evotor.framework.core.action.command.close_bank_session

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ActivityStarter
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl


/**
 * Команда закрытия смены патежного терминала
 * @param userUuid - Идентификатор сотрудника в формате `uuid4`, от лица которого будет произведена операция. Если передано null, то будет выбран текущий авторизованный сотрудник. @see ru.evotor.framework.users.UserAPI
 * @param paymentSystemAccountId - Идентификатор аккаунта в рамках платёжной системы. Если передано null, то будет выбран пинпад по умолчанию. @see ru.evotor.framework.payment.PaymentSystemApi
 */
class CloseBankSessionCommand(
    val paymentSystemAccountId: Int? = null,
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
            paymentSystemAccountId?.let {
                putInt(KEY_ACCOUNT_ID, it)
            }
        }
    }

    companion object {
        /**
         * Разрешение для закрытия смены банковского терминала.
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
        const val NAME_PERMISSION = "ru.evotor.permission.CLOSE_BANK_SESSION"
        const val NAME = "evo.v2.bank_session.close"

        private const val KEY_USER_UUID = "userUuid"
        private const val KEY_ACCOUNT_ID = "paymentSystemAccountId"

        fun create(bundle: Bundle?): CloseBankSessionCommand? {
            if (bundle == null) {
                return null
            }
            return CloseBankSessionCommand(
                getPaymentSystemAccountId(bundle),
                getUserUuid(bundle)
            )
        }

        private fun getUserUuid(bundle: Bundle): String? {
            return bundle.getString(KEY_USER_UUID, null)
        }

        private fun getPaymentSystemAccountId(bundle: Bundle): Int? {
            return if (bundle.containsKey(KEY_ACCOUNT_ID)) {
                bundle.getInt(KEY_ACCOUNT_ID)
            } else null
        }
    }
}
