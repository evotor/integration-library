package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.optLong
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.LocalModuleInfo
import ru.evotor.framework.receipt.position.MarksCheckingInfo

object MarksCheckingInfoMapper {
    internal fun fromCursor(cursor: Cursor): MarksCheckingInfo? {
        val checkId = cursor.optString(PositionTable.COLUMN_MARKS_CHECKING_INFO_CHECK_ID)
            ?: return null
        val checkTimestamp = cursor.optLong(PositionTable.COLUMN_MARKS_CHECKING_INFO_CHECK_TIMESTAMP)
            ?: return null
        val inst = cursor.optString(PositionTable.COLUMN_MARKS_CHECKING_INFO_CHECK_INST)
        val lmChzDbVersion = cursor.optString(PositionTable.COLUMN_MARKS_CHECKING_INFO_CHECK_LM_CHZ_DB_VERSION)
        val localModuleInfo = if (inst != null && lmChzDbVersion != null) {
            LocalModuleInfo(
                inst,
                lmChzDbVersion
            )
        } else {
            null
        }
        return MarksCheckingInfo(
            checkId = checkId,
            checkTimestamp = checkTimestamp,
            localModuleInfo = localModuleInfo
        )
    }
}
