package ru.evotor.framework.receipt

import android.os.Parcel
import android.os.Parcelable

import java.math.BigDecimal

class ReceiptResult : Result {

    var receiptNumber: Int = 0
    var documentNumber: Int = 0
    var sessionNumber: Int = 0
    var checkSum: BigDecimal = BigDecimal.ZERO
    var receiptDay: Int = 0
    var receiptMonth: Int = 0
    var receiptYear: Int = 0

    /**
     * ФН - Заводской номер фискального накопителя
     */
    var fnSerialNumber: String? = null

    /**
     * ФПД - номер фискального признака документа
     */
    var fiscalSignDocNumber: String? = null

    /**
     * ФНД - Номер фискального документа
     */
    var fiscalDocNumber: Int = 0

    /**
     * ФД - Дата фискального документа
     */
    var fiscalReceiptDate: String? = null

    /**
     * Серийный (заводской) номер ККТ
     */
    var kktSerialNumber: String? = null

    /**
     * Регистрационный номер ККТ
     */
    var kktRegNumber: String? = null

    constructor()

    constructor(objectToCopy: Result) : super(objectToCopy)

    constructor(resultCode: ResultCode) : super(resultCode)

    constructor(resultCode: ResultCode, errorDescription: String) : super(resultCode, errorDescription)

    constructor(parcel: Parcel) : super(parcel) {
        receiptNumber = parcel.readInt()
        documentNumber = parcel.readInt()
        sessionNumber = parcel.readInt()
        checkSum = parcel.readSerializable() as BigDecimal
        receiptDay = parcel.readInt()
        receiptMonth = parcel.readInt()
        receiptYear = parcel.readInt()
        fnSerialNumber = parcel.readString()
        fiscalSignDocNumber = parcel.readString()
        fiscalDocNumber = parcel.readInt()
        fiscalReceiptDate = parcel.readString()
        kktSerialNumber = parcel.readString()
        kktRegNumber = parcel.readString()
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        super.writeToParcel(dest, flags)
        dest.writeInt(receiptNumber)
        dest.writeInt(documentNumber)
        dest.writeInt(sessionNumber)
        dest.writeSerializable(checkSum)
        dest.writeInt(receiptDay)
        dest.writeInt(receiptMonth)
        dest.writeInt(receiptYear)
        dest.writeString(fnSerialNumber)
        dest.writeString(fiscalSignDocNumber)
        dest.writeInt(fiscalDocNumber)
        dest.writeString(fiscalReceiptDate)
        dest.writeString(kktSerialNumber)
        dest.writeString(kktRegNumber)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReceiptResult

        if (receiptNumber != other.receiptNumber) return false
        if (documentNumber != other.documentNumber) return false
        if (sessionNumber != other.sessionNumber) return false
        if (checkSum != other.checkSum) return false
        if (receiptDay != other.receiptDay) return false
        if (receiptMonth != other.receiptMonth) return false
        if (receiptYear != other.receiptYear) return false
        if (fnSerialNumber != other.fnSerialNumber) return false
        if (fiscalSignDocNumber != other.fiscalSignDocNumber) return false
        if (fiscalDocNumber != other.fiscalDocNumber) return false
        if (fiscalReceiptDate != other.fiscalReceiptDate) return false
        if (kktSerialNumber != other.kktSerialNumber) return false
        if (kktRegNumber != other.kktRegNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = receiptNumber
        result = 31 * result + documentNumber
        result = 31 * result + sessionNumber
        result = 31 * result + checkSum.hashCode()
        result = 31 * result + receiptDay
        result = 31 * result + receiptMonth
        result = 31 * result + receiptYear
        result = 31 * result + (fnSerialNumber?.hashCode() ?: 0)
        result = 31 * result + (fiscalSignDocNumber?.hashCode() ?: 0)
        result = 31 * result + fiscalDocNumber
        result = 31 * result + (fiscalReceiptDate?.hashCode() ?: 0)
        result = 31 * result + (kktSerialNumber?.hashCode() ?: 0)
        result = 31 * result + (kktRegNumber?.hashCode() ?: 0)
        return result
    }

    companion object {
        private const val serialVersionUID = 6106269076155338045L

        val CREATOR: Parcelable.Creator<ReceiptResult> = object : Parcelable.Creator<ReceiptResult> {
            override fun createFromParcel(pc: Parcel): ReceiptResult = ReceiptResult(pc)

            override fun newArray(size: Int): Array<ReceiptResult?> = arrayOfNulls(size)
        }
    }
}