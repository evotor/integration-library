package ru.evotor.framework.payment

import android.os.Parcel
import android.os.Parcelable

/**
 * Признак способа расчета для позиции
 */
sealed class PaymentFeature : Parcelable {

    abstract fun writeFieldsToParcel(dest: Parcel, flags: Int)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // Determine position in parcel for writing data size
        val dataSizePosition = parcel.dataPosition()
        // Use integer placeholder for data size
        parcel.writeInt(0)
        //Determine position of data start
        val startDataPosition = parcel.dataPosition()

        writeFieldsToParcel(parcel, flags)

        // Calculate data size
        val dataSize = parcel.dataPosition() - startDataPosition
        // Save position at the end of data
        val endOfDataPosition = parcel.dataPosition()
        //Set position to start to write additional data size
        parcel.setDataPosition(dataSizePosition)
        parcel.writeInt(dataSize)
        // Go back to the end of parcel
        parcel.setDataPosition(endOfDataPosition)
    }

    /**
     * Полная предварительная оплата до момента передачи предмета расчета
     */
    class PrepaymentFull() : PaymentFeature() {

        constructor(parcel: Parcel) : this()

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): PrepaymentFull {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return PrepaymentFull()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<PrepaymentFull> = object : Parcelable.Creator<PrepaymentFull> {
                override fun createFromParcel(parcel: Parcel): PrepaymentFull = readFromParcel(parcel)

                override fun newArray(size: Int): Array<PrepaymentFull?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Частичная предварительная оплата до момента передачи предмета расчета
     */
    class PrepaymentPartial() : PaymentFeature() {

        constructor(parcel: Parcel) : this()

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): PrepaymentPartial {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return PrepaymentPartial()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<PrepaymentPartial> = object : Parcelable.Creator<PrepaymentPartial> {
                override fun createFromParcel(parcel: Parcel): PrepaymentPartial = readFromParcel(parcel)

                override fun newArray(size: Int): Array<PrepaymentPartial?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Аванс
     */
    class Advance() : PaymentFeature() {

        constructor(parcel: Parcel) : this()

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): Advance {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return Advance()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<Advance> = object : Parcelable.Creator<Advance> {
                override fun createFromParcel(parcel: Parcel): Advance = readFromParcel(parcel)

                override fun newArray(size: Int): Array<Advance?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Полная оплата, в том числе с учетом аванса (предварительной оплаты) в момент передачи предмета расчета
     */
    class CheckoutFull() : PaymentFeature() {

        constructor(parcel: Parcel) : this()

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): CheckoutFull {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return CheckoutFull()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<CheckoutFull> = object : Parcelable.Creator<CheckoutFull> {
                override fun createFromParcel(parcel: Parcel): CheckoutFull = readFromParcel(parcel)

                override fun newArray(size: Int): Array<CheckoutFull?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Частичная оплата предмета расчета в момент его передачи с последующей оплатой в кредит
     */
    class CheckoutPartial() : PaymentFeature() {

        constructor(parcel: Parcel) : this()

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): CheckoutPartial {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return CheckoutPartial()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<CheckoutPartial> = object : Parcelable.Creator<CheckoutPartial> {
                override fun createFromParcel(parcel: Parcel): CheckoutPartial = readFromParcel(parcel)

                override fun newArray(size: Int): Array<CheckoutPartial?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Передача предмета расчета без его оплаты в момент его передачи с последующей оплатой в кредит
     */
    class CreditPass() : PaymentFeature() {

        constructor(parcel: Parcel) : this()

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): CreditPass {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return CreditPass()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<CreditPass> = object : Parcelable.Creator<CreditPass> {
                override fun createFromParcel(parcel: Parcel): CreditPass = readFromParcel(parcel)

                override fun newArray(size: Int): Array<CreditPass?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Оплата предмета расчета после его передачи с оплатой в кредит (оплата кредита)
     */
    class CreditCheckout() : PaymentFeature() {

        constructor(parcel: Parcel) : this()

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): CreditCheckout {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return CreditCheckout()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<CreditCheckout> = object : Parcelable.Creator<CreditCheckout> {
                override fun createFromParcel(parcel: Parcel): CreditCheckout = readFromParcel(parcel)

                override fun newArray(size: Int): Array<CreditCheckout?> = arrayOfNulls(size)
            }
        }
    }
}
