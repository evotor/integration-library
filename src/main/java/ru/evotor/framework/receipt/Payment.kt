package ru.evotor.framework.receipt

import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.payment.PaymentSystem
import java.math.BigDecimal

/**
 * Оплата
 */
data class Payment(
        /**
         * Uuid
         */
        val uuid: String,
        /**
         * Сумма денег принятых от клиента
         */
        val value: BigDecimal,
        /**
         * Платежная система
         */
        val system: PaymentSystem?,
        /**
         * Идентификатор цели платежа
         */
        val purposeIdentifier: String?,
        /**
         * Идентификатор аккаунта
         */
        val accountId: String?,
        /**
         * Описание аккаунта
         */
        val accountUserDescription: String?


): Parcelable {
    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(uuid)
        parcel.writeSerializable(value)
        parcel.writeParcelable(system, 0)
        parcel.writeString(purposeIdentifier)
        parcel.writeString(accountId)
        parcel.writeString(accountUserDescription)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Payment> = object : Parcelable.Creator<Payment> {
            override fun createFromParcel(parcel: Parcel): Payment {
                val uuid = parcel.readString()
                val value = parcel.readSerializable() as BigDecimal
                val system = parcel.readParcelable<PaymentSystem>(PaymentSystem::class.java.classLoader)
                val purposeIdentifier = parcel.readString()
                val accountId = parcel.readString()
                val accountUserDescription = parcel.readString()
                return Payment(uuid, value, system, purposeIdentifier, accountId, accountUserDescription)
            }

            override fun newArray(size: Int): Array<Payment?> = arrayOfNulls(size)
        }
    }

    override fun describeContents() = 0

    override fun toString(): String {
        return "Payment(uuid='$uuid', value=$value, system=$system, purposeIdentifier=$purposeIdentifier, accountId=$accountId, accountUserDescription=$accountUserDescription)"
    }
}