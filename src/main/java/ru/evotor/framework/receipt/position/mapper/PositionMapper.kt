package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.core.safeGetEnum
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.Service
import ru.evotor.framework.mapper.QuantityMapper
import ru.evotor.framework.payment.mapper.AmountOfRublesMapper
import ru.evotor.framework.receipt.position.Position
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
            price = AmountOfRublesMapper.read(cursor, ReceiptContract.Position.PRICE)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::price),
            discount = AmountOfRublesMapper.read(cursor, ReceiptContract.Position.DISCOUNT)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::discount),
            vatRate = VatRateMapper.read(cursor)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::vatRate),
            quantity = QuantityMapper.read(
                    cursor,
                    ReceiptContract.Position.QUANTITY_EXACT_VALUE,
                    ReceiptContract.Position.QUANTITY_SCALE,
                    ReceiptContract.Position.UNIT_OF_MEASUREMENT_VARIATION_ID,
                    ReceiptContract.Position.UNIT_OF_MEASUREMENT_NAME,
                    ReceiptContract.Position.UNIT_OF_MEASUREMENT_TYPE
            ) ?: throw IntegrationLibraryMappingException(Position::class.java, Position::quantity),
            settlementMethod = SettlementMethodMapper.read(cursor)
                    ?: throw IntegrationLibraryMappingException(Position::class.java, Position::settlementMethod),
            agentRequisites = AgentRequisitesMapper.read(cursor)
    )
}