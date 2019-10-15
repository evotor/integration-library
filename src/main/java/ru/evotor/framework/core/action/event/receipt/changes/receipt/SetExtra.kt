package ru.evotor.framework.core.action.event.receipt.changes.position

import android.os.Bundle
import org.json.JSONObject
import ru.evotor.framework.core.action.event.receipt.changes.IChange

/**
 * Добавляет в чек дополнительные поля в виде валидного JSON-объекта.
 */
data class SetExtra(val extra: JSONObject?) : IChange {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(
                    KEY_EXTRA,
                    extra?.toString()
            )
        }
    }

    override fun getType(): IChange.Type {
        return IChange.Type.SET_EXTRA
    }

    companion object {
        const val KEY_EXTRA = "extra"

        @JvmStatic
        fun from(bundle: Bundle?): SetExtra? {
            bundle ?: return null

            return SetExtra(
                    bundle.getString(KEY_EXTRA)?.let { JSONObject(it) }
            )
        }
    }
}