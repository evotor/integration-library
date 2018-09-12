package ru.evotor.framework


import android.graphics.Color
import android.support.annotation.ColorInt

/**
 * Created by Khaustov on 06.02.18.
 */
object ColorUtils {
    private const val RED_MULTIPLIER = 0.299
    private const val GREEN_MULTIPLIER = 0.587
    private const val BLUE_MULTIPLIER = 0.114
    private const val RGB_MAX_COLOR_VALUE = 255

    @JvmStatic
    @ColorInt
    fun getContrastColor(@ColorInt color: Int): Int {
        val rgbValue = 1 - (RED_MULTIPLIER * Color.red(color) + GREEN_MULTIPLIER * Color.green(color)
                + BLUE_MULTIPLIER * Color.blue(color)) / RGB_MAX_COLOR_VALUE
        return if (rgbValue < 0.5) Color.BLACK else Color.WHITE
    }
}