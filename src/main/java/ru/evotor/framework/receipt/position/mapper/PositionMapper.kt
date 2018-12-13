package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.*
import ru.evotor.framework.core.OutdatedLibraryException
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
            uuid = cursor.optString(PositionContract.COLUMN_UUID)?.let { UUID.fromString(it) }
                    ?: throw OutdatedLibraryException(Position::uuid.name),
            productUuid = cursor.optString(PositionContract.COLUMN_PRODUCT_UUID)?.let { UUID.fromString(it) }
                    ?: throw OutdatedLibraryException(Position::productUuid.name),
            type = cursor.safeGetEnum(PositionContract.COLUMN_TYPE, Position.Type.values())
                    ?: throw OutdatedLibraryException(Position::type.name),
            name = cursor.optString(PositionContract.COLUMN_NAME)
                    ?: throw OutdatedLibraryException(Position::name.name),
            productCode = cursor.optString(PositionContract.COLUMN_PRODUCT_CODE)
                    ?: throw OutdatedLibraryException(Position::productCode.name),
            price = cursor.safeGetMoney(PositionContract.COLUMN_PRICE)
                    ?: throw OutdatedLibraryException(Position::price.name),
            quantity = cursor.safeGetQuantity(PositionContract.COLUMN_QUANTITY)
                    ?: throw OutdatedLibraryException(Position::quantity.name),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            discount = cursor.safeGetMoney(PositionContract.COLUMN_DISCOUNT)
                    ?: throw OutdatedLibraryException(Position::discount.name),
            settlementMethod = SettlementMethodMapper.read(cursor),
            agentRequisites = AgentRequisitesMapper.read(cursor)
    )
}