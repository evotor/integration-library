package ru.evotor.framework.kkt.api

import android.content.Context
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.startIntegrationService
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.kkt.FfdVersion
import ru.evotor.framework.kkt.event.CorrectionReceiptRegistrationRequestedEvent
import ru.evotor.framework.kkt.event.handler.service.KktIntegrationServiceInternal
import ru.evotor.framework.kkt.provider.KktContract
import ru.evotor.framework.payment.PaymentType
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.TaxNumber
import ru.evotor.framework.receipt.TaxationSystem
import ru.evotor.framework.receipt.correction.CorrectionType
import ru.evotor.framework.safeGetBoolean
import ru.evotor.framework.safeGetInt
import ru.evotor.framework.safeGetList
import java.math.BigDecimal
import java.util.*

/**
 * API для работы с кассой.
 */
object KktApi {
    /**
     * Получает версию ФФД, на которую была зарегистрирована касса.
     * @return FfdVersion или null, если не удалось связаться с кассой
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученную версию ФФД
     */
    @JvmStatic
    fun getRegisteredFfdVersion(context: Context): FfdVersion? = context.contentResolver.query(
            KktContract.BASE_URI,
            arrayOf(KktContract.COLUMN_SUPPORTED_FFD_VERSION),
            null,
            null,
            null
    )?.use { cursor ->
        cursor.moveToFirst()
        cursor.safeGetInt(KktContract.COLUMN_SUPPORTED_FFD_VERSION)?.let { version ->
            if (version !in 0..FfdVersion.values().size) {
                throw IntegrationLibraryMappingException(FfdVersion::class.java.name)
            }
            FfdVersion.values()[version]
        }
    }

    /**
     * Получает список типов агентов, которые были указаны при регистрации кассы.
     * @return List<Agent.Type> или null, если в кассе не зарегистрированы типы агентов, либо если
     * не удалось связаться с кассой.
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученные типы
     * агентов.
     */
    @JvmStatic
    fun getRegisteredAgentTypes(context: Context): List<Agent.Type>? =
            context.contentResolver.query(
                    KktContract.BASE_URI,
                    arrayOf(KktContract.COLUMN_REGISTERED_AGENT_TYPES),
                    null,
                    null,
                    null
            )?.use { cursor ->
                cursor.moveToFirst()
                cursor.safeGetList(KktContract.COLUMN_REGISTERED_AGENT_TYPES)?.map { item ->
                    item.toInt().let { index ->
                        if (index !in 0..Agent.Type.values().size) {
                            throw IntegrationLibraryMappingException(Agent.Type::class.java.name)
                        }
                        Agent.Type.values()[index]
                    }
                }
            }

    /**
     * Получает список типов субагентов, которые были указаны при регистрации кассы.
     * @return List<Subagent.Type> или null, если в кассе не зарегистрированы типы субагентов, либо
     * если не удалось связаться с кассой.
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученные типы
     * субагентов.
     */
    @JvmStatic
    fun getRegisteredSubagentTypes(context: Context): List<Subagent.Type>? =
            context.contentResolver.query(
                    KktContract.BASE_URI,
                    arrayOf(KktContract.COLUMN_REGISTERED_SUBAGENT_TYPES),
                    null,
                    null,
                    null
            )?.use { cursor ->
                cursor.moveToFirst()
                cursor.safeGetList(KktContract.COLUMN_REGISTERED_SUBAGENT_TYPES)?.map { item ->
                    item.toInt().let { index ->
                        if (index !in 0..Subagent.Type.values().size) {
                            throw IntegrationLibraryMappingException(Subagent.Type::class.java.name)
                        }
                        Subagent.Type.values()[index]
                    }
                }
            }

    /**
     * Установлен ли на терминал пакет обновлений с возможностью пробивать фискальные документы по
     * ставке НДС 20%.
     * @return Boolean или null, если не удалось связаться с кассой.
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученное значение
     */
    @JvmStatic
    fun isVatRate20Available(context: Context): Boolean? =
            context.contentResolver.query(
                    KktContract.BASE_URI,
                    arrayOf(KktContract.COLUMN_IS_VAT_RATE_20_AVAILABLE),
                    null,
                    null,
                    null
            )?.use { cursor ->
                cursor.moveToFirst()
                cursor.safeGetBoolean(KktContract.COLUMN_IS_VAT_RATE_20_AVAILABLE)
                        ?: throw IntegrationLibraryMappingException(KktApi::isVatRate20Available.name)
            }

    @JvmStatic
    fun registerCorrectionReceipt(
            context: Context,
            settlementType: SettlementType,
            taxationSystem: TaxationSystem,
            sum: BigDecimal,
            paymentType: PaymentType,
            taxNumber: TaxNumber,
            correctionType: CorrectionType,
            basisForCorrection: String,
            correctionDescription: String?,
            correctableSettlementDate: Date,
            prescriptionNumber: String,
            callback: IntegrationManagerCallback
    ) {
        context.startIntegrationService(
                KktIntegrationServiceInternal.ACTION_CORRECTION_RECEIPT_REGISTRATION_REQUESTED,
                CorrectionReceiptRegistrationRequestedEvent(
                        taxationSystem,
                        settlementType,
                        sum,
                        paymentType,
                        taxNumber,
                        correctionType,
                        basisForCorrection,
                        correctionDescription,
                        correctableSettlementDate,
                        prescriptionNumber
                ),
                callback
        )
    }
}