package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.*
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.PayableService
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
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
                    ?: throw IntegrationLibraryMappingException(Position::uuid.name),
            productUuid = cursor.safeGetString(PositionContract.COLUMN_PRODUCT_UUID)?.let { UUID.fromString(it) }
                    ?: throw IntegrationLibraryMappingException(Position::productUuid.name),
            type = cursor.safeGetEnum(PositionContract.COLUMN_TYPE, Position.Type.values())
                    ?: throw IntegrationLibraryMappingException(Position::type.name),
            name = cursor.safeGetString(PositionContract.COLUMN_NAME)
                    ?: throw IntegrationLibraryMappingException(Position::name.name),
            productCode = cursor.safeGetString(PositionContract.COLUMN_PRODUCT_CODE)
                    ?: throw IntegrationLibraryMappingException(Position::productCode.name),
            price = cursor.safeGetMoney(PositionContract.COLUMN_PRICE)
                    ?: throw IntegrationLibraryMappingException(Position::price.name),
            quantity = cursor.safeGetQuantity(PositionContract.COLUMN_QUANTITY)
                    ?: throw IntegrationLibraryMappingException(Position::quantity.name),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            discount = cursor.safeGetMoney(PositionContract.COLUMN_DISCOUNT)
                    ?: throw IntegrationLibraryMappingException(Position::discount.name),
            settlementMethod = SettlementMethodMapper.read(cursor),
            agentRequisites = AgentRequisitesMapper.read(cursor)
    )
}