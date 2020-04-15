package ru.evotor.framework.component

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.datamapper.IntegrationComponentMapper

/**
 * Компонент интеграционного приложения, данные из манифеста
 *
 * @property packageName Название пакета
 * @property componentName Название компонента (сервис, активити и т.п.)
 * @property appUuid Уникальный идентификатор приложения в Облаке Эвотор
 * @property appName Название приложения
 */
open class IntegrationComponent(
        val packageName: String?,
        val componentName: String?,
        val appUuid: String?,
        val appName: String?
) : Parcelable, IBundlable {
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
        val CREATOR: Parcelable.Creator<IntegrationComponent> = object : Parcelable.Creator<IntegrationComponent> {
            override fun createFromParcel(parcel: Parcel): IntegrationComponent {
                val parcelableVersion = parcel.readInt()
                val parcelableSize = parcel.readInt()
                val startPosition = parcel.dataPosition()

                val component = IntegrationComponent(parcel)

                // skip over all fields added in future versions of this parcel
                parcel.setDataPosition(startPosition + parcelableSize)

                return component
            }

            override fun newArray(size: Int): Array<IntegrationComponent?> {
                return arrayOfNulls(size)
            }
        }

        fun from(bundle: Bundle?) = IntegrationComponentMapper.fromBundle(bundle)
    }

    override fun toBundle(): Bundle = IntegrationComponentMapper.toBundle(this)
}