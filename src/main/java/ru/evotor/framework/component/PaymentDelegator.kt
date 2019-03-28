package ru.evotor.framework.component

import android.os.Parcel
import android.os.Parcelable

/**
 * Компонент интеграционного приложения, осуществляющий делегирование платежей другим приложениям
 * a.k.a. приложение комбооплаты
 *
 * @property packageName Название пакета
 * @property componentName Название компонента (сервис, активити и т.п.)
 * @property appUuid Уникальный идентификатора приложения в системе Эвотора
 * @property appName Название приложения
 */
class PaymentDelegator(packageName: String?,
                       componentName: String?,
                       appUuid: String?,
                       appName: String?
) : IntegrationComponent(packageName, componentName, appUuid, appName), Parcelable {
    constructor(parcel: Parcel) : this(
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
        val CREATOR: Parcelable.Creator<PaymentDelegator> = object : Parcelable.Creator<PaymentDelegator> {
            override fun createFromParcel(parcel: Parcel): PaymentDelegator {
                val parcelableVersion = parcel.readInt()
                val parcelableSize = parcel.readInt()
                val startPosition = parcel.dataPosition()

                val delegator = PaymentDelegator(parcel)

                // skip over all fields added in future versions of this parcel
                parcel.setDataPosition(startPosition + parcelableSize)

                return delegator
            }

            override fun newArray(size: Int): Array<PaymentDelegator?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as PaymentDelegator

        if (packageName != other.packageName) return false
        if (componentName != other.componentName) return false
        if (appUuid != other.appUuid) return false
        if (appName != other.appName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = packageName?.hashCode() ?: 0
        result = 31 * result + (componentName?.hashCode() ?: 0)
        result = 31 * result + (appUuid?.hashCode() ?: 0)
        result = 31 * result + (appName?.hashCode() ?: 0)
        return result
    }
}