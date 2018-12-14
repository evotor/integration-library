package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.*
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.UnclassifiedPayableService
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.PayableService
import ru.evotor.framework.inventory.product.mapper.UnitOfMeasurementMapper
import ru.evotor.framework.receipt.position.Position
import ru.evotor.framework.receipt.position.provider.PositionContract
import java.util.*

internal object PositionMapper {
    fun getPositionType(product: Product) = when (product) {
        is ExcisableProduct -> Position.Type.EXCISABLE_PRODUCT
        is PayableService -> Position.Type.PAYABLE_SERVICE
        else -> Position.Type.ORDINARY_PRODUCT
    }

    fun read(cursor: Cursor) = Position(
            uuid = cursor.safeGetString(PositionContract.COLUMN_UUID)?.let { UUID.fromString(it) }
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::uuid),
            productUuid = cursor.safeGetString(PositionContract.COLUMN_PRODUCT_UUID)?.let { UUID.fromString(it) }
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::productUuid),
            type = cursor.safeGetEnum(PositionContract.COLUMN_TYPE, Position.Type.values())
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::type),
            name = cursor.safeGetString(PositionContract.COLUMN_NAME)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::name),
            productCode = cursor.safeGetString(PositionContract.COLUMN_PRODUCT_CODE)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::productCode),
            price = cursor.safeGetMoney(PositionContract.COLUMN_PRICE)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::price),
            quantity = cursor.safeGetQuantity(PositionContract.COLUMN_QUANTITY)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::quantity),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            discount = cursor.safeGetMoney(PositionContract.COLUMN_DISCOUNT)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::discount),
            settlementMethod = SettlementMethodMapper.read(cursor),
            agentRequisites = AgentRequisitesMapper.read(cursor)
    )
}