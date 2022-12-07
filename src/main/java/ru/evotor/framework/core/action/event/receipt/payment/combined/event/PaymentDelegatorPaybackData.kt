package ru.evotor.framework.core.action.event.receipt.payment.combined.event

import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.component.PaymentPerformer
import java.math.BigDecimal

class PaymentDelegatorPaybackData(
    val performer: PaymentPerformer?,
    val sum: BigDecimal?
) : Parcelable {
    constructor(parcel: Parcel): this(
        performer = parcel.readParcelable(PaymentPerformer::class.java.classLoader),
        sum = parcel.readSerializable() as BigDecimal
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(performer, flags)
        dest?.writeSerializable(sum)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PaymentDelegatorPaybackData> = object : Parcelable.Creator<PaymentDelegatorPaybackData> {
            override fun createFromParcel(parcel: Parcel): PaymentDelegatorPaybackData {
                return PaymentDelegatorPaybackData(parcel)
            }

            override fun newArray(size: Int): Array<PaymentDelegatorPaybackData?> {
                return arrayOfNulls(size)
            }
        }
    }
}
