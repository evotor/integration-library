package ru.evotor.framework.receipt.position.event

import android.os.Bundle

import ru.evotor.IBundlable
import ru.evotor.framework.core.action.datamapper.PositionMapper
import ru.evotor.framework.receipt.Position

abstract class PositionEvent internal constructor(
        val receiptUuid: String,
        val position: Position
) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        result.putBundle(KEY_POSITION, PositionMapper.toBundle(position))
        return result
    }

    companion object {

        private const val KEY_RECEIPT_UUID = "receiptUuid"

        private const val KEY_POSITION = "position"

        internal fun getReceiptUuid(bundle: Bundle): String? =
                bundle.getString(KEY_RECEIPT_UUID, null)

        internal fun getPosition(bundle: Bundle): Position? =
                PositionMapper.from(bundle.getBundle(KEY_POSITION))

    }

}
