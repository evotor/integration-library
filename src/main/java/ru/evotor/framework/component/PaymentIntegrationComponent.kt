package ru.evotor.framework.component

import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.payment.PaymentSystem

class PaymentIntegrationComponent : IntegrationComponent, Parcelable {
    var paymentSystem: PaymentSystem? = null

    constructor(paymentSystem: PaymentSystem?,
                packageName: String?,
                componentName: String?,
                appUuid: String?,
                appName: String?
    ) : super(packageName, componentName, appUuid, appName) {
        this.paymentSystem = paymentSystem
    }

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(PaymentSystem::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
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
        val CREATOR: Parcelable.Creator<PaymentIntegrationComponent> = object : Parcelable.Creator<PaymentIntegrationComponent> {
            override fun createFromParcel(parcel: Parcel): PaymentIntegrationComponent {
                return PaymentIntegrationComponent(parcel)
            }

            override fun newArray(size: Int): Array<PaymentIntegrationComponent?> {
                return arrayOfNulls(size)
            }
        }
    }
}