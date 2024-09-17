package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable

data class SaleBanTime(
    val startTime: String,
    val endTime: String
) : IBundlable {

    init {
        checkTimeCorrectness(startTime)
        checkTimeCorrectness(endTime)
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_START_TIME, startTime)
            putString(KEY_END_TIME, endTime)
        }
    }

    private fun checkTimeCorrectness(time: String) {
        val pair = parseHoursAndMinutes(time)
        val hours = pair.first
        val minutes = pair.second

        if (!validateHours(hours) || !validateMinutes(minutes)) {
            throw IllegalArgumentException("Incorrect time $time")
        }
    }

    private fun validateMinutes(minutes: Int): Boolean {
        return minutes in 0..59
    }

    private fun validateHours(hours: Int): Boolean {
        return hours in 0..23
    }

    override fun toString(): String {
        return "$startTime-$endTime"
    }

    companion object {
        private const val KEY_START_TIME = "START_TIME"
        private const val KEY_END_TIME = "END_TIME"

        fun parseHoursAndMinutes(time: String): Pair<Int, Int> {
            if (time.matches(Regex("""\d{2}:\d{2}"""))) {
                val (hours, minutes) = time.split(":").map { it.toInt() }
                return hours to minutes
            } else {
                throw IllegalArgumentException("Incorrect time $time")
            }
        }

        @JvmStatic
        fun from(bundle: Bundle?): SaleBanTime? {
            return bundle?.let {
                SaleBanTime(
                    it.getString(KEY_START_TIME) ?: return null,
                    it.getString(KEY_END_TIME) ?: return null
                )
            }
        }
    }
}
