package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.receipt.position.Mark

object MarkMapper {

    private const val KEY_MARK_ENTITY = "markEntity"

    @JvmStatic
    fun toBundle(mark: Mark): Bundle =
            Bundle().apply {
                putParcelable(KEY_MARK_ENTITY, mark)
            }

    @JvmStatic
    fun fromBundle(bundle: Bundle?): Mark? {
        return bundle?.let {
            it.classLoader = Mark::class.java.classLoader
            it.getParcelable<Mark>(KEY_MARK_ENTITY)
        }
    }
}