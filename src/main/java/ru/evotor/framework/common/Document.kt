package ru.evotor.framework.common

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.FutureFeature
import ru.evotor.framework.common.mapper.DocumentMapper
import java.util.*

abstract class Document internal constructor(): IBundlable {
    @FutureFeature("Uuid документа, зарегистрированного в системе Эвотор")
    protected open val uuid: UUID? = null

    override fun toBundle(): Bundle = DocumentMapper.write(this)
}