package ru.evotor.framework.component

import android.os.Parcel
import android.os.Parcelable

/**
 * Компонент интеграционного приложения, данные из манифеста
 *
 * @property packageName Название пакета
 * @property componentName Название компонента (сервис, активити и т.п.)
 * @property appUuid Уникальный идентификатора приложения в системе Эвотора
 * @property appName Название приложения
 */
open class IntegrationComponent(
        val packageName: String?,
        val componentName: String?,
        val appUuid: String?,
        val appName: String?
) : Parcelable {
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