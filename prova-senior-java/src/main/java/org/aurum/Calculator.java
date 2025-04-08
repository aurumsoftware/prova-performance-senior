package org.aurum;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Calculator {

	public static void main(String[] args) {
		System.out.println(new Calculator().calculateProportionalDifferencePrice(200, 100, "2025-04-24", "MONTHLY"));
	}



	public double calculateProportionalDifferencePrice(
			double oldPrice,
			double newPrice,
			String dueDate,
			String recurrencyType
	) {
		LocalDate dueDateFormat = LocalDate.parse(dueDate);
		int monthsOfRecurrence;

		switch (recurrencyType) {
			case "MONTHLY":
				monthsOfRecurrence = 1;
				break;
			case "SEMIANNUAL":
				monthsOfRecurrence = 6;
				break;
			case "YEARLY":
			case "YEARLY_SPLITED":
				monthsOfRecurrence = 12;
				break;
			default:
				throw new IllegalArgumentException("Unknown recurrence type");
		}

		LocalDate startDateFormat = dueDateFormat.minusMonths(monthsOfRecurrence);
		LocalDate today = LocalDate.now();
		long daysLeft = ChronoUnit.DAYS.between(startDateFormat, today);

		if (daysLeft > 0) {
			long totalPlanDays = ChronoUnit.DAYS.between(startDateFormat, dueDateFormat);
			long remainingDays = totalPlanDays - daysLeft;
			double totalAmountRemainingPayed = 0.0;
			if (remainingDays > 0) {
				double currentPriceByDay = oldPrice / totalPlanDays;
				totalAmountRemainingPayed = currentPriceByDay * remainingDays;
			}
			double newPriceByDay = newPrice / totalPlanDays;
			double totalAmountRemainingNotPayed = newPriceByDay * remainingDays;
			return totalAmountRemainingNotPayed - totalAmountRemainingPayed;
		} else {
			return newPrice - oldPrice;
		}
	}
}
