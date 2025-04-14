package ru.evotor.framework.payment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils

data class AdditionalTransactionData(
    val tid: String,
    val inn: String?,
    val primaryAccountNumber: String,
    val issuerBik: String,
    val issuerTransactionNumber: String,
) : IBundlable, Parcelable {

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(KEY_TID, tid)
        bundle.putString(KEY_INN, inn)
        bundle.putString(KEY_PRIMARY_ACCOUNT_NUMBER, primaryAccountNumber)
        bundle.putString(KEY_PRIMARY_ISSUER_BIK, issuerBik)
        bundle.putString(KEY_PRIMARY_ISSUER_TRANSACTION_NUMBER, issuerTransactionNumber)
        return bundle
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        ParcelableUtils.writeExpand(parcel, VERSION) { parcel ->
            parcel.writeString(tid)
            parcel.writeString(inn)
            parcel.writeString(primaryAccountNumber)
            parcel.writeString(issuerBik)
            parcel.writeString(issuerTransactionNumber)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        private const val VERSION = 1

        private const val KEY_TID = "tid"
        private const val KEY_INN = "inn"
        private const val KEY_PRIMARY_ACCOUNT_NUMBER = "primaryAccountNumber"
        private const val KEY_PRIMARY_ISSUER_BIK = "issuerBik"
        private const val KEY_PRIMARY_ISSUER_TRANSACTION_NUMBER = "issuerTransactionNumber"

        @JvmStatic
        fun fromBundle(bundle: Bundle?): AdditionalTransactionData? {
            bundle ?: return null

            return AdditionalTransactionData(
                tid = bundle.getString(KEY_TID) ?: return null,
                inn = bundle.getString(KEY_INN),
                primaryAccountNumber = bundle.getString(KEY_PRIMARY_ACCOUNT_NUMBER) ?: return null,
                issuerBik = bundle.getString(KEY_PRIMARY_ISSUER_BIK) ?: return null,
                issuerTransactionNumber = bundle.getString(KEY_PRIMARY_ISSUER_TRANSACTION_NUMBER) ?: return null,
            )
        }

        @JvmField
        val CREATOR = object : Parcelable.Creator<AdditionalTransactionData> {
            override fun createFromParcel(parcel: Parcel): AdditionalTransactionData {
                return create(parcel)
            }

            override fun newArray(size: Int): Array<AdditionalTransactionData?> {
                return arrayOfNulls(size)
            }
        }

        private fun create(dest: Parcel): AdditionalTransactionData {
            var additionalTransactionData: AdditionalTransactionData? = null
            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->
                // version 1
                val tid = parcel.readString() ?: throw IllegalStateException("tid should be null")
                val inn = parcel.readString()
                val primaryAccountNumber = parcel.readString() ?: throw IllegalStateException("primaryAccountNumber should be null")
                val issuerBik = parcel.readString() ?: throw IllegalStateException("issuerBik should be null")
                val issuerTransactionNumber = parcel.readString() ?: throw IllegalStateException("issuerTransactionNumber should be null")

                additionalTransactionData = AdditionalTransactionData(
                    tid = tid,
                    inn = inn,
                    primaryAccountNumber = primaryAccountNumber,
                    issuerBik = issuerBik,
                    issuerTransactionNumber = issuerTransactionNumber,
                )
            }
            checkNotNull(additionalTransactionData)
            return additionalTransactionData as AdditionalTransactionData

        }
    }
}
