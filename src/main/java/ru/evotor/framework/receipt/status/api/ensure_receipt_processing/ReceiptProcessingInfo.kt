package ru.evotor.framework.receipt.status.api.ensure_receipt_processing

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils
import ru.evotor.framework.receipt.Receipt

/**
 * Информация о состоянии чека.
 *
 * @param receiptUuid UUID чека
 * @param status статус чека
 * */
data class ReceiptProcessingInfo(
    val receiptUuid: String,
    val status: Receipt.Status
) : Parcelable, IBundlable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        ParcelableUtils.writeExpand(parcel, PARCELABLE_VERSION) {
            // version 1
            parcel.writeString(receiptUuid)
            parcel.writeInt(status.ordinal)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toBundle() = Bundle().apply { putParcelable(KEY_RECEIPT_PROCESSING_INFO, this@ReceiptProcessingInfo) }

    companion object {
        private const val PARCELABLE_VERSION = 1

        private const val KEY_RECEIPT_PROCESSING_INFO = "KEY_RECEIPT_PROCESSING_INFO"

        @JvmField
        val CREATOR = object : Parcelable.Creator<ReceiptProcessingInfo?> {
            override fun createFromParcel(parcel: Parcel): ReceiptProcessingInfo {
                var info: ReceiptProcessingInfo? = null
                ParcelableUtils.readExpand(parcel, PARCELABLE_VERSION) { parcel, version ->
                    if (version >= 1) {
                        info = ReceiptProcessingInfo(
                            parcel.readString()!!,
                            Receipt.Status.values()[parcel.readInt()]
                        )
                    }
                }
                checkNotNull(info)
                return info as ReceiptProcessingInfo
            }

            override fun newArray(size: Int): Array<ReceiptProcessingInfo?> {
                return arrayOfNulls(size)
            }
        }

        fun from(bundle: Bundle): ReceiptProcessingInfo? {
            bundle.classLoader = ReceiptProcessingInfo::class.java.classLoader
            return bundle.getParcelable(KEY_RECEIPT_PROCESSING_INFO)
        }
    }
}
