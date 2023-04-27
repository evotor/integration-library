package ru.evotor.framework.core.action.event.receipt.payment.combined.event

import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.ParcelableUtils
import ru.evotor.framework.component.PaymentPerformer
import java.math.BigDecimal

class PaymentDelegatorPaybackData(
    val performer: PaymentPerformer?,
    val sum: BigDecimal?
) : Parcelable {
    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            parcel.writeParcelable(performer, flags)
            parcel.writeSerializable(sum)
        }
    }

    companion object {
        private const val VERSION = 1

        @JvmField
        val CREATOR: Parcelable.Creator<PaymentDelegatorPaybackData> = object : Parcelable.Creator<PaymentDelegatorPaybackData> {
            override fun createFromParcel(parcel: Parcel): PaymentDelegatorPaybackData {
                return create(parcel)
            }

            override fun newArray(size: Int): Array<PaymentDelegatorPaybackData?> {
                return arrayOfNulls(size)
            }
        }

        private fun create(source: Parcel): PaymentDelegatorPaybackData {
            var data: PaymentDelegatorPaybackData? = null

            ParcelableUtils.readExpand(source, VERSION) { parcel, version ->
                var performer: PaymentPerformer? = null
                var sum: BigDecimal? = null

                if (version >= 1) {
                    performer = parcel.readParcelable(PaymentPerformer::class.java.classLoader)
                    sum = parcel.readSerializable() as BigDecimal
                }

                data = PaymentDelegatorPaybackData(performer, sum)
            }

            checkNotNull(data)
            return data as PaymentDelegatorPaybackData
        }
    }
}
