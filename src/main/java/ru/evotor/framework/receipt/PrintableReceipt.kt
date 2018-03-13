package ru.evotor.framework.receipt

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.core.action.event.receipt.changes.receipt.print_extra.SetPrintExtra
import ru.evotor.framework.users.User
import java.io.Serializable
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

/**
 * Created by ivan on 27.02.2018.
 *
 * This class is used in DTO to pass data via AIDL
 */

data class PrintableReceipt(
        val user: User,
        val receipt: Receipt.PrintReceipt,
        val positionUuidTaxes: Map<String, Tax>,
        val printExtra: Map<String, List<SetPrintExtra>>,
        val clientPhone: String = "",
        val clientEmail: String = ""
) : Parcelable {

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeParcelable(user, 0)

        parcel.writeParcelable(receipt, 0)

        parcel.writeInt(positionUuidTaxes.size)
        for ((key, value) in positionUuidTaxes) {
            parcel.writeString(key)
            parcel.writeParcelable(value, 0)
        }
        parcel.writeInt(printExtra.size)

        for ((key, value) in printExtra) {
            val setPrintExtraBundles = ArrayList<Bundle>(value.size)
            value.mapTo(setPrintExtraBundles) { it.toBundle() }

            parcel.writeString(key)
            parcel.writeSerializable(setPrintExtraBundles as Serializable)
        }

        parcel.writeString(clientPhone)
        parcel.writeString(clientEmail)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<PrintableReceipt> = object : Parcelable.Creator<PrintableReceipt> {
            override fun createFromParcel(parcel: Parcel): PrintableReceipt {
                val user = parcel.readParcelable<User>(User::class.java.classLoader)

                val receipt = parcel.readParcelable<Receipt.PrintReceipt>(Receipt.PrintReceipt::class.java.classLoader)

                val positionUuidTaxesSize = parcel.readInt()
                val positionUuidTaxes = HashMap<String, Tax>(positionUuidTaxesSize)
                for (i in 0 until positionUuidTaxesSize) {
                    positionUuidTaxes.put(parcel.readString(), parcel.readParcelable(Tax::class.java.classLoader) as Tax)
                }

                val printExtraSize = parcel.readInt()
                val printExtra = HashMap<String, List<SetPrintExtra>>(printExtraSize)
                for (i in 0 until printExtraSize) {
                    val key = parcel.readString()
                    val setPrintExtraBundles = parcel.readSerializable() as List<Bundle>
                    val setPrintExtras = ArrayList<SetPrintExtra>()
                    for (bundle in setPrintExtraBundles) {
                        SetPrintExtra.from(bundle)?.let { setPrintExtras.add(it) }
                    }
                    printExtra[key] = setPrintExtras
                }

                val phone = parcel.readString()
                val email = parcel.readString()

                return PrintableReceipt(user, receipt, positionUuidTaxes, printExtra, phone, email)
            }

            override fun newArray(size: Int): Array<PrintableReceipt?> = arrayOfNulls(size)
        }
    }

    override fun describeContents() = 0
}