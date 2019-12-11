package ru.evotor.framework.receipt.position

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

/**
 * Признак способа расчёта, который записывается в тег 1214 фискального документа.
 *
 * Признак способа расчёта необходимо указывать для каждой позиции чека.
 */
sealed class SettlementMethod : Parcelable {

    protected abstract fun writeFieldsToParcel(dest: Parcel, flags: Int)

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
     * Предоплата 100% – полная предварительная оплата до момента передачи предмета расчёта.
     */
    class FullPrepayment() : SettlementMethod() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FullPrepayment) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): FullPrepayment {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return FullPrepayment()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<FullPrepayment> = object : Parcelable.Creator<FullPrepayment> {
                override fun createFromParcel(parcel: Parcel): FullPrepayment = readFromParcel(parcel)

                override fun newArray(size: Int): Array<FullPrepayment?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Предоплата – частичная предварительная оплата до момента передачи предмета расчёта.
     */
    class PartialPrepayment() : SettlementMethod() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is PartialPrepayment) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): PartialPrepayment {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return PartialPrepayment()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<PartialPrepayment> = object : Parcelable.Creator<PartialPrepayment> {
                override fun createFromParcel(parcel: Parcel): PartialPrepayment = readFromParcel(parcel)

                override fun newArray(size: Int): Array<PartialPrepayment?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Аванс.
     */
    class AdvancePayment() : SettlementMethod() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is AdvancePayment) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): AdvancePayment {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return AdvancePayment()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<AdvancePayment> = object : Parcelable.Creator<AdvancePayment> {
                override fun createFromParcel(parcel: Parcel): AdvancePayment = readFromParcel(parcel)

                override fun newArray(size: Int): Array<AdvancePayment?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Полный расчёт – полная оплата, в том числе с учётом аванса (предварительной оплаты) в момент передачи предмета расчёта.
     */
    class FullSettlement() : SettlementMethod() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FullSettlement) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): FullSettlement {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return FullSettlement()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<FullSettlement> = object : Parcelable.Creator<FullSettlement> {
                override fun createFromParcel(parcel: Parcel): FullSettlement = readFromParcel(parcel)

                override fun newArray(size: Int): Array<FullSettlement?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Частичный расчёт и кредит – частичная оплата предмета расчёта в момент его передачи с последующей оплатой в кредит.
     *
     * @property amount Сумма первичного взноса.
     */
    class PartialSettlement(val amount: BigDecimal) : SettlementMethod() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {
            dest.writeString(amount.toPlainString())
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is PartialSettlement) return false

            if (amount != other.amount) return false

            return true
        }

        override fun hashCode(): Int {
            return amount.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): PartialSettlement {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                val amount = BigDecimal(parcel.readString())
                parcel.setDataPosition(dataStartPosition + dataSize)
                return PartialSettlement(amount)
            }

            @JvmField
            val CREATOR: Parcelable.Creator<PartialSettlement> = object : Parcelable.Creator<PartialSettlement> {
                override fun createFromParcel(parcel: Parcel): PartialSettlement = readFromParcel(parcel)

                override fun newArray(size: Int): Array<PartialSettlement?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Передача в кредит – передача предмета расчёта без его оплаты в момент его передачи с последующей оплатой в кредит.
     */
    class Lend() : SettlementMethod() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Lend) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): Lend {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return Lend()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<Lend> = object : Parcelable.Creator<Lend> {
                override fun createFromParcel(parcel: Parcel): Lend = readFromParcel(parcel)

                override fun newArray(size: Int): Array<Lend?> = arrayOfNulls(size)
            }
        }
    }

    /**
     * Оплата кредита – оплата предмета расчёта после его передачи с оплатой в кредит.
     */
    class LoanPayment() : SettlementMethod() {

        override fun writeFieldsToParcel(dest: Parcel, flags: Int) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(VERSION)
            super.writeToParcel(parcel, flags)
        }

        override fun describeContents(): Int = 0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is LoanPayment) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        companion object {
            private const val VERSION = 1

            @JvmStatic
            private fun readFromParcel(parcel: Parcel): LoanPayment {
                val version = parcel.readInt()
                val dataSize = parcel.readInt()
                val dataStartPosition = parcel.dataPosition()
                // read fields here
                parcel.setDataPosition(dataStartPosition + dataSize)
                return LoanPayment()
            }

            @JvmField
            val CREATOR: Parcelable.Creator<LoanPayment> = object : Parcelable.Creator<LoanPayment> {
                override fun createFromParcel(parcel: Parcel): LoanPayment = readFromParcel(parcel)

                override fun newArray(size: Int): Array<LoanPayment?> = arrayOfNulls(size)
            }
        }
    }

}