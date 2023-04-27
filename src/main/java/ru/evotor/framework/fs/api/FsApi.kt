package ru.evotor.framework.fs.api

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.fs.FsFiscalizationDocument
import ru.evotor.framework.kkt.provider.KktContract
import ru.evotor.framework.optInt
import ru.evotor.framework.optLong
import ru.evotor.framework.optString
import java.util.*

/**
 * Интерфейс для работы с ФН.
 */
object FsApi {
    private val fsUriPrefix = "${KktContract.BASE_URI}${KktContract.PATH_KKT_FS_REGISTRATION_INFO}"

    /**
     * Возвращает серийный номер фискального накопителя или null, если фискальный накопитель отсуствует
     * или попытка завершилась неудачей
     *
     * @param context текущий контекст
     * @return серийный номер фискального накопителя или null
     */
    @JvmStatic
    fun getFsSerialNumber(context: Context): String? {
        return getKktFsInfo(context)
    }

    @JvmStatic
    fun getLastFsFiscalizationDocument(context: Context): GetFsFiscalizationDocumentResult {
        val uri = Uri.parse("$fsUriPrefix/${KktContract.PATH_KKT_FS_REGISTRATION_INFO_LAST}")
        return getFsFiscalizationDocument(context, uri)
    }

    @JvmStatic
    fun getFirstFsFiscalizationDocument(context: Context): GetFsFiscalizationDocumentResult {
        val uri = Uri.parse("$fsUriPrefix/${KktContract.PATH_KKT_FS_REGISTRATION_INFO_FIRST}")
        return getFsFiscalizationDocument(context, uri)
    }

    private fun mapCursorToFsFiscalizationDocument(cursor: Cursor): FsFiscalizationDocument? {
        if (!cursor.moveToFirst()) {
            return null
        }

        val date: Date? =
            cursor.optLong(KktContract.COLUMN_FS_REGISTRATION_INFO_DATE)?.let { Date(it) }
        val inn: String? = cursor.optString(KktContract.COLUMN_FS_REGISTRATION_INFO_INN)
        val rnm: String? = cursor.optString(KktContract.COLUMN_FS_REGISTRATION_INFO_RNM)
        val taxationSystems: Byte? =
            cursor.optInt(KktContract.COLUMN_FS_REGISTRATION_INFO_TAXATION_SYSTEMS)?.toByte()
        val workModeFlags: Byte? =
            cursor.optInt(KktContract.COLUMN_FS_REGISTRATION_INFO_WORK_MODE_FLAGS)?.toByte()
        val workModeExFlags: Byte? =
            cursor.optInt(KktContract.COLUMN_FS_REGISTRATION_INFO_WORK_MODE_EX_FLAGS)?.toByte()
        val ofdInn: String? = cursor.optString(KktContract.COLUMN_FS_REGISTRATION_INFO_OFD_INN)
        val reregistrationReasonCode: Int? =
            cursor.optInt(KktContract.COLUMN_FS_REGISTRATION_INFO_REREGISTRATION_REASON_CODE)
        val fiscalDocumentNumber: Int? =
            cursor.optInt(KktContract.COLUMN_FS_REGISTRATION_INFO_FISCAL_DOCUMENT_NUMBER)
        val fiscalDocumentSign: Int? =
            cursor.optInt(KktContract.COLUMN_FS_REGISTRATION_INFO_FISCAL_DOCUMENT_SIGN)

        date ?: return null
        inn ?: return null
        rnm ?: return null
        taxationSystems ?: return null
        workModeFlags ?: return null
        fiscalDocumentNumber ?: return null
        fiscalDocumentSign ?: return null

        return FsFiscalizationDocument(
            date = date,
            inn = inn,
            rnm = rnm,
            taxationSystems = taxationSystems,
            workModeFlags = workModeFlags,
            workModeExFlags = workModeExFlags,
            ofdInn = ofdInn,
            reregistrationReasonCode = reregistrationReasonCode,
            fiscalDocumentNumber = fiscalDocumentNumber,
            fiscalDocumentSign = fiscalDocumentSign
        )
    }

    private fun getFsFiscalizationDocument(
        context: Context,
        uri: Uri
    ): GetFsFiscalizationDocumentResult {
        val cursor = context.contentResolver.query(
            uri,
            arrayOf(
                KktContract.COLUMN_FS_REGISTRATION_INFO_DATE,
                KktContract.COLUMN_FS_REGISTRATION_INFO_INN,
                KktContract.COLUMN_FS_REGISTRATION_INFO_RNM,
                KktContract.COLUMN_FS_REGISTRATION_INFO_TAXATION_SYSTEMS,
                KktContract.COLUMN_FS_REGISTRATION_INFO_WORK_MODE_FLAGS,
                KktContract.COLUMN_FS_REGISTRATION_INFO_WORK_MODE_EX_FLAGS,
                KktContract.COLUMN_FS_REGISTRATION_INFO_OFD_INN,
                KktContract.COLUMN_FS_REGISTRATION_INFO_REREGISTRATION_REASON_CODE,
                KktContract.COLUMN_FS_REGISTRATION_INFO_FISCAL_DOCUMENT_NUMBER,
                KktContract.COLUMN_FS_REGISTRATION_INFO_FISCAL_DOCUMENT_SIGN
            ),
            null,
            null,
            null
        )

        cursor ?: return GetFsFiscalizationDocumentResult.Unsupported
        return cursor.use {
            GetFsFiscalizationDocumentResult.Success(mapCursorToFsFiscalizationDocument(it))
        }
    }

    private fun getKktFsInfo(context: Context): String? {
        val uri = Uri.parse("${KktContract.BASE_URI}${KktContract.PATH_KKT_FS_INFO}")
        val cursor = context.contentResolver.query(
            uri,
            arrayOf(
                KktContract.COLUMN_FS_SERIAL_NUMBER
            ),
            null,
            null,
            null
        )

        return cursor?.use {
            it.moveToFirst()
            it.optString(KktContract.COLUMN_FS_SERIAL_NUMBER)
        }
    }


    sealed class GetFsFiscalizationDocumentResult {
        class Success(val document: FsFiscalizationDocument?) : GetFsFiscalizationDocumentResult()
        object Unsupported : GetFsFiscalizationDocumentResult()
    }
}
