package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.*
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.Service
import ru.evotor.framework.mapper.QuantityMapper
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
            uuid = cursor.safeGetString(ReceiptContract.PositionColumns.UUID)?.let { UUID.fromString(it) }
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::uuid),
            productUuid = cursor.safeGetString(ReceiptContract.PositionColumns.PRODUCT_UUID)?.let { UUID.fromString(it) },
            productCode = cursor.safeGetString(ReceiptContract.PositionColumns.PRODUCT_CODE),
            name = cursor.safeGetString(ReceiptContract.PositionColumns.NAME)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::name),
            type = cursor.safeGetEnum(ReceiptContract.PositionColumns.TYPE, Position.Type.values())
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::type),
            barcode = cursor.safeGetString(ReceiptContract.PositionColumns.BARCODE),
            mark = cursor.safeGetString(ReceiptContract.PositionColumns.MARK),
            price = cursor.safeGetMoney(ReceiptContract.PositionColumns.PRICE)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::price),
            discount = cursor.safeGetMoney(ReceiptContract.PositionColumns.DISCOUNT)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::discount),
            vatRate = cursor.safeGetEnum(ReceiptContract.PositionColumns.VAT_RATE, VatRate.values())
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::vatRate),
            quantity = QuantityMapper.read(cursor),
            settlementMethod = SettlementMethodMapper.read(cursor),
            agentRequisites = AgentRequisitesMapper.read(cursor)
    )
}