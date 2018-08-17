package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.Utils
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemEvent
import ru.evotor.framework.payment.PaymentSystem
import ru.evotor.framework.receipt.Position
import java.math.BigDecimal

internal class ReceiptEventMapper(private val bundle: Bundle) {

    fun getReceiptUuid(): String? =
            bundle.getString(KEY_RECEIPT_UUID)


    fun getChanges(): List<IPositionChange> =
            Utils.filterByClass(
                    ChangesMapper.create(bundle.getParcelableArray(KEY_CHANGES)),
                    IPositionChange::class.java
            )

    fun getReceiptDiscount(): BigDecimal? =
            bundle.getMoney(KEY_RECEIPT_DISCOUNT)

    fun getPosition(): Position? =
            PositionMapper.from(bundle.getBundle(KEY_POSITION))

    fun getPaymentSystemOperationType(): PaymentSystemEvent.OperationType =
            Utils.safeValueOf(
                    PaymentSystemEvent.OperationType::class.java,
                    bundle.getString(KEY_PAYMENT_SYSTEM_OPERATION_TYPE, null),
                    PaymentSystemEvent.OperationType.UNKNOWN
            )

    fun getPaymentSystemAccountId(): String? =
            bundle.getString(KEY_PAYMENT_SYSTEM_ACCOUNT_ID, null)

    fun getPaymentSystemSum(): BigDecimal? =
            bundle.getMoney(KEY_PAYMENT_SYSTEM_SUM, BigDecimal(0))

    fun getPaymentSystemRrn(): String? =
            bundle.getString(KEY_PAYMENT_SYSTEM_RRN, null)

    fun getPaymentSystemDescription(): String? =
            bundle.getString(KEY_PAYMENT_SYSTEM_DESCRIPTION, null)

    fun getPaymentSystem(): PaymentSystem? =
            PaymentSystemMapper.from(bundle.getBundle(KEY_PAYMENT_SYSTEM))

    companion object {

        private const val KEY_RECEIPT_UUID = "receiptUuid"

        private const val KEY_CHANGES = "changes"

        private const val KEY_RECEIPT_DISCOUNT = "discount"

        private const val KEY_POSITION = "position"

        private const val KEY_PAYMENT_SYSTEM_OPERATION_TYPE = "operationType"

        private const val KEY_PAYMENT_SYSTEM_ACCOUNT_ID = "accountId"

        private const val KEY_PAYMENT_SYSTEM_SUM = "sum"

        private const val KEY_PAYMENT_SYSTEM_RRN = "rrn"

        private const val KEY_PAYMENT_SYSTEM_DESCRIPTION = "description"

        private const val KEY_PAYMENT_SYSTEM = "paymentSystem"

    }

}