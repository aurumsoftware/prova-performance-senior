class Calculator {
  calculateProportionalDifferencePrice(
    oldPrice,
    newPrice,
    dueDate,
    recurrencyType
  ) {
    const dueDateFormat = new Date(dueDate);
    let monthsOfRecurrence;
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
        throw new Error("Unknown recurrence type");
    }

    const startDate = new Date(dueDateFormat);
    startDate.setMonth(dueDateFormat.getMonth() - monthsOfRecurrence);

    const today = new Date();

    const daysBetween = (date1, date2) => {
      const diffInMs = Math.abs(date2.getTime() - date1.getTime());
      return Math.ceil(diffInMs / (1000 * 60 * 60 * 24));
    };

    const daysLeft = daysBetween(startDate, today);

    if (daysLeft > 0) {
      const totalPlanDays = daysBetween(startDate, dueDateFormat);
      const remainingDays = totalPlanDays - daysLeft;

      let totalAmountRemainingPayed = 0.0;
      if (remainingDays > 0) {
        const currentPriceByDay = oldPrice / totalPlanDays;
        totalAmountRemainingPayed = currentPriceByDay * remainingDays;
      }

      const newPriceByDay = newPrice / totalPlanDays;
      const totalAmountRemainingNotPayed = newPriceByDay * remainingDays;

      return totalAmountRemainingNotPayed - totalAmountRemainingPayed;
    } else {
      return newPrice - oldPrice;
    }
  }
}

module.exports = Calculator;