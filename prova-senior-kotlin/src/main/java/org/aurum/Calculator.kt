package org.aurum

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Calculator {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Calculator().calculateProportionalDifferencePrice(100.0, 300.0, "2025-04-16", "MONTHLY"))
        }
    }

    fun calculateProportionalDifferencePrice(
        oldPrice: Double,
        newPrice: Double,
        dueDate: String,
        recurrencyType: String
    ): Double {
        val dueDateFormat = LocalDate.parse(dueDate)
        val monthsOfRecurrence = when (recurrencyType) {
            "MONTHLY" -> 1
            "SEMIANNUAL" -> 6
            "YEARLY", "YEARLY_SPLITED" -> 12
            else -> throw IllegalArgumentException("Unknown recurrence type")
        }
        val startDateFormat = dueDateFormat.minusMonths(monthsOfRecurrence.toLong())
        val today = LocalDate.now()
        val daysLeft = ChronoUnit.DAYS.between(startDateFormat, today)

        return if (daysLeft > 0) {
            val totalPlanDays = ChronoUnit.DAYS.between(startDateFormat, dueDateFormat)
            val remainingDays = totalPlanDays - daysLeft
            val totalAmountRemainingPayed = if (remainingDays > 0) {
                val currentPriceByDay = oldPrice / totalPlanDays
                currentPriceByDay * remainingDays
            } else {
                0.0
            }
            val newPriceByDay = newPrice / totalPlanDays
            val totalAmountRemainingNotPayed = newPriceByDay * remainingDays
            totalAmountRemainingNotPayed - totalAmountRemainingPayed
        } else {
            newPrice - oldPrice
        }
    }
}