package ru.evotor

import android.os.Parcel
import android.os.Parcelable

inline fun <reified T : Parcelable> Parcel.readParcelable(): T? {
    return readParcelable(this, T::class.java)
}

fun <T : Parcelable> readParcelable(parcel: Parcel, clazzT: Class<T>): T? {
    val dataPosition = parcel.dataPosition()
    return try {
        parcel.readParcelable(clazzT.classLoader)
    } catch (t: Throwable) {
        parcel.setDataPosition(dataPosition)
        parcel.readValue(clazzT.classLoader) as? T
    }
}

fun Parcel.writeAliased(p: Parcelable?, flags: Int) {
    writeParcelable(p, flags)
}

fun <T : Parcelable> Parcel.readAliased(creator: Parcelable.Creator<T?>): T? {
    val ignoredClass = readString() ?: return null
    println("read class $ignoredClass, but ignore this and read with $creator")
    return creator.createFromParcel(this)
}

fun <T : Parcelable> Parcel.writeAliasedArray(value: Array<T>?, flags: Int) {
    writeParcelableArray(value, flags)
}

fun Parcel.readParcelableArray(clazz: Class<out Parcelable>): Array<Parcelable?>? {
    return readParcelableArray(clazz.classLoader)
}
