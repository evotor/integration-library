package ru.evotor.framework.ui

import android.graphics.drawable.Drawable
import java.util.*

data class IntegrationUiComponent internal constructor(
        val type: Type,
        val appUuid: UUID,
        val backgroundColor: Int?,
        val icon: Drawable?,
        val label: String,
        val labelColor: Int
) {
    enum class Type {
        PAYMENT_METHOD
    }
}