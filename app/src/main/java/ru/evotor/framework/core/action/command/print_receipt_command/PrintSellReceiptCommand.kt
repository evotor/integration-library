package ru.evotor.framework.core.action.command.print_receipt_command

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.calculator.MoneyCalculator
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl
import ru.evotor.framework.core.action.datamapper.PrintReceiptMapper
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra
import ru.evotor.framework.min
import ru.evotor.framework.payment.PaymentType
import ru.evotor.framework.receipt.Payment
import ru.evotor.framework.receipt.Position
import ru.evotor.framework.receipt.PrintGroup
import ru.evotor.framework.receipt.Receipt
import ru.evotor.framework.sumByBigDecimal
import java.math.BigDecimal
import java.util.*


/**
 * Команда печати чека продажи
 * @param printReceipts Список печатных чеков
 * @param extra Экстра данные к чеку
 */
class PrintSellReceiptCommand(
        val printReceipts: List<Receipt.PrintReceipt>,
        val extra: SetExtra?,
        val clientPhone: String?,
        val clientEmail: String?) : IBundlable {

    /**
     * @param positions Список позиций
     * @param payments Список оплат
     */
    constructor(
            positions: List<Position>,
            payments: List<Payment>,
            clientPhone: String?,
            clientEmail: String?) : this(
            ArrayList<Receipt.PrintReceipt>().apply {
                add(Receipt.PrintReceipt(
                        PrintGroup(
                                UUID.randomUUID().toString(),
                                PrintGroup.Type.CASH_RECEIPT,
                                null,
                                null,
                                null,
                                null,
                                clientEmail == null && clientPhone == null
                        ),
                        positions,
                        payments.associate { it to it.value },
                        calculateChanges(
                                positions.sumByBigDecimal { it.totalWithSubPositionsAndWithoutDocumentDiscount },
                                payments
                        ),
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ))
            },
            null,
            clientPhone,
            clientEmail
    )

    /**
     *
     */
    fun process(activity: Activity, callback: IntegrationManagerCallback) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, activity.applicationContext)
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(activity.applicationContext)
                .call(PrintSellReceiptCommand.NAME,
                        componentNameList[0],
                        this,
                        activity,
                        callback,
                        Handler(Looper.getMainLooper())
                )
    }

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putParcelableArrayList(KEY_PRINT_RECEIPTS, printReceipts.mapTo(ArrayList(), { PrintReceiptMapper.toBundle(it) }))
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra?.toBundle())
        bundle.putString(KEY_CLIENT_EMAIL, clientEmail)
        bundle.putString(KEY_CLIENT_PHONE, clientPhone)
        return bundle
    }

    companion object {

        const val NAME = "evo.v2.receipt.sell.printReceipt"
        private const val KEY_PRINT_RECEIPTS = "printReceipts"
        private const val KEY_RECEIPT_EXTRA = "extra"
        private const val KEY_CLIENT_EMAIL = "clientEmail"
        private const val KEY_CLIENT_PHONE = "clientPhone"

        fun create(bundle: Bundle?): PrintSellReceiptCommand? {
            if (bundle == null) {
                return null
            }
            return PrintSellReceiptCommand(
                    bundle.getParcelableArrayList<Bundle>(KEY_PRINT_RECEIPTS)
                            .map { PrintReceiptMapper.from(it) }
                            .filterNotNull(),
                    SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                    bundle.getString(KEY_CLIENT_PHONE, null),
                    bundle.getString(KEY_CLIENT_EMAIL, null)
            )
        }


        private fun calculateChanges(sum: BigDecimal, payments: List<Payment>): Map<Payment, BigDecimal> {
            var remaining = sum
            val result = HashMap<Payment, BigDecimal>()
            for (payment in payments) {
                if (payment.system?.paymentType != PaymentType.CASH) {
                    result.put(payment, BigDecimal.ZERO)
                    continue
                }

                val change = min(payment.value, remaining)
                remaining = MoneyCalculator.subtract(remaining, change)
                result.put(payment, change)
            }

            return result
        }
    }
}