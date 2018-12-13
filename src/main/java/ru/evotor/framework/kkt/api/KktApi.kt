package ru.evotor.framework.kkt.api

import android.content.Context
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.kkt.FfdVersion
import ru.evotor.framework.kkt.provider.KktContract
import ru.evotor.framework.safeGetInt
import ru.evotor.framework.safeGetList

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
}