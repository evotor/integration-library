package ru.evotor.framework.component

import android.os.Parcel
import android.os.Parcelable

open class IntegrationComponent : Parcelable {
    var packageName: String? = null
    var componentName: String? = null
    var appUuid: String? = null
    var appName: String? = null

    constructor(
            packageName: String?,
            componentName: String?,
            appUuid: String?,
            appName: String?
    ) {
        this.packageName = packageName
        this.componentName = componentName
        this.appUuid = appUuid
        this.appName = appName
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
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
        val CREATOR: Parcelable.Creator<IntegrationComponent> = object : Parcelable.Creator<IntegrationComponent> {
            override fun createFromParcel(parcel: Parcel): IntegrationComponent {
                return IntegrationComponent(parcel)
            }

            override fun newArray(size: Int): Array<IntegrationComponent?> {
                return arrayOfNulls(size)
            }
        }
    }
}