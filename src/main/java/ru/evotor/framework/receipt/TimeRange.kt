package ru.evotor.framework.receipt

import android.os.Bundle
import ru.evotor.IBundlable
import java.util.Locale
import kotlin.Throws

/**
 * @param startHours часы начала, может принимать значения от 0 до 23
 * @param startMinutes минуты начала, может принимать значения от 0 до 59
 * @param endHours часы окончания, может принимать значения от 0 до 23
 * @param endMinutes минуты окончания, может принимать значения от 0 до 59
 *
 * Кидает IllegalArgumentException, если время передано некорректно
 */
data class TimeRange(
    val startHours: Int,
    val startMinutes: Int,
    val endHours: Int,
    val endMinutes: Int
) : IBundlable {

    init {
        if (!validateHours(startHours) || !validateMinutes(startMinutes)) {
            throw IllegalArgumentException("Incorrect time ${String.format(Locale.getDefault(), "%02d:%02d", startHours, startMinutes)}")
        }
        if (!validateHours(endHours) || !validateMinutes(endMinutes)) {
            throw IllegalArgumentException("Incorrect time ${String.format(Locale.getDefault(), "%02d:%02d", endHours, endMinutes)}")
        }
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putInt(KEY_START_HOURS, startHours)
            putInt(KEY_START_MINUTES, startMinutes)
            putInt(KEY_END_HOURS, endHours)
            putInt(KEY_END_MINUTES, endMinutes)
        }
    }

    override fun toString(): String {
        return String.format(Locale.getDefault(), "%02d:%02d-%02d:%02d", startHours, startMinutes, endHours, endMinutes)
    }

    companion object {

        @JvmStatic
        fun from(bundle: Bundle?): TimeRange? {
            return bundle?.let {
                TimeRange(
                    startHours = it.getInt(KEY_START_HOURS),
                    startMinutes = it.getInt(KEY_START_MINUTES),
                    endHours = it.getInt(KEY_END_HOURS),
                    endMinutes = it.getInt(KEY_END_MINUTES)
                )
            }
        }

        /**
         * Метод создания объекта TimeRange
         *
         * @param startTime время начала в формате HH:mm
         * @param endTime время окончания в формате HH:mm
         *
         * HH может принимать значения от 0 до 23,
         * mm может  принимать значения от 0 до 59
         *
         * Кидает IllegalArgumentException, если время передано некорректно
         */
        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun create(
            startTime: String,
            endTime: String
        ): TimeRange {
            val start = parseAndCheckTime(startTime)
            val end = parseAndCheckTime(endTime)
            return TimeRange(
                startHours = start.first,
                startMinutes = start.second,
                endHours = end.first,
                endMinutes = end.second
            )
        }

        private fun parseAndCheckTime(time: String): Pair<Int, Int> {
            val pair = parseHoursAndMinutes(time)
            val hours = pair.first
            val minutes = pair.second

            return hours to minutes
        }

        private fun parseHoursAndMinutes(time: String): Pair<Int, Int> {
            if (time.matches(parseTimeRegex)) {
                val (hours, minutes) = time.split(":").map { it.toInt() }
                return hours to minutes
            } else {
                throw IllegalArgumentException("Incorrect time $time")
            }
        }

        private fun validateMinutes(minutes: Int): Boolean {
            return minutes in 0..59
        }

        private fun validateHours(hours: Int): Boolean {
            return hours in 0..23
        }

        private val parseTimeRegex by lazy { Regex("""\d{2}:\d{2}""") }

        private const val KEY_START_HOURS = "START_HOURS"
        private const val KEY_START_MINUTES = "START_MINUTES"
        private const val KEY_END_HOURS = "END_HOURS"
        private const val KEY_END_MINUTES = "END_MINUTES"
    }
}
