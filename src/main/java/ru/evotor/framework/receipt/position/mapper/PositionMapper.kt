package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.*
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.Service
import ru.evotor.framework.mapper.QuantityMapper
import ru.evotor.framework.receipt.TaxNumber
import ru.evotor.framework.receipt.position.Position
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.framework.receipt.provider.ReceiptContract
import java.util.*

internal object PositionMapper {
    fun getType(product: Product) = when (product) {
        is ExcisableProduct -> Position.Type.EXCISABLE_PRODUCT
        is Service -> Position.Type.SERVICE
        else -> Position.Type.ORDINARY_PRODUCT
    }

    fun read(cursor: Cursor) = Position(
            uuid = cursor.safeGetString(ReceiptContract.Position.UUID)?.let { UUID.fromString(it) }
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::uuid),
            productUuid = cursor.safeGetString(ReceiptContract.Position.PRODUCT_UUID)?.let { UUID.fromString(it) },
            productCode = cursor.safeGetString(ReceiptContract.Position.PRODUCT_CODE),
            name = cursor.safeGetString(ReceiptContract.Position.NAME)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::name),
            type = cursor.safeGetEnum(ReceiptContract.Position.TYPE, Position.Type.values())
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::type),
            barcode = cursor.safeGetString(ReceiptContract.Position.BARCODE),
            mark = cursor.safeGetString(ReceiptContract.Position.MARK),
            price = cursor.safeGetAmountOfRubles(ReceiptContract.Position.PRICE)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::price),
            discount = cursor.safeGetAmountOfRubles(ReceiptContract.Position.DISCOUNT)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::discount),
            vatRate = readVatRate(cursor)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::vatRate),
            quantity = QuantityMapper.read(cursor)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::quantity),
            settlementMethod = SettlementMethodMapper.read(cursor)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::settlementMethod),
            agentRequisites = AgentRequisitesMapper.read(cursor)
    )

    fun readVatRate(cursor: Cursor): VatRate? = cursor.safeGetString(ReceiptContract.Position.VAT_RATE)?.let {
        when (it) {
            TaxNumber.NO_VAT.name -> VatRate.WITHOUT_VAT
            TaxNumber.VAT_0.name -> VatRate.VAT_0
            TaxNumber.VAT_10.name -> VatRate.VAT_10
            TaxNumber.VAT_10_110.name -> VatRate.VAT_10_110
            TaxNumber.VAT_18.name -> VatRate.VAT_20
            TaxNumber.VAT_18_118.name -> VatRate.VAT_20_118
            else -> null
        }
    }
}