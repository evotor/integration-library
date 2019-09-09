package ru.evotor.framework.kkt.api

import android.content.AsyncQueryHandler
import android.content.Context
import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.kkt.provider.KktContract
import ru.evotor.framework.safeGetList

class AsyncHandler(context: Context, private val callback: (serial: String, reg: String) -> Unit)
    : AsyncQueryHandler(context.contentResolver) {

    override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
        cursor?.use {
            it.moveToFirst()

            val serialNumber = it.safeGetList(KktContract.COLUMN_SERIAL_NUMBER)
                    ?: throw IntegrationLibraryMappingException(KktContract.COLUMN_SERIAL_NUMBER)

            val regNumber = it.safeGetList(KktContract.COLUMN_REGISTER_NUMBER)
                    ?: throw IntegrationLibraryMappingException(KktContract.COLUMN_REGISTER_NUMBER)

            callback(serialNumber[0], regNumber[0])
        }
    }

    companion object {
        internal const val KKT_INFO_TOKEN = 0
    }
}