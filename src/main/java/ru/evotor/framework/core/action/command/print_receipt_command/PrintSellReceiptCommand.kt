package ru.evotor.framework.core.action.command.print_receipt_command

import android.app.Activity
import android.os.Bundle
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra
import ru.evotor.framework.receipt.Payment
import ru.evotor.framework.receipt.Position
import ru.evotor.framework.receipt.PrintGroup
import ru.evotor.framework.receipt.Receipt
import ru.evotor.framework.sumByBigDecimal
import java.math.BigDecimal
import java.util.*


/**
 * Команда печати чека продажи.
 * @param printReceipts Список чеков для печати.
 * @param extra Дополнительные данные к чеку.
 * @param clientPhone Телефон клиента.
 * @param clientEmail Электронная почта клиента.
 * @param receiptDiscount Скидка на чек.
 * @param paymentAddress Адрес места расчёта
 * @param paymentPlace Место расчёта
 */
class PrintSellReceiptCommand(
        printReceipts: List<Receipt.PrintReceipt>,
        extra: SetExtra?,
        clientPhone: String?,
        clientEmail: String?,
        receiptDiscount: BigDecimal?,
        paymentAddress: String? = null,
        paymentPlace: String? = null
) : PrintReceiptCommand(
        printReceipts,
        extra,
        clientPhone,
        clientEmail,
        receiptDiscount,
        paymentAddress,
        paymentPlace
) {

    /**
     * @param positions Список позиций
     * @param payments Список оплат
     * @param clientPhone Телефон клиента
     * @param clientEmail Эл.почта клиента
     * @param paymentAddress Адрес места расчёта
     * @param paymentPlace Место расчёта
     */
    constructor(
            positions: List<Position>,
            payments: List<Payment>,
            clientPhone: String?,
            clientEmail: String?,
            paymentAddress: String? = null,
            paymentPlace: String? = null) : this(
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
                        hashMapOf()
                ))
            },
            null,
            clientPhone,
            clientEmail,
            BigDecimal.ZERO,
            paymentAddress,
            paymentPlace
    )

    fun process(activity: Activity, callback: IntegrationManagerCallback) {
        process(activity, callback, NAME)
    }

    companion object {

        const val NAME = "evo.v2.receipt.sell.printReceipt"

        fun create(bundle: Bundle?): PrintSellReceiptCommand? {
            if (bundle == null) {
                return null
            }
            return PrintSellReceiptCommand(
                    getPrintReceipts(bundle),
                    getSetExtra(bundle),
                    getClientPhone(bundle),
                    getClientEmail(bundle),
                    getReceiptDiscount(bundle),
                    getPaymentAddress(bundle),
                    getPaymentPlace(bundle)
            )
        }
    }
}