package ru.evotor.framework.kkt.api

import android.content.Context
import ru.evotor.framework.kkt.FfdVersion
import ru.evotor.framework.kkt.provider.KktContract

object KktApi {
    @JvmStatic
    fun getFfdVersion(context: Context): FfdVersion {
        return context.contentResolver.query(KktContract.URI_FFD_VERSION, null, null, null, null)
                ?.let { cursor ->
                    cursor.moveToFirst()
                    cursor.getString(cursor.getColumnIndex(KktContract.COLUMN_FFD_VERSION))?.let {
                        FfdVersion.valueOf(it)
                    }
                } ?: FfdVersion.UNKNOWN
    }
}