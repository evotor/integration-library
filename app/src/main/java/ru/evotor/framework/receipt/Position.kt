package ru.evotor.framework.receipt

import android.os.Parcel
import android.os.Parcelable

import java.math.BigDecimal
import java.util.ArrayList
import java.util.HashSet
import java.util.UUID

import ru.evotor.framework.calculator.MoneyCalculator
import ru.evotor.framework.calculator.PercentCalculator
import ru.evotor.framework.inventory.ProductItem
import ru.evotor.framework.inventory.ProductType

class Position() : Parcelable {

    /**
     * UUID позиции
     * @return UUID позиции
     */
    lateinit var uuid: String

    /**
     * UUID товара
     * @return UUID товара
     */
    var productUuid: String? = null

    /**
     * Код товара
     * @return Код товара
     */
    var productCode: String? = null

    /**
     * Вид товара
     * @return Вид товара
     */
    lateinit var productType: ProductType

    /**
     * Наименование
     * @return Наименование
     */
    lateinit var name: String

    /**
     * Наименование единицы измерения
     * @return Наименование единицы измерения
     */
    lateinit var measureName: String

    /**
     * Точность единицы измерения
     * @return Точность единицы измерения
     */
    var measurePrecision: Int = 0

    /**
     * НДС
     * @return Налоговая ставка
     */
    var taxNumber: TaxNumber? = null

    /**
     * Цена без скидок
     * @return Цена без скидок
     */
    lateinit var price: BigDecimal

    /**
     * Цена с учетом скидки на позицию
     * @return Цена с учетом скидки на позицию
     */
    lateinit var priceWithDiscountPosition: BigDecimal

    /**
     * Количество
     * @return Количество
     */
    lateinit var quantity: BigDecimal

    /**
     * Штрихкод, по которому товар был найден
     * @return Штрихкод, по которому товар был найден
     */
    var barcode: String? = null

    /**
     * Алкогольная марка
     * @return Алкогольная марка
     */
    var mark: String? = null

    /**
     * Крепость
     * @return Крепость
     */
    var alcoholByVolume: BigDecimal? = null

    /**
     * Код вида продукции ФСРАР
     * @return Код вида продукции ФСРАР
     */
    var alcoholProductKindCode: Long? = null

    /**
     * Объём тары
     * @return Объём тары
     */
    var tareVolume: BigDecimal? = null

    /**
     * Экстра ключи
     * @return Экстра ключи
     */
    var extraKeys: MutableSet<ExtraKey> = HashSet()

    /*
     * Подпозиции (модификаторы)
     * @return Подпозиции (модификаторы)
     */
    var subPositions: MutableList<Position> = ArrayList()

    @Deprecated("Deprecated since 10.11.2017. Use position constructor with 18 parameters, containing TaxNumber argument.")
    constructor(
            uuid: String,
            productUuid: String?,
            productCode: String?,
            productType: ProductType,
            name: String,
            measureName: String,
            measurePrecision: Int,
            price: BigDecimal,
            priceWithDiscountPosition: BigDecimal,
            quantity: BigDecimal,
            barcode: String?,
            mark: String,
            alcoholByVolume: BigDecimal?,
            alcoholProductKindCode: Long?,
            tareVolume: BigDecimal?,
            extraKeys: Set<ExtraKey>?,
            subPositions: List<Position>?
    ) : this() {
        this.uuid = uuid
        this.productUuid = productUuid
        this.productCode = productCode
        this.productType = productType
        this.name = name
        this.measureName = measureName
        this.measurePrecision = measurePrecision
        this.taxNumber = null
        this.price = price
        this.priceWithDiscountPosition = priceWithDiscountPosition
        this.quantity = quantity
        this.barcode = barcode
        this.mark = mark
        this.alcoholByVolume = alcoholByVolume
        this.alcoholProductKindCode = alcoholProductKindCode
        this.tareVolume = tareVolume
        extraKeys?.let { this.extraKeys.addAll(it) }
        subPositions?.let { this.subPositions.addAll(it) }
    }

    constructor(
            uuid: String,
            productUuid: String?,
            productCode: String?,
            productType: ProductType,
            name: String,
            measureName: String,
            measurePrecision: Int,
            taxNumber: TaxNumber?,
            price: BigDecimal,
            priceWithDiscountPosition: BigDecimal,
            quantity: BigDecimal,
            barcode: String?,
            mark: String?,
            alcoholByVolume: BigDecimal?,
            alcoholProductKindCode: Long?,
            tareVolume: BigDecimal?,
            extraKeys: Set<ExtraKey>?,
            subPositions: List<Position>?
    ) : this() {
        this.uuid = uuid
        this.productUuid = productUuid
        this.productCode = productCode
        this.productType = productType
        this.name = name
        this.measureName = measureName
        this.measurePrecision = measurePrecision
        this.taxNumber = taxNumber
        this.price = price
        this.priceWithDiscountPosition = priceWithDiscountPosition
        this.quantity = quantity
        this.barcode = barcode
        this.mark = mark
        this.alcoholByVolume = alcoholByVolume
        this.alcoholProductKindCode = alcoholProductKindCode
        this.tareVolume = tareVolume
        extraKeys?.let { this.extraKeys.addAll(it) }
        subPositions?.let { this.subPositions.addAll(it)}
    }

    constructor(position: Position) : this(
            position.uuid,
            position.productUuid,
            position.productCode,
            position.productType,
            position.name,
            position.measureName,
            position.measurePrecision,
            position.taxNumber,
            position.price,
            position.priceWithDiscountPosition,
            position.quantity,
            position.barcode,
            position.mark,
            position.alcoholByVolume,
            position.alcoholProductKindCode,
            position.tareVolume,
            position.extraKeys,
            position.subPositions
    )

    /**
     * Возвращает сумму без учета скидок
     * @return сумма без учета скидок
     */
    val totalWithoutDiscounts: BigDecimal
        get() = MoneyCalculator.multiply(price, quantity)

    /**
     * Возвращает сумму без учета скидки на чек
     * @return сумма без учета скидки на чек
     */
    val totalWithoutDocumentDiscount: BigDecimal
        get() = MoneyCalculator.multiply(priceWithDiscountPosition, quantity)

    /**
     * Возвращает процент скидки на позицию
     * @return процент скидки на позицию
     */
    val discountPercents: BigDecimal
        get() {
            if (discountPositionSum.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO
            }
            return PercentCalculator.calcPercent(totalWithoutDiscounts, discountPositionSum)
        }

    /**
     * Возвращает сумму скидки на позицию
     * @return сумма скидки на позицию
     */
    val discountPositionSum: BigDecimal
        get() = MoneyCalculator.subtract(totalWithoutDiscounts, totalWithoutDocumentDiscount)

    /**
     * Возвращает сумму c учетом скидки на чек и на позицию
     * @param discountDocumentPositionSum сумма скидки на чек, распределенная на эту позицию
     * @return сумма с учетом скидки на чек и на позицию
     */
    fun getTotal(discountDocumentPositionSum: BigDecimal): BigDecimal {
        return MoneyCalculator.subtract(MoneyCalculator.multiply(priceWithDiscountPosition, quantity),
                discountDocumentPositionSum)
    }

    /**
     * Возвращает сумму без учета скидки на чек с учётом всех подпозиций
     * @return сумма без учета скидки на чек с учётом всех подпозиций
     */
    val totalWithSubPositionsAndWithoutDocumentDiscount: BigDecimal
        get() {
            return subPositions.fold(totalWithoutDocumentDiscount){ sum, subPosition -> sum.add(subPosition.totalWithoutDocumentDiscount) }
        }

    constructor(parcel: Parcel) : this() {
        uuid = parcel.readString()
        productUuid = parcel.readString()
        productCode = parcel.readString()
        productType = ProductType.valueOf(parcel.readString())
        name = parcel.readString()
        measureName = parcel.readString()
        measurePrecision = parcel.readInt()
        taxNumber = TaxNumber.valueOf(parcel.readString())
        price = parcel.readSerializable() as BigDecimal
        priceWithDiscountPosition = parcel.readSerializable() as BigDecimal
        quantity = parcel.readSerializable() as BigDecimal
        barcode = parcel.readString()
        mark = parcel.readString()
        alcoholByVolume = parcel.readSerializable() as BigDecimal
        alcoholProductKindCode = parcel.readValue(Long::class.java.classLoader) as? Long
        tareVolume = parcel.readSerializable() as BigDecimal
        extraKeys = parcel.createTypedArrayList(ExtraKey.CREATOR).toMutableSet()
        subPositions = parcel.createTypedArrayList(CREATOR)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val position = other as Position

        if (measurePrecision != position.measurePrecision) return false
        if (uuid != position.uuid) return false
        if (if (productUuid != null) productUuid != position.productUuid else position.productUuid != null)
            return false
        if (if (productCode != null) productCode != position.productCode else position.productCode != null)
            return false
        if (productType != position.productType) return false
        if (name != position.name) return false
        if (measureName != position.measureName) return false
        if (taxNumber !== position.taxNumber) return false
        if (price != position.price) return false
        if (priceWithDiscountPosition != position.priceWithDiscountPosition) return false
        if (quantity != position.quantity) return false
        if (if (barcode != null) barcode != position.barcode else position.barcode != null)
            return false
        if (if (mark != null) mark != position.mark else position.mark != null) return false
        if (if (alcoholByVolume != null) alcoholByVolume != position.alcoholByVolume else position.alcoholByVolume != null)
            return false
        if (if (alcoholProductKindCode != null) alcoholProductKindCode != position.alcoholProductKindCode else position.alcoholProductKindCode != null)
            return false
        if (if (tareVolume != null) tareVolume != position.tareVolume else position.tareVolume != null)
            return false
        if (extraKeys != position.extraKeys)
            return false
        return subPositions == position.subPositions
    }


    override fun toString(): String {
        return "Position{" +
                "uuid='" + uuid + '\'' +
                ", productUuid='" + productUuid + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productType=" + productType +
                ", name='" + name + '\'' +
                ", measureName='" + measureName + '\'' +
                ", measurePrecision=" + measurePrecision +
                ", taxNumber=" + taxNumber +
                ", price=" + price +
                ", priceWithDiscountPosition=" + priceWithDiscountPosition +
                ", quantity=" + quantity +
                ", barcode='" + barcode + '\'' +
                ", mark='" + mark + '\'' +
                ", alcoholByVolume=" + alcoholByVolume +
                ", alcoholProductKindCode=" + alcoholProductKindCode +
                ", tareVolume=" + tareVolume +
                ", extraKeys=" + extraKeys +
                ", subPositions=" + subPositions +
                '}'
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + (productUuid?.hashCode() ?: 0)
        result = 31 * result + (productCode?.hashCode() ?: 0)
        result = 31 * result + productType.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + measureName.hashCode()
        result = 31 * result + measurePrecision
        result = 31 * result + (taxNumber?.hashCode() ?: 0)
        result = 31 * result + price.hashCode()
        result = 31 * result + priceWithDiscountPosition.hashCode()
        result = 31 * result + quantity.hashCode()
        result = 31 * result + (barcode?.hashCode() ?: 0)
        result = 31 * result + (mark?.hashCode() ?: 0)
        result = 31 * result + (alcoholByVolume?.hashCode() ?: 0)
        result = 31 * result + (alcoholProductKindCode?.hashCode() ?: 0)
        result = 31 * result + (tareVolume?.hashCode() ?: 0)
        result = 31 * result + extraKeys.hashCode()
        result = 31 * result + subPositions.hashCode()
        return result
    }

    class Builder @Deprecated("Use Position.Builder.copy(oldPosition) instead.")
    constructor(position: Position) {

        private val position: Position

        init {
            this.position = Position(position)
        }

        fun toAlcoholMarked(
                mark: String,
                alcoholByVolume: BigDecimal,
                alcoholProductKindCode: Long,
                tareVolume: BigDecimal
        ): Builder {
            position.productType = ProductType.ALCOHOL_MARKED
            position.mark = mark
            position.alcoholByVolume = alcoholByVolume
            position.alcoholProductKindCode = alcoholProductKindCode
            position.tareVolume = tareVolume
            return this
        }

        fun toAlcoholNotMarked(
                alcoholByVolume: BigDecimal,
                alcoholProductKindCode: Long,
                tareVolume: BigDecimal
        ): Builder {
            position.productType = ProductType.ALCOHOL_NOT_MARKED
            position.alcoholByVolume = alcoholByVolume
            position.alcoholProductKindCode = alcoholProductKindCode
            position.tareVolume = tareVolume
            return this
        }

        fun setUuid(uuid: String): Builder {
            position.uuid = uuid
            return this
        }

        fun setQuantity(quantity: BigDecimal): Builder {
            position.quantity = quantity
            return this
        }

        fun setPrice(price: BigDecimal): Builder {
            position.price = price
            return this
        }

        fun setPriceWithDiscountPosition(priceWithDiscountPosition: BigDecimal): Builder {
            position.priceWithDiscountPosition = priceWithDiscountPosition
            return this
        }

        fun setMark(mark: String?): Builder {
            position.mark = mark
            return this
        }

        fun setExtraKeys(extraKeys: Set<ExtraKey>?): Builder {
            extraKeys?.let { position.extraKeys.addAll(extraKeys)  }
            return this
        }

        fun setMeasureName(measureName: String): Builder {
            position.measureName = measureName
            return this
        }

        fun setMeasurePrecision(measurePrecision: Int): Builder {
            position.measurePrecision = measurePrecision
            return this
        }

        fun setTaxNumber(taxNumber: TaxNumber?): Builder {
            position.taxNumber = taxNumber
            return this
        }

        fun setSubPositions(subPositions: List<Position>?): Builder {
            subPositions?.let { position.subPositions.addAll(subPositions) }
            return this
        }

        fun setBarcode(barcode: String?): Builder {
            position.barcode = barcode
            return this
        }

        fun build(): Position {
            return Position(position)
        }

        companion object {
            fun newInstance(
                    product: ProductItem.Product,
                    quantity: BigDecimal
            ): Builder {
                val builder = Builder.newInstance(
                        UUID.randomUUID().toString(),
                        product.uuid,
                        product.name,
                        product.measureName,
                        product.measurePrecision,
                        product.price,
                        quantity
                )

                val position = builder.position
                position.productType = product.type
                position.alcoholByVolume = product.alcoholByVolume
                position.alcoholProductKindCode = product.alcoholProductKindCode
                position.tareVolume = product.tareVolume
                position.productCode = product.code

                return builder

            }

            fun newInstance(
                    uuid: String,
                    productUuid: String?,
                    name: String,
                    measureName: String,
                    measurePrecision: Int,
                    price: BigDecimal,
                    quantity: BigDecimal
            ): Builder {
                return Builder(
                        Position(
                                uuid,
                                productUuid, null,
                                ProductType.NORMAL,
                                name,
                                measureName,
                                measurePrecision, null,
                                price,
                                price,
                                quantity, null, null, null, null, null, null, null
                        )
                )
            }

            fun copyFrom(position: Position): Builder {
                return Builder(Position(position))
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uuid)
        parcel.writeString(productUuid)
        parcel.writeString(productCode)
        parcel.writeString(productType.name)
        parcel.writeString(name)
        parcel.writeString(measureName)
        parcel.writeInt(measurePrecision)
        parcel.writeString(taxNumber?.name)
        parcel.writeSerializable(price)
        parcel.writeSerializable(priceWithDiscountPosition)
        parcel.writeSerializable(quantity)
        parcel.writeString(barcode)
        parcel.writeString(mark)
        parcel.writeSerializable(alcoholByVolume)
        parcel.writeValue(alcoholProductKindCode)
        parcel.writeSerializable(tareVolume)
        parcel.writeTypedList(extraKeys.toList())
        parcel.writeTypedList(subPositions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Position> {
            override fun createFromParcel(parcel: Parcel): Position {
                return Position(parcel)
            }

            override fun newArray(size: Int): Array<Position?> {
                return arrayOfNulls(size)
            }
        }
    }
}
