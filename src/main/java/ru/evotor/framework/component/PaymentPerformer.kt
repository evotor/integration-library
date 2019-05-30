package ru.evotor.framework.component

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.datamapper.PaymentPerformerMapper
import ru.evotor.framework.payment.PaymentSystem

/**
 * Компонент (служба, операция и т.д.) интеграционного приложения, осуществляющий оплату
 *
 * @param paymentSystem Платежная система
 * @param packageName Название пакета
 * @param componentName Название компонента (служба, операция и т.д.)
 * @param appUuid Уникальный идентификатора приложения в Облаке Эвотор
 * @param appName Название приложения
 */
class PaymentPerformer(val paymentSystem: PaymentSystem?,
                       packageName: String?,
                       componentName: String?,
                       appUuid: String?,
                       appName: String?
) : IntegrationComponent(packageName, componentName, appUuid, appName), Parcelable, IBundlable {
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

        fun from(bundle: Bundle?) = PaymentPerformerMapper.fromBundle(bundle)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as PaymentPerformer

        if (paymentSystem != other.paymentSystem) return false
        if (packageName != other.packageName) return false
        if (componentName != other.componentName) return false
        if (appUuid != other.appUuid) return false
        if (appName != other.appName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = paymentSystem?.hashCode() ?: 0
        result = 31 * result + (packageName?.hashCode() ?: 0)
        result = 31 * result + (componentName?.hashCode() ?: 0)
        result = 31 * result + (appUuid?.hashCode() ?: 0)
        result = 31 * result + (appName?.hashCode() ?: 0)
        return result
    }

    override fun toBundle(): Bundle = PaymentPerformerMapper.toBundle(this)
}
