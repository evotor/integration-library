package ru.evotor.framework.component

import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.payment.PaymentSystem

/**
 * Компонент интеграционного приложения, осуществляющий оплату
 *
 * @property paymentSystem Платежная система
 * @property packageName Название пакета
 * @property componentName Название компонента (сервис, активити и т.п.)
 * @property appUuid Уникальный идентификатора приложения в системе Эвотора
 * @property appName Название приложения
 */
class PaymentPerformer(val paymentSystem: PaymentSystem?,
                       packageName: String?,
                       componentName: String?,
                       appUuid: String?,
                       appName: String?
) : IntegrationComponent(packageName, componentName, appUuid, appName), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(PaymentSystem::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

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
        parcel.writeParcelable(paymentSystem, flags)
        parcel.writeString(packageName)
        parcel.writeString(componentName)
        parcel.writeString(appUuid)
        parcel.writeString(appName)

        // Go back and write the size
        val parcelableSize = parcel.dataPosition() - startPosition
        parcel.setDataPosition(sizePosition)
        parcel.writeInt(parcelableSize)
        parcel.setDataPosition(startPosition + parcelableSize)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        /**
         * Since there might be a case where new versions of the client using the library getting
         * old versions of the protocol (and thus old versions of this class), we need a versioning
         * system for the parcels sent between the clients and the providers.
         */
        const val PARCELABLE_VERSION = 1

        @JvmField
        val CREATOR: Parcelable.Creator<PaymentPerformer> = object : Parcelable.Creator<PaymentPerformer> {
            override fun createFromParcel(parcel: Parcel): PaymentPerformer {
                val parcelableVersion = parcel.readInt()
                val parcelableSize = parcel.readInt()
                val startPosition = parcel.dataPosition()

                val performer = PaymentPerformer(parcel)

                // skip over all fields added in future versions of this parcel
                parcel.setDataPosition(startPosition + parcelableSize)

                return performer
            }

            override fun newArray(size: Int): Array<PaymentPerformer?> {
                return arrayOfNulls(size)
            }
        }
    }
}