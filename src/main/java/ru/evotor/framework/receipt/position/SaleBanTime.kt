package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable

class SaleBanTime() : IBundlable {
    var startHours: Int = 0
        private set
    var startMinutes: Int = 0
        private set
    var endHours: Int = 0
        private set
    var endMinutes: Int = 0
        private set

    constructor(
        startHours: Int,
        startMinutes: Int,
        endHours: Int,
        endMinutes: Int
    ) : this() {
        if (
            !validateHours(startHours) ||
            !validateMinutes(startMinutes) ||
            !validateHours(endHours) ||
            !validateMinutes(endMinutes)
        ) {
            throw IllegalArgumentException("Incorrect time")
        }
        this.startHours = startHours
        this.startMinutes = startMinutes
        this.endHours = endHours
        this.endMinutes = endMinutes
    }

    constructor(
        startTime: String,
        endTime: String
    ) : this() {
        val start = parseHoursAndMinutes(startTime)
        val end = parseHoursAndMinutes(endTime)
        this.startHours = start.first
        this.startMinutes = start.second
        this.endHours = end.first
        this.endMinutes = end.second
    }


    private fun parseHoursAndMinutes(time: String): Pair<Int, Int> {
        if (time.matches(Regex("""\d{2}:\d{2}"""))) {
            val (hours, minutes) = time.split(":").map { it.toInt() }

            if (validateHours(hours) && validateMinutes(minutes)) {
                return hours to minutes
            } else {
                throw IllegalArgumentException("Incorrect time $time")
            }
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

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putInt(KEY_START_HOURS, startHours)
            putInt(KEY_START_MINUTES, startMinutes)
            putInt(KEY_END_HOURS, endHours)
            putInt(KEY_END_MINUTES, endMinutes)
        }
    }

    override fun toString(): String {
        return "from $startHours:$startMinutes to $endHours:$endMinutes"
    }

    companion object {
        private const val KEY_START_HOURS = "START_HOURS"
        private const val KEY_START_MINUTES = "START_MINUTES"
        private const val KEY_END_HOURS = "END_HOURS"
        private const val KEY_END_MINUTES = "END_MINUTES"

        @JvmStatic
        fun from(bundle: Bundle?): SaleBanTime? {
            return bundle?.let {
                SaleBanTime(
                    it.getInt(KEY_START_HOURS),
                    it.getInt(KEY_START_MINUTES),
                    it.getInt(KEY_END_HOURS),
                    it.getInt(KEY_END_MINUTES))
            }
        }
    }
}
