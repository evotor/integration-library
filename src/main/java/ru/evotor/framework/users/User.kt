package ru.evotor.framework.users

import android.os.Parcel
import android.os.Parcelable

/**
 * Пользователь
 */
data class User(
        val uuid: String,
        val secondName: String?,
        val firstName: String?,
        val phone: String?,
        val pin: String?,
        val roleUuid: String,
        val roleTitle: String
) : Parcelable {

    fun getFullName(): String {
        val stringBuilder = StringBuilder()
        firstName?.let {
            stringBuilder.append(it).append(" ")
        }
        secondName?.let {
            stringBuilder.append(it).append(" ")
        }
        return stringBuilder.toString().trim();
    }

    fun getCashierName() = getFullName().take(64)

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(uuid)
        dest.writeString(secondName)
        dest.writeString(firstName)
        dest.writeString(phone)
        dest.writeString(pin)
        dest.writeString(roleUuid)
        dest.writeString(roleTitle)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(parcel: Parcel): User {
                val uuid = parcel.readString()
                val secondName = parcel.readString()
                val firstName = parcel.readString()
                val phone = parcel.readString()
                val pin = parcel.readString()
                val roleUuid = parcel.readString()
                val roleTitle = parcel.readString()
                return User(uuid, secondName, firstName, phone, pin, roleUuid, roleTitle)
            }

            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    override fun describeContents() = 0
}