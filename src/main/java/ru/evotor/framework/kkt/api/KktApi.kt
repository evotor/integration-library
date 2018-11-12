package ru.evotor.framework.kkt.api

import android.content.Context
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.kkt.FfdVersion
import ru.evotor.framework.kkt.provider.KktContract
import ru.evotor.framework.optInt
import ru.evotor.framework.optList

/**
 * API для работы с кассой.
 */
object KktApi {

    /**
     * Получает поддерживаемую кассой версию ФФД.
     */
    @JvmStatic
    fun getSupportedFfdVersion(context: Context): FfdVersion = context.contentResolver.query(
            KktContract.BASE_URI,
            arrayOf(KktContract.COLUMN_SUPPORTED_FFD_VERSION),
            null,
            null,
            null
    )?.use { cursor ->
        cursor.moveToFirst()
        cursor.optInt(KktContract.COLUMN_SUPPORTED_FFD_VERSION)?.let { version ->
            FfdVersion.values()[version]
        }
    } ?: FfdVersion.UNKNOWN

    /**
     * Получает список типов агентов, которые были указаны при регистрации кассы.
     */
    @JvmStatic
    fun getRegisteredAgentTypes(context: Context): List<Agent.Type>? = context.contentResolver.query(
            KktContract.BASE_URI,
            arrayOf(KktContract.COLUMN_REGISTERED_AGENT_TYPES),
            null,
            null,
            null
    )?.use { cursor ->
        cursor.moveToFirst()
        cursor.optList(KktContract.COLUMN_REGISTERED_AGENT_TYPES)?.map { item ->
            Agent.Type.values()[item.toInt()]
        }
    }

    /**
     * Получает список типов субагентов, которые были указаны при регистрации кассы.
     */
    @JvmStatic
    fun getRegisteredSubagentTypes(context: Context): List<Subagent.Type>? = context.contentResolver.query(
            KktContract.BASE_URI,
            arrayOf(KktContract.COLUMN_REGISTERED_SUBAGENT_TYPES),
            null,
            null,
            null
    )?.use { cursor ->
        cursor.moveToFirst()
        cursor.optList(KktContract.COLUMN_REGISTERED_SUBAGENT_TYPES)?.map { item ->
            Subagent.Type.values()[item.toInt()]
        }
    }

}