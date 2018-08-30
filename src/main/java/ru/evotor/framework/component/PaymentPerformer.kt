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
        parcel.writeParcelable(paymentSystem, flags)
        parcel.writeString(packageName)
        parcel.writeString(componentName)
        parcel.writeString(appUuid)
        parcel.writeString(appName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PaymentPerformer> = object : Parcelable.Creator<PaymentPerformer> {
            override fun createFromParcel(parcel: Parcel): PaymentPerformer {
                return PaymentPerformer(parcel)
            }

            override fun newArray(size: Int): Array<PaymentPerformer?> {
                return arrayOfNulls(size)
            }
        }
    }
}