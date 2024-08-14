package in.kpmg.hrms.payroll.utils;

import java.time.Month;
import java.time.Year;

public class MonthToDayUtlity {

    public static int getDaysInMonth(String monthName, int year) {
        // Convert the month name to uppercase to match the Month enum
        monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);

        // Get the Month enum from the month name
        Month month;
        try {
            month = Month.valueOf(monthName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid month name: " + monthName);
        }

        // Get the number of days in the month for the given year
        return month.length(Year.isLeap(year));
    }

}
