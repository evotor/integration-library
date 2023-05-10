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
 * Команда печати чека выплаты наличных
 *
 * @param partnerName Получатель
 * @param description Основание
 * @param sum Сумма выплаты
 * @param paymentCategoryId Id категории платежа. Должен иметь одно из следующих значений:
 * 1 - Инкассация (default)
 * 2 - Оплата поставщику
 * 3 - Оплата услуг
 * 4 - Аренда
 * 5 - Заработная плата
 * 6 - Прочее
 */
class PrintCashOutcomeDocumentCommand(
    val partnerName: String,
    val description: String,
    val sum: BigDecimal,
    val paymentCategoryId: Long = 1
) : IBundlable {
    override fun toBundle(): Bundle =
        Bundle().apply {
            putString(KEY_PARTNER_NAME, partnerName)
            putString(KEY_DESCRIPTION, description)
            putLong(KEY_PAYMENT_CATEGORY_NAME, paymentCategoryId)
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
        const val ACTION_NAME = "evo.v2.cash.outcome.printDocument"

        private const val KEY_PARTNER_NAME = "partner_name"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_SUM = "cash_income_sum"
        private const val KEY_PAYMENT_CATEGORY_NAME = "payment_category_name"

        fun create(bundle: Bundle?): PrintCashOutcomeDocumentCommand? {
            return if (bundle == null) {
                null
            } else {
                PrintCashOutcomeDocumentCommand(
                    partnerName = bundle.getString(KEY_PARTNER_NAME, ""),
                    description = bundle.getString(KEY_DESCRIPTION, ""),
                    sum = bundle.getSerializable(KEY_SUM) as BigDecimal,
                    paymentCategoryId = bundle.getLong(KEY_PAYMENT_CATEGORY_NAME, 1)
                )
            }
        }
    }
}
