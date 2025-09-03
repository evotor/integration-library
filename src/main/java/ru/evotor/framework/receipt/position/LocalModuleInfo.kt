package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable

/**
 * Данные о локальном модуле ЧЗ
 */
data class LocalModuleInfo(
    /**
     * Идентификатор экземпляра ЛМ ЧЗ
     */
    val inst: String,
    /**
     * Версия базы ЛМ ЧЗ, на которой осуществлялась проверка
     */
    val lmChzDbVersion: String
) : IBundlable {
    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_LM_CHZ_ID, inst)
        putString(KEY_LM_CHZ_DB_Version, lmChzDbVersion)
    }

    companion object {
        private const val KEY_LM_CHZ_ID = "LmChzId"
        private const val KEY_LM_CHZ_DB_Version = "LmChzDbVersion"

        @JvmStatic
        fun from(bundle: Bundle?): LocalModuleInfo? = bundle?.let {
            val inst = it.getString(KEY_LM_CHZ_ID) ?: return null
            val lmChzDbVersion = it.getString(KEY_LM_CHZ_DB_Version) ?: return null

            LocalModuleInfo(
                inst = inst,
                lmChzDbVersion = lmChzDbVersion
            )
        }
    }
}
