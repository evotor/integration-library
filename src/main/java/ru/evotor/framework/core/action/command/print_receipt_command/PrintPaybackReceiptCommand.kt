package ru.evotor.framework.core.action.command.print_receipt_command

import android.content.Context
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
 * Команда печати чека возврата.
 * @param printReceipts Список чеков для печати.
 * @param extra Дополнительные данные к чеку.
 * @param clientPhone Телефон клиента.
 * @param clientEmail Электронная почта клиента.
 * @param receiptDiscount Скидка на чек.
 * @param paymentAddress Адрес места расчёта
 * @param paymentPlace Место расчёта
 * @param userUuid Идентификатор сотрудника в формате `uuid4`, от лица которого будет произведена операция. Если передано null, то будет выбран текущий авторизованный сотрудник. @see ru.evotor.framework.users.UserAPI
 */
class PrintPaybackReceiptCommand(
        printReceipts: List<Receipt.PrintReceipt>,
        extra: SetExtra?,
        clientPhone: String?,
        clientEmail: String?,
        receiptDiscount: BigDecimal?,
        val sellReceiptUuid: String? = null,
        paymentAddress: String? = null,
        paymentPlace: String? = null,
        userUuid: String? = null
) : PrintReceiptCommand(
        printReceipts = printReceipts,
        extra = extra,
        clientPhone = clientPhone,
        clientEmail = clientEmail,
        receiptDiscount = receiptDiscount,
        paymentAddress = paymentAddress,
        paymentPlace = paymentPlace,
        userUuid = userUuid
) {

    /**
     * @param positions Список позиций
     * @param payments Список оплат
     * @param clientPhone Телефон клиента
     * @param clientEmail Эл.почта клиента
     * @param sellReceiptUuid Идентифиатор чека продажи, на основании которого осуществляется возврат
     * @param paymentAddress Адрес места расчёта
     * @param paymentPlace Место расчёта
     */
    constructor(
            positions: List<Position>,
            payments: List<Payment>,
            clientPhone: String?,
            clientEmail: String?,
            sellReceiptUuid: String? = null,
            paymentAddress: String? = null,
            paymentPlace: String? = null,
            userUuid: String? = null) : this(
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
            sellReceiptUuid,
            paymentAddress,
            paymentPlace,
            userUuid
    )

    fun process(context: Context, callback: IntegrationManagerCallback) {
        process(context, callback, NAME)
    }

    override fun toBundle() = super.toBundle().apply {
        this.putString(KEY_SELL_RECEIPT_UUID, sellReceiptUuid)
    }

    companion object {

        const val NAME = "evo.v2.receipt.payback.printReceipt"

        private const val KEY_SELL_RECEIPT_UUID = "SELL_RECEIPT_UUID"

        fun create(bundle: Bundle?): PrintPaybackReceiptCommand? {
            if (bundle == null) {
                return null
            }
            return PrintPaybackReceiptCommand(
                    printReceipts = getPrintReceipts(bundle),
                    extra = getSetExtra(bundle),
                    clientPhone = getClientPhone(bundle),
                    clientEmail = getClientEmail(bundle),
                    receiptDiscount = getReceiptDiscount(bundle),
                    sellReceiptUuid = bundle.getString(KEY_SELL_RECEIPT_UUID),
                    paymentAddress = getPaymentAddress(bundle),
                    paymentPlace = getPaymentPlace(bundle),
                    userUuid = getUserUuid(bundle)
            )
        }
    }
}
