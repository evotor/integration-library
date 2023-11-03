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
    val checkTimestamp: Long
) : IBundlable {
    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_CHECK_ID, checkId)
        putString(KEY_TIMESTAMP, checkTimestamp.toString())
    }

    companion object {

        private const val KEY_CHECK_ID = "CheckId"
        private const val KEY_TIMESTAMP = "Timestamp"

        @JvmStatic
        fun from(bundle: Bundle?): MarksCheckingInfo? = bundle?.let {
            val checkId = it.getString(KEY_CHECK_ID) ?: return null
            val checkTimestamp = it.optLong(KEY_TIMESTAMP) ?: return null

            MarksCheckingInfo(checkId = checkId, checkTimestamp = checkTimestamp)
        }
    }
}
