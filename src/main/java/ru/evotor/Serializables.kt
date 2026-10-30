package ru.evotor

import android.os.Parcel
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream
import java.io.ObjectStreamClass
import java.io.Serializable
import kotlin.text.startsWith
import kotlin.text.substringAfter

fun readSerializable(parcel: Parcel): Serializable? {
    return parcel.readSerializable()
}
