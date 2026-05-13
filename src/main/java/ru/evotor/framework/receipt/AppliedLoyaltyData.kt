package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.ParcelableUtils

/**
 * Информация о примененной лояльности к чеку
 *
 * @param loyaltyCardId Внутренний идентификатор карты лояльности (номер карты лояльности, серийный номер, etc.).
 *
 * @param externalLoyaltyCardId Внешний идентификатор карты лояльности
 *
 * @param additionalData JSON, содержащий ополнительные данные о приложении лоляьности
 * В его состав должены входить параметры:
 *  @param KEY_LOYALTY_APP_ID - appUuid приложения, применившего карту лояльности
 *  @param KEY_LOYALTY_SERVICE_PACKAGE - packageName сервиса, применившего лояльность
 *  @param KEY_LOYALTY_SERVICE_CLASS - className сервиса, применившего лояльность
 *  Опционально:
 *  @param KEY_LOYALTY_EARNED_BONUSES - количество начисленных бонусов
 *  @param KEY_LOYALTY_SPENT_BONUSES - количество списанных бонусов
 */
data class AppliedLoyaltyData(
    val loyaltyCardId: String?,
    val externalLoyaltyCardId: String?,
    val additionalData: String?
) : Parcelable, IBundlable {
    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flag: Int) {
        ParcelableUtils.writeExpand(dest, VERSION) { parcel ->
            parcel.writeString(loyaltyCardId)
            parcel.writeString(externalLoyaltyCardId)
            parcel.writeString(additionalData)
        }
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_LOYALTY_CARD_ID, loyaltyCardId)
            putString(KEY_EXTERNAL_LOYALTY_CARD_ID, externalLoyaltyCardId)
            putString(KEY_ADDITIONAL_DATA, additionalData)
        }
    }

    companion object {
        private const val VERSION = 1

        private const val KEY_LOYALTY_APP_ID = "loyaltyAppId"
        private const val KEY_LOYALTY_SERVICE_PACKAGE = "loyaltyServicePackage"
        private const val KEY_LOYALTY_SERVICE_CLASS = "loyaltyServiceClass"
        private const val KEY_LOYALTY_EARNED_BONUSES = "earnedBonusAmount"
        private const val KEY_LOYALTY_SPENT_BONUSES = "spentBonusAmount"
        private const val KEY_LOYALTY_CARD_ID = "loyaltyCardId"
        private const val KEY_EXTERNAL_LOYALTY_CARD_ID = "externalLoyaltyCardId"
        private const val KEY_ADDITIONAL_DATA = "additionalData"

        val CLEAR = AppliedLoyaltyData(null, null, null)

        @JvmField
        val CREATOR = object : Parcelable.Creator<AppliedLoyaltyData> {
            override fun createFromParcel(parcel: Parcel): AppliedLoyaltyData? = create(parcel)

            override fun newArray(size: Int): Array<AppliedLoyaltyData?> = arrayOfNulls(size)
        }

        private fun create(dest: Parcel): AppliedLoyaltyData? {
            var appliedLoyaltyData: AppliedLoyaltyData? = null
            ParcelableUtils.readExpand(dest, VERSION) { parcel, version ->
                if (version >= 1) {
                    val loyaltyAppId = parcel.readString()
                    val packageName = parcel.readString()
                    val className = parcel.readString()
                    if (loyaltyAppId != null && packageName != null && className != null) {
                        val loyaltyCardId = parcel.readString()
                        val externalLoyaltyCardId = parcel.readString()
                        val additionalData = parcel.readString()
                        appliedLoyaltyData = AppliedLoyaltyData(
                            loyaltyCardId = loyaltyCardId,
                            externalLoyaltyCardId = externalLoyaltyCardId,
                            additionalData = additionalData
                        )
                    }
                }
            }
            return appliedLoyaltyData
        }

        @JvmStatic
        fun from(bundle: Bundle?): AppliedLoyaltyData? = bundle?.let {
            return AppliedLoyaltyData(
                loyaltyCardId = it.getString(KEY_LOYALTY_CARD_ID),
                externalLoyaltyCardId = it.getString(KEY_EXTERNAL_LOYALTY_CARD_ID),
                additionalData = it.getString(KEY_ADDITIONAL_DATA)
            )
        }
    }
}
