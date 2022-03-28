package ru.evotor.framework.core.action.event.receipt.changes.receipt

import android.os.Bundle
import androidx.annotation.RequiresPermission
import ru.evotor.framework.core.action.event.receipt.changes.IChange

/**
 * Добавляет в чек контактные данные клиента, на которые будет отправлен электронный чек.
 * Чек будет отправлен либо на email, либо на номер телефона
 */
class SetPurchaserContactData private constructor(
    val email: String?,
    val phone: String?
) : IChange {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_EMAIL, email)
            putString(KEY_PHONE, phone)
        }
    }

    override fun getType(): IChange.Type {
        return IChange.Type.SET_PURCHASER_CONTACT_DATA
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SetPurchaserContactData

        if (email != other.email) return false
        if (phone != other.phone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email?.hashCode() ?: 0
        result = 31 * result + (phone?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "SetPurchaserContactData(email=$email, phone=$phone)"
    }

    companion object {
        @JvmStatic
        @RequiresPermission(SET_PURCHASER_CONTACT_DATA_PERMISSION)
        fun createForEmail(email: String?): SetPurchaserContactData {
            return SetPurchaserContactData(email = email, phone = null)
        }

        @JvmStatic
        @RequiresPermission(SET_PURCHASER_CONTACT_DATA_PERMISSION)
        fun createForPhone(phone: String?): SetPurchaserContactData {
            return SetPurchaserContactData(email = null, phone = phone)
        }

        @JvmStatic
        @RequiresPermission(SET_PURCHASER_CONTACT_DATA_PERMISSION)
        fun createForClearContactData(): SetPurchaserContactData {
            return SetPurchaserContactData(email = null, phone = null)
        }

        @Suppress("MemberVisibilityCanBePrivate")
        const val SET_PURCHASER_CONTACT_DATA_PERMISSION =
            "ru.evotor.permission.SET_PURCHASER_CONTACT_DATA"

        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"

        @JvmStatic
        fun from(bundle: Bundle?): SetPurchaserContactData? {
            bundle ?: return null

            return SetPurchaserContactData(
                email = bundle.getString(KEY_EMAIL),
                phone = bundle.getString(KEY_PHONE)
            )
        }
    }
}
