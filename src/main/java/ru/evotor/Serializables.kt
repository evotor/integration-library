package ru.evotor

import android.os.Parcel
import java.io.Serializable

fun readSerializable(parcel: Parcel): Serializable? {
    return parcel.readSerializable()
}
