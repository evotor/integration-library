package ru.evotor.framework.receipt

import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.calculator.MoneyCalculator
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashMap

/**
 * Чек
 */
data class Receipt
(
        /**
         * Заголовок чека
         */
        val header: Header,
        /**
         * Печатные формы чека
         */
        val printDocuments: List<PrintReceipt>
) : Parcelable {

    /**
     * Список всех позиций чека
     */
    fun getPositions(): List<Position> {
        return printDocuments
                .flatMap { it.positions }
                .toList()
    }

    /**
     * Список всех оплат чека
     */
    fun getPayments(): List<Payment> {
        return printDocuments
                .map { it.payments }
                .flatMap { it.keys }
                .distinct()
    }

    /**
     * Скидка на чек. Без учета скидок на позиции
     */
    fun getDiscount(): BigDecimal {
        return printDocuments
                .fold(BigDecimal.ZERO, { acc, printDocument ->
                    MoneyCalculator.add(acc, printDocument.getDiscount())
                })
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeParcelable(header, 0)
        parcel.writeTypedList(printDocuments)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Receipt> = object : Parcelable.Creator<Receipt> {
            override fun createFromParcel(parcel: Parcel): Receipt {
                val header = parcel.readParcelable<Header>(Header::class.java.classLoader)
                val printDocuments = ArrayList<PrintReceipt>()
                parcel.readTypedList(printDocuments, PrintReceipt.CREATOR)
                return Receipt(header, printDocuments)
            }

            override fun newArray(size: Int): Array<Receipt?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    /**
     * Заголовок чека
     */
    data class Header(
            /**
             * Uuid чека
             */
            val uuid: String,
            /**
             * Номер чека. Может быть null для еще незакрытого чека
             */
            val number: String?,
            /**
             * Тип чека
             */
            val type: Type,
            /**
             * Дата регистрации чека.
             */
            val date: Date?,
            /**
             * Email для отправки чека по почте
             */
            var clientEmail: String?,

            /**
             * Phone для отправки чека по смс
             */
            var clientPhone: String?,

            /**
             * Extra
             */
            val extra: String?
    ) : Parcelable {
        override fun writeToParcel(parcel: Parcel, i: Int) {
            parcel.writeString(uuid)
            parcel.writeString(number)
            parcel.writeString(type.name)
            parcel.writeLong(date?.time ?: -1L)
            parcel.writeString(clientEmail)
            parcel.writeString(clientPhone)
            parcel.writeString(extra)
        }

        companion object {

            @JvmField
            val CREATOR: Parcelable.Creator<Header> = object : Parcelable.Creator<Header> {
                override fun createFromParcel(parcel: Parcel): Header {
                    val uuid = parcel.readString()
                    val number = parcel.readString()
                    val type = Type.valueOf(parcel.readString())
                    val dateInMillis = parcel.readLong()

                    val date: Date? =
                            if (dateInMillis != -1L)
                                Date(dateInMillis)
                            else
                                null

                    val email = parcel.readString()
                    val phone = parcel.readString()
                    val extra = parcel.readString()
                    return Header(uuid, number, type, date, email, phone, extra)
                }

                override fun newArray(size: Int): Array<Header?> {
                    return arrayOfNulls(size)
                }
            }
        }

        override fun describeContents(): Int {
            return 0
        }
    }

    /**
     * Тип чека
     */
    enum class Type {
        /**
         * Продажа
         */
        SELL,
        /**
         * Возврат
         */
        PAYBACK,
        /**
         * Покупка
         */
        BUY,
        /**
         * Возврат покупки
         */
        BUYBACK
    }

    /**
     * Печатная форма чека
     */
    data class PrintReceipt(
            /**
             * Печатная группа
             */
            val printGroup: PrintGroup?,
            /**
             * Позиции
             */
            val positions: List<Position>,
            /**
             * Оплаты
             */
            val payments: Map<Payment, BigDecimal>,
            /**
             * Сдача
             */
            val changes: Map<Payment, BigDecimal>,
            /**
             * Скидка на документ, распределенная на позиции
             * Ключ - uuid позиции
             * Значение - скидка (уже высчитанная из цены)
             *
             * Added on 13.02.2018
             */
            val discounts: Map<String, BigDecimal>?
    ) : Parcelable {

        /**
         * Сумма скидок для текущей группы
         */
        fun getDiscount(): BigDecimal {
            return positions
                    .fold(BigDecimal.ZERO, { acc, position ->
                        MoneyCalculator.add(acc, discounts?.get(position.uuid) ?: BigDecimal.ZERO)
                    })
        }

        override fun writeToParcel(parcel: Parcel, i: Int) {
            parcel.writeParcelable(printGroup, 0)
            parcel.writeTypedList(positions)

            parcel.writeInt(payments.size)
            for (entry in payments.entries) {
                parcel.writeParcelable(entry.key, 0)
                parcel.writeSerializable(entry.value)
            }

            parcel.writeInt(changes.size)
            for (entry in changes.entries) {
                parcel.writeParcelable(entry.key, 0)
                parcel.writeSerializable(entry.value)
            }

            discounts?.let {
                parcel.writeInt(it.size)
                for (entry in it.entries) {
                    parcel.writeString(entry.key)
                    parcel.writeSerializable(entry.value)
                }
            } ?: parcel.writeInt(-1)
        }

        companion object {

            @JvmField
            val CREATOR: Parcelable.Creator<PrintReceipt> = object : Parcelable.Creator<PrintReceipt> {
                override fun createFromParcel(parcel: Parcel): PrintReceipt {
                    val printGroup = parcel.readParcelable<PrintGroup>(PrintGroup::class.java.classLoader)
                    val positions = ArrayList<Position>()
                    parcel.readTypedList(positions, Position.CREATOR)

                    val paymentsSize = parcel.readInt()
                    val payments = HashMap<Payment, BigDecimal>(paymentsSize)
                    for (i in 0 until paymentsSize) {
                        val key = parcel.readParcelable<Payment>(Payment::class.java.classLoader)
                        val value = parcel.readSerializable() as BigDecimal
                        payments[key] = value
                    }

                    val changesSize = parcel.readInt()
                    val changes = HashMap<Payment, BigDecimal>(changesSize)
                    for (i in 0 until changesSize) {
                        val key = parcel.readParcelable<Payment>(Payment::class.java.classLoader)
                        val value = parcel.readSerializable() as BigDecimal
                        payments[key] = value
                    }

                    val discountSize = parcel.readInt()
                    val discounts: MutableMap<String, BigDecimal>? =
                            if (discountSize == -1) {
                                null
                            } else {
                                HashMap<String, BigDecimal>(discountSize)
                            }

                    for (i in 0 until discountSize) {
                        discounts?.put(parcel.readString(), parcel.readSerializable() as BigDecimal)
                    }

                    return PrintReceipt(printGroup, positions, payments, changes, discounts)
                }

                override fun newArray(size: Int): Array<PrintReceipt?> = arrayOfNulls(size)
            }
        }

        override fun describeContents() = 0
    }
}