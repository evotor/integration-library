package ru.evotor.framework.receipt.position

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

/**
 * Способ расчета
 */
sealed class SettlementMethod : Parcelable {

    companion object {
        enum class Type {
            /**
             * Полная предварительная оплата до момента передачи предмета расчета
             */
            PREPAYMENT_FULL,
            /**
             * Частичная предварительная оплата до момента передачи предмета расчета
             */
            PREPAYMENT_PARTIAL,
            /**
             * Аванс
             */
            ADVANCE,
            /**
             * Полная оплата, в том числе с учетом аванса (предварительной оплаты) в момент передачи предмета расчета
             */
            CHECKOUT_FULL,
            /**
             * Частичная оплата предмета расчета в момент его передачи с последующей оплатой в кредит
             */
            CHECKOUT_PARTIAL,
            /**
             * Передача предмета расчета без его оплаты в момент его передачи с последующей оплатой в кредит
             */
            CREDIT_PASS,
            /**
             * Оплата предмета расчета после его передачи с оплатой в кредит (оплата кредита)
             */
            CREDIT_CHECKOUT
        }
        @JvmStatic
        @JvmOverloads
        fun fromType(type: Type, amount: BigDecimal? = null): SettlementMethod = when (type) {
            SettlementMethod.Companion.Type.PREPAYMENT_FULL -> FullPrepayment()
            SettlementMethod.Companion.Type.PREPAYMENT_PARTIAL -> PartialPrepayment()
            SettlementMethod.Companion.Type.ADVANCE -> AdvancePayment()
            SettlementMethod.Companion.Type.CHECKOUT_FULL -> FullSettlement()
            SettlementMethod.Companion.Type.CHECKOUT_PARTIAL -> PartialSettlement(amount!!)
            SettlementMethod.Companion.Type.CREDIT_PASS -> Lend()
            SettlementMethod.Companion.Type.CREDIT_CHECKOUT -> LoanPayment()
        }
        @JvmStatic
        @JvmOverloads
        fun fromInt(typeOrdinal: Int, amount: BigDecimal? = null): SettlementMethod {
            val type = Type.values()[typeOrdinal]
            return fromType(type, amount)
        }
    }
    fun toInt(): Int = when (this) {
        is SettlementMethod.FullPrepayment -> Type.PREPAYMENT_FULL.ordinal
        is SettlementMethod.PartialPrepayment -> Type.PREPAYMENT_PARTIAL.ordinal
        is SettlementMethod.AdvancePayment -> Type.ADVANCE.ordinal
        is SettlementMethod.FullSettlement -> Type.CHECKOUT_FULL.ordinal
        is SettlementMethod.PartialSettlement -> Type.CHECKOUT_PARTIAL.ordinal
        is SettlementMethod.Lend -> Type.CREDIT_PASS.ordinal
        is SettlementMethod.LoanPayment -> Type.CREDIT_CHECKOUT.ordinal
    }


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
     * Полная предварительная оплата до момента передачи предмета расчета
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
     * Частичная предварительная оплата до момента передачи предмета расчета
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
     * Аванс
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
     * Полная оплата, в том числе с учетом аванса (предварительной оплаты) в момент передачи
     * предмета расчета (полный расчёт)
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
     * Частичная оплата предмета расчета в момент его передачи с последующей оплатой в кредит
     * (частичный расчёт и кредит)
     * @param amount сумма первичного взноса
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
     * Передача предмета расчета без его оплаты в момент его передачи с последующей оплатой в кредит
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
     * Оплата предмета расчета после его передачи с оплатой в кредит (оплата кредита)
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