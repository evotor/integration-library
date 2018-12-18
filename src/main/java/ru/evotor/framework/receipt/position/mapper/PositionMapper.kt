package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.*
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.PayableService
import ru.evotor.framework.inventory.product.mapper.UnitOfMeasurementMapper
import ru.evotor.framework.receipt.position.Position
import ru.evotor.framework.receipt.provider.ReceiptContract
import java.util.*

internal object PositionMapper {
    fun getPositionType(product: Product) = when (product) {
        is ExcisableProduct -> Position.Type.EXCISABLE_PRODUCT
        is PayableService -> Position.Type.PAYABLE_SERVICE
        else -> Position.Type.ORDINARY_PRODUCT
    }

    fun read(cursor: Cursor) = Position(
            uuid = cursor.safeGetString(ReceiptContract.PositionColumns.UUID)?.let { UUID.fromString(it) }
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::uuid),
            productUuid = cursor.safeGetString(ReceiptContract.PositionColumns.PRODUCT_UUID)?.let { UUID.fromString(it) },
            type = cursor.safeGetEnum(ReceiptContract.PositionColumns.TYPE, Position.Type.values())
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::type),
            name = cursor.safeGetString(ReceiptContract.PositionColumns.NAME)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::name),
            productCode = cursor.safeGetString(ReceiptContract.PositionColumns.PRODUCT_CODE),
            barcode = cursor.safeGetString(ReceiptContract.PositionColumns.BARCODE),
            mark = cursor.safeGetString(ReceiptContract.PositionColumns.MARK),
            price = cursor.safeGetMoney(ReceiptContract.PositionColumns.PRICE)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::price),
            quantity = cursor.safeGetQuantity(ReceiptContract.PositionColumns.QUANTITY)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::quantity),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            discount = cursor.safeGetMoney(ReceiptContract.PositionColumns.DISCOUNT)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::discount),
            settlementMethod = SettlementMethodMapper.read(cursor),
            agentRequisites = AgentRequisitesMapper.read(cursor)
    )
}