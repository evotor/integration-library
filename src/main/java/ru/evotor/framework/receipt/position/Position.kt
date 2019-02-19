package ru.evotor.framework.receipt.position

import ru.evotor.framework.FutureFeature
import ru.evotor.framework.Quantity
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.payment.AmountOfRubles
import ru.evotor.framework.receipt.position.mapper.PositionMapper
import java.util.*

@FutureFeature("Новая позиция")
internal class Position internal constructor(
        val uuid: UUID,
        val productUuid: UUID?,
        val productCode: String?,
        val name: String,
        val type: Type,
        val barcode: String?,
        val mark: String?,
        val price: AmountOfRubles,
        val discount: AmountOfRubles,
        val vatRate: VatRate,
        val quantity: Quantity,
        val settlementMethod: SettlementMethod,
        val agentRequisites: AgentRequisites?
) {
    val priceWithDiscountsAndSurcharges get() = price - discount

    constructor(
            uuid: UUID = UUID.randomUUID(),
            product: Product,
            name: String = product.name,
            barcode: String? = null,
            price: AmountOfRubles = product.price!!,
            discount: AmountOfRubles = AmountOfRubles(0),
            quantity: Quantity = Quantity(
                    1,
                    product.quantity.unitOfMeasurement
            ),
            settlementMethod: SettlementMethod = SettlementMethod.FullSettlement(),
            agentRequisites: AgentRequisites? = null
    ) : this(
            uuid,
            product.uuid,
            product.code,
            name,
            PositionMapper.getType(product),
            barcode,
            null,
            price,
            discount,
            product.vatRate,
            quantity,
            settlementMethod,
            agentRequisites
    )

    constructor(
            uuid: UUID = UUID.randomUUID(),
            excisableProduct: ExcisableProduct,
            name: String = (excisableProduct as Product).name,
            barcode: String? = null,
            mark: String,
            price: AmountOfRubles = (excisableProduct as Product).price!!,
            discount: AmountOfRubles = AmountOfRubles(0),
            quantity: Quantity = Quantity(
                    1,
                    (excisableProduct as Product).quantity.unitOfMeasurement
            ),
            settlementMethod: SettlementMethod = SettlementMethod.FullSettlement(),
            agentRequisites: AgentRequisites? = null
    ) : this(
            uuid,
            (excisableProduct as Product).uuid,
            excisableProduct.code,
            name,
            PositionMapper.getType(excisableProduct),
            barcode,
            mark,
            price,
            discount,
            excisableProduct.vatRate,
            quantity,
            settlementMethod,
            agentRequisites
    )

    constructor(
            uuid: UUID = UUID.randomUUID(),
            productCode: String? = null,
            name: String,
            type: Type = Type.ORDINARY_PRODUCT,
            barcode: String? = null,
            mark: String? = null,
            price: AmountOfRubles,
            discount: AmountOfRubles = AmountOfRubles(0),
            vatRate: VatRate = VatRate.WITHOUT_VAT,
            quantity: Quantity = Quantity(1),
            settlementMethod: SettlementMethod = SettlementMethod.FullSettlement(),
            agentRequisites: AgentRequisites? = null
    ) : this(
            uuid,
            null,
            productCode,
            name,
            type,
            barcode,
            mark,
            price,
            discount,
            vatRate,
            quantity,
            settlementMethod,
            agentRequisites
    )

    fun copy(
            uuid: UUID = this.uuid,
            productCode: String? = this.productCode,
            name: String = this.name,
            type: Type = this.type,
            barcode: String? = this.barcode,
            mark: String? = this.mark,
            price: AmountOfRubles = this.price,
            quantity: Quantity = this.quantity,
            discount: AmountOfRubles = this.discount,
            vatRate: VatRate = this.vatRate,
            settlementMethod: SettlementMethod = this.settlementMethod,
            agentRequisites: AgentRequisites? = this.agentRequisites
    ) = Position(
            uuid,
            if (productCode == this.productCode && type == this.type && vatRate == this.vatRate)
                productUuid
            else
                null,
            productCode,
            name,
            type,
            barcode,
            if (type == Type.EXCISABLE_PRODUCT)
                mark
            else
                null,
            price,
            discount,
            vatRate,
            quantity,
            settlementMethod,
            agentRequisites
    )

    enum class Type {
        ORDINARY_PRODUCT,
        EXCISABLE_PRODUCT,
        SERVICE
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Position) return false

        if (uuid != other.uuid) return false
        if (productUuid != other.productUuid) return false
        if (type != other.type) return false
        if (name != other.name) return false
        if (productCode != other.productCode) return false
        if (barcode != other.barcode) return false
        if (mark != other.mark) return false
        if (price != other.price) return false
        if (vatRate != other.vatRate) return false
        if (quantity != other.quantity) return false
        if (discount != other.discount) return false
        if (settlementMethod != other.settlementMethod) return false
        if (agentRequisites != other.agentRequisites) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + (productUuid?.hashCode() ?: 0)
        result = 31 * result + type.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (productCode?.hashCode() ?: 0)
        result = 31 * result + (barcode?.hashCode() ?: 0)
        result = 31 * result + (mark?.hashCode() ?: 0)
        result = 31 * result + price.hashCode()
        result = 31 * result + vatRate.hashCode()
        result = 31 * result + quantity.hashCode()
        result = 31 * result + discount.hashCode()
        result = 31 * result + settlementMethod.hashCode()
        result = 31 * result + (agentRequisites?.hashCode() ?: 0)
        return result
    }
}