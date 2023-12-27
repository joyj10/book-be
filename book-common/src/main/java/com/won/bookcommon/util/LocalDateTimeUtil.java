package com.won.bookcommon.util;

import java.time.LocalDate;
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

    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FULL_DASH));
    }

}
