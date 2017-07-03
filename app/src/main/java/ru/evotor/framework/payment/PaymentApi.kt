package ru.evotor.framework.payment

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.receipt.PaymentTable
import ru.evotor.framework.safeValueOf

object PaymentApi {

    const val AUTHORITY = "ru.evotor.evotorpos.payment"

    @JvmField val BASE_URI = Uri.parse("content://$AUTHORITY")

    @JvmStatic
    fun getPaymentSystems(context: Context): MutableList<Pair<PaymentSystem, MutableList<PaymentAccount>>> {
        val paymentSystemList = mutableListOf<Pair<PaymentSystem, MutableList<PaymentAccount>>>()

        val cursor: Cursor? = context.contentResolver.query(PaymentTable.URI, null, null, null, null)

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    val paymentSystem = PaymentSystem(
                            safeValueOf(cursor.getString(cursor.getColumnIndex(PaymentTable.ROW_PAYMENT_TYPE))),
                            cursor.getString(cursor.getColumnIndex(PaymentTable.ROW_PAYMENT_SYSTEM_USER_DESCRIPTION))
                    )

                    val paymentAccount = PaymentAccount(
                            cursor.getString(cursor.getColumnIndex(PaymentTable.ROW_ACCOUNT_USER_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndex(PaymentTable.ROW_ACCOUNT_ID))
                    )

                    var inList = false
                    paymentSystemList.forEach {
                        if (it.first == paymentSystem) {
                            inList = true
                            it.second.add(paymentAccount)
                        }
                    }
                    if (!inList) {
                        paymentSystemList.add(Pair(paymentSystem, mutableListOf(paymentAccount)))
                    }
                }
            } finally {
                cursor.close()
            }
        }

        return paymentSystemList
    }

}