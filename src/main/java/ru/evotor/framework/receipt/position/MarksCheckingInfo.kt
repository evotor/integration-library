package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.optLong

/**
 * Данные об онлайн-проверке марки
 */
data class MarksCheckingInfo(
    /**
     * Идентификатор онлайн-проверки марки
     */
    val checkId: String,
    /**
     * Время онлайн проверки
     */
    val checkTimestamp: Long,
    /**
     * Информация о локальном модуле
     */
    val localModuleInfo: LocalModuleInfo?
) : IBundlable {
    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_CHECK_ID, checkId)
        putLong(KEY_TIMESTAMP, checkTimestamp)
        putBundle(KEY_LOCAL_MODULE, localModuleInfo?.toBundle())
    }

    companion object {
        private const val KEY_CHECK_ID = "CheckId"
        private const val KEY_TIMESTAMP = "Timestamp"
        private const val KEY_LOCAL_MODULE = "LocalModule"

        @JvmStatic
        fun from(bundle: Bundle?): MarksCheckingInfo? = bundle?.let {
            val checkId = it.getString(KEY_CHECK_ID) ?: return null
            val checkTimestamp = it.optLong(KEY_TIMESTAMP) ?: return null
            val localModuleInfoBundle = it.getBundle(KEY_LOCAL_MODULE)
            val localModuleInfo = LocalModuleInfo.from(localModuleInfoBundle)

            MarksCheckingInfo(
                checkId = checkId,
                checkTimestamp = checkTimestamp,
                localModuleInfo = localModuleInfo
            )
        }
    }
}
