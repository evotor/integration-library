package ru.evotor.framework.core.action.command.open_receipt_command

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.Utils
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl
import ru.evotor.framework.core.action.datamapper.ChangesMapper
import ru.evotor.framework.core.action.datamapper.ChangesMapper.toBundle
import ru.evotor.framework.core.action.event.receipt.changes.position.PositionAdd
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetExtra
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetPurchaserContactData
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.receipt.correction.CorrectionType
import java.util.*

/**
 * Команда открытия чека коррекции расхода
 * @param changes Список позиций
 * @param extra Дополнительные поля для чека
 * @param correctionDate Дата совершения корректируемого расчета (ТЕГ 1178)
 * @param correctionType Тип коррекции (ТЕГ 1173)
 * @param prescription Номер предписания налогового органа (ТЕГ 1179)
 * @param setPurchaserContactData Контактные данные покупателя, на которые будет отправлен чек
 */
class OpenCorrectionOutcomeReceiptCommand(
    val changes: List<PositionAdd>,
    val extra: SetExtra? = null,
    @FiscalRequisite(tag = FiscalTags.CORRECTABLE_SETTLEMENT_DATE)
    val correctionDate: Date,
    @FiscalRequisite(tag = FiscalTags.CORRECTION_TYPE)
    val correctionType: CorrectionType,
    @FiscalRequisite(tag = FiscalTags.PRESCRIPTION_NUMBER)
    val prescription: String? = null,
    val setPurchaserContactData: SetPurchaserContactData? = null
) : IBundlable {

    companion object {
        const val NAME = "evo.v2.receipt.correction.outcome.openReceipt"
        private const val KEY_CHANGES = "changes"
        private const val KEY_RECEIPT_EXTRA = "extra"
        private const val KEY_CORRECTION_DATE = "correctionDate"
        private const val KEY_CORRECTION_TYPE = "correctionType"
        private const val KEY_PRESCRIPTION = "prescription"
        private const val KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA = "setPurchaserContactData"

        @JvmStatic
        fun create(bundle: Bundle?): OpenCorrectionOutcomeReceiptCommand? {
            return bundle?.let {
                OpenCorrectionOutcomeReceiptCommand(
                    changes = Utils.filterByClass(
                        ChangesMapper.create(
                            it.getParcelableArray(
                                KEY_CHANGES
                            )
                        ), PositionAdd::class.java
                    ),
                    extra = SetExtra.from(it.getBundle(KEY_RECEIPT_EXTRA)),
                    correctionDate = Date(it.getLong(KEY_CORRECTION_DATE)),
                    correctionType = CorrectionType.valueOf(it.getString(KEY_CORRECTION_TYPE) as String),
                    prescription = it.getString(KEY_PRESCRIPTION),
                    setPurchaserContactData = SetPurchaserContactData.from(
                        it.getBundle(
                            KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA
                        )
                    )
                )
            }
        }
    }

    fun process(activity: Activity, callback: IntegrationManagerCallback) {
        IntegrationManagerImpl
            .convertImplicitIntentToExplicitIntent(NAME, activity.applicationContext)
            .firstOrNull()
            ?.let { component ->
                IntegrationManagerImpl(activity.applicationContext).call(
                    NAME,
                    component,
                    this,
                    activity,
                    callback,
                    Handler(Looper.getMainLooper())
                )
            }
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putParcelableArray(KEY_CHANGES, changes.map { toBundle(it) }.toTypedArray())
            putBundle(KEY_RECEIPT_EXTRA, extra?.toBundle())
            putLong(KEY_CORRECTION_DATE, correctionDate.time)
            putString(KEY_CORRECTION_TYPE, correctionType.name)
            putString(KEY_PRESCRIPTION, prescription)
            putBundle(
                KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA,
                setPurchaserContactData?.toBundle()
            )
        }
    }
}
