package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.receipt.position.Mark

object MarkMapper {

    const val KEY_MARK_ENTITY = "markEntity"

    @JvmStatic
    fun toBundle(mark: Mark): Bundle =
            Bundle().apply {
                putParcelable(KEY_MARK_ENTITY, mark)
            }

    @JvmStatic
    fun fromBundle(bundle: Bundle?): Mark? {
        if (bundle == null) return null
        if (!bundle.containsKey(KEY_MARK_ENTITY)) return null

        return with(bundle) {
            classLoader = Mark::class.java.classLoader
            getParcelable<Mark>(KEY_MARK_ENTITY)
        }
    }
}