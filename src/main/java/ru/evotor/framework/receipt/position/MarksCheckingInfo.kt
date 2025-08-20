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
     * Идентификатор экземпляра ЛМ ЧЗ
     */
    val inst: String?,
    /**
     * Версия базы ЛМ ЧЗ, на которой осуществлялась проверка
     */
    val lmChzDbVersion: String?
) : IBundlable {
    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_CHECK_ID, checkId)
        putLong(KEY_TIMESTAMP, checkTimestamp)
        putString(KEY_LM_CHZ_ID, inst)
        putString(KEY_LM_CHZ_DB_Version, lmChzDbVersion)
    }

    companion object {

        private const val KEY_CHECK_ID = "CheckId"
        private const val KEY_TIMESTAMP = "Timestamp"
        private const val KEY_LM_CHZ_ID = "LmChzId"
        private const val KEY_LM_CHZ_DB_Version = "LmChzDbVersion"

        @JvmStatic
        fun from(bundle: Bundle?): MarksCheckingInfo? = bundle?.let {
            val checkId = it.getString(KEY_CHECK_ID) ?: return null
            val checkTimestamp = it.optLong(KEY_TIMESTAMP) ?: return null
            val inst = it.getString(KEY_LM_CHZ_ID)
            val lmChzDbVersion = it.getString(KEY_LM_CHZ_DB_Version)

            MarksCheckingInfo(
                checkId = checkId,
                checkTimestamp = checkTimestamp,
                inst = inst,
                lmChzDbVersion = lmChzDbVersion
            )
        }
    }
}
