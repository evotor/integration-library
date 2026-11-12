package ru.evotor.framework.receipt.status.api.ensure_receipt_processing

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.Receipt
import kotlin.collections.get

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
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        Receipt.Status.values()[parcel.readInt()]
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        /**
         * NOTE: When adding fields in the process of updating this API, make sure to bump
         * {@link #PARCELABLE_VERSION}.
         */
        parcel.writeInt(PARCELABLE_VERSION)
        // Inject a placeholder that will store the parcel size from this point on
        // (not including the size itself).
        val sizePosition = parcel.dataPosition()
        parcel.writeInt(0)
        val startPosition = parcel.dataPosition()

        // version 1
        parcel.writeString(receiptUuid)
        parcel.writeInt(status.ordinal)

        // Go back and write the size
        val parcelableSize = parcel.dataPosition() - startPosition
        parcel.setDataPosition(sizePosition)
        parcel.writeInt(parcelableSize)
        parcel.setDataPosition(startPosition + parcelableSize)
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
                val parcelableVersion = parcel.readInt()
                val parcelableSize = parcel.readInt()
                val startPosition = parcel.dataPosition()

                val info = ReceiptProcessingInfo(parcel)

                // skip over all fields added in future versions of this parcel
                parcel.setDataPosition(startPosition + parcelableSize)

                return info
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
