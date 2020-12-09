package ru.evotor.framework.fs.api

import android.content.Context
import android.net.Uri
import ru.evotor.framework.*
import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.startIntegrationService
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.kkt.FfdVersion
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.kkt.api.KktApi
import ru.evotor.framework.kkt.event.CorrectionReceiptRegistrationRequestedEvent
import ru.evotor.framework.kkt.event.handler.service.KktBacksideIntegrationService
import ru.evotor.framework.kkt.provider.KktContract
import ru.evotor.framework.payment.PaymentType
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.TaxationSystem
import ru.evotor.framework.receipt.correction.CorrectionType
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.framework.safeGetBoolean
import ru.evotor.framework.safeGetInt
import ru.evotor.framework.safeGetList
import ru.evotor.framework.safeGetString
import java.math.BigDecimal
import java.util.*

/**
 * Интерфейс для работы с кассой.
 */
object FsApi {
    private var fsSerialNumber: String? = null
    private var fsActivationDate: Date? = null


    /**
     * Возвращает серийный номер фискального накопителя или null, если фискальный накопитель отсуствует
     * или попытка завершилась неудачей
     *
     * @param context текущий контекст
     * @return серийный номер фискального накопителя или null
     */
    @JvmStatic
    fun getFsSerialNumber(context: Context): String? {
        if (fsSerialNumber == null) getKktFsInfo(context)

        return fsSerialNumber
    }

    private fun getKktFsInfo(context: Context) {
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

        cursor?.use {
            it.moveToFirst()
            fsSerialNumber = it.safeGetString(KktContract.COLUMN_FS_SERIAL_NUMBER)
        }
    }
}