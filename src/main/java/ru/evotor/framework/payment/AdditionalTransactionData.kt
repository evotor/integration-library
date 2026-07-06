package ru.evotor.framework.payment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils
import ru.evotor.framework.optLong

data class AdditionalTransactionData(
    val tid: String?,
    val initialDatetime: Long,
    val paymentSystemCode: String,
    val acquiringBankCode: String,
    val authorizationCode: String,
    val transactionId: String?
) : IBundlable, Parcelable {
    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(KEY_TID, tid)
        bundle.putLong(KEY_INITIAL_DATETIME, initialDatetime)
        bundle.putString(KEY_PAYMENT_SYSTEM_CODE, paymentSystemCode)
        bundle.putString(KEY_ACQUIRING_BANK_CODE, acquiringBankCode)
        bundle.putString(KEY_AUTHORIZATION_CODE, authorizationCode)
        bundle.putString(KEY_TRANSACTION_ID, transactionId)
        return bundle
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        ParcelableUtils.writeExpand(parcel, VERSION) { parcel ->
            // version 1
            parcel.writeString("") // fake tid
            parcel.writeString("") // fake inn
            parcel.writeString("") // fake primaryAccountNumber
            parcel.writeString("") // fake issuerBik
            parcel.writeString("") // fake issuerTransactionNumber

            // version 2
            parcel.writeString(tid)
            parcel.writeLong(initialDatetime)
            parcel.writeString(paymentSystemCode)
            parcel.writeString(acquiringBankCode)
            parcel.writeString(authorizationCode)
            parcel.writeString(transactionId)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        private const val VERSION = 2

        private const val KEY_TID = "tid"
        private const val KEY_INITIAL_DATETIME = "initialDatetime"
        private const val KEY_PAYMENT_SYSTEM_CODE = "paymentSystemCode"
        private const val KEY_ACQUIRING_BANK_CODE = "acquiringBankCode"
        private const val KEY_AUTHORIZATION_CODE = "authorizationCode"
        private const val KEY_TRANSACTION_ID = "transactionId"

        @JvmStatic
        fun fromBundle(bundle: Bundle?): AdditionalTransactionData? {
            bundle ?: return null

            return AdditionalTransactionData(
                tid = bundle.getString(KEY_TID) ?: return null,
                initialDatetime = bundle.optLong(KEY_INITIAL_DATETIME) ?: return null,
                paymentSystemCode = bundle.getString(KEY_PAYMENT_SYSTEM_CODE) ?: return null,
                acquiringBankCode = bundle.getString(KEY_ACQUIRING_BANK_CODE) ?: return null,
                authorizationCode = bundle.getString(KEY_AUTHORIZATION_CODE) ?: return null,
                transactionId = bundle.getString(KEY_TRANSACTION_ID),
            )
        }

        @JvmField
        val CREATOR = object : Parcelable.Creator<AdditionalTransactionData?> {
            override fun createFromParcel(parcel: Parcel): AdditionalTransactionData? {
                return create(parcel)
            }

            override fun newArray(size: Int): Array<AdditionalTransactionData?> {
                return arrayOfNulls(size)
            }
        }

        private fun create(dest: Parcel): AdditionalTransactionData? {
            var additionalTransactionData: AdditionalTransactionData? = null
            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->
                // version 1
                parcel.readString() // fake tid
                parcel.readString() // fake inn
                parcel.readString() // fake primaryAccountNumber
                parcel.readString() // fake issuerBik
                parcel.readString() // fake issuerTransactionNumber

                if (version >= 2) {
                    val tid = parcel.readString()
                    val initialDatetime = parcel.readLong()
                    val paymentSystemCode = parcel.readString() ?: throw IllegalStateException("paymentSystemCode should not be null")
                    val acquiringBankCode = parcel.readString() ?: throw IllegalStateException("acquiringBankCode should not be null")
                    val authorizationCode = parcel.readString() ?: throw IllegalStateException("authorizationCode should not be null")
                    val transactionId = parcel.readString()

                    additionalTransactionData = AdditionalTransactionData(
                        tid = tid,
                        initialDatetime = initialDatetime,
                        paymentSystemCode = paymentSystemCode,
                        acquiringBankCode = acquiringBankCode,
                        authorizationCode = authorizationCode,
                        transactionId = transactionId,
                    )
                }
            }
            return additionalTransactionData
        }
    }
}
