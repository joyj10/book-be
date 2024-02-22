package com.won.bookcommon.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTimeUtil
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
public class LocalDateTimeUtil {
    private LocalDateTimeUtil() { throw new IllegalStateException("Utility class"); }

    public static final String DATE_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String DATE_FULL_DASH = "yyyy-MM-dd";

    public static String toString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FULL_DASH);
        return localDate.format(formatter);
    }
    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FULL_DASH));
    }

    public static LocalDate getFirstDate(int year, int month) {
        return LocalDate.of(year, month, 1);
    }

    public static LocalDate getLastDate(int year, int month) {
        YearMonth yearMonth = YearMonth.from(LocalDate.of(year, month, 1));
        return yearMonth.atEndOfMonth();
    }
}
