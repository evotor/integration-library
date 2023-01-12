package ru.evotor.framework.core.action.command.print_cash_command

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ActivityStarter
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl
import java.math.BigDecimal

/**
 * Команда печати чека внесения наличных
 *
 * @param partnerName Получатель
 * @param description Основание
 * @param sum Сумма выплаты
 */
class PrintCashIncomeDocumentCommand(
    val partnerName: String,
    val description: String,
    val sum: BigDecimal
) : IBundlable {
    override fun toBundle(): Bundle =
        Bundle().apply {
            putString(KEY_PARTNER_NAME, partnerName)
            putString(KEY_DESCRIPTION, description)
            putSerializable(KEY_SUM, sum)
        }

    fun process(context: Context, callback: IntegrationManagerCallback) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(ACTION_NAME, context.applicationContext)
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(context.applicationContext)
            .call(
                ACTION_NAME,
                componentNameList[0],
                this,
                ActivityStarter(context),
                callback,
                Handler(Looper.getMainLooper())
            )
    }

    companion object {
        const val ACTION_NAME = "evo.v2.cash.income.printDocument"

        private const val KEY_PARTNER_NAME = "partner_name"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_SUM = "cash_income_sum"

        fun create(bundle: Bundle?): PrintCashIncomeDocumentCommand? {
            return if (bundle == null) {
                null
            } else {
                PrintCashIncomeDocumentCommand(
                    partnerName = bundle.getString(KEY_PARTNER_NAME, ""),
                    description = bundle.getString(KEY_DESCRIPTION, ""),
                    sum = bundle.getSerializable(KEY_SUM) as BigDecimal
                )
            }
        }
    }
}
