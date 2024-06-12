package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.optLong
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.MarksCheckingInfo

object MarksCheckingInfoMapper {
    internal fun fromCursor(cursor: Cursor): MarksCheckingInfo? {
        val checkId = cursor.optString(PositionTable.COLUMN_MARKS_CHECKING_INFO_CHECK_ID)
            ?: return null
        val checkTimestamp = cursor.optLong(PositionTable.COLUMN_MARKS_CHECKING_INFO_CHECK_TIMESTAMP)
            ?: return null
        return MarksCheckingInfo(
            checkId = checkId,
            checkTimestamp = checkTimestamp
        )
    }
}
