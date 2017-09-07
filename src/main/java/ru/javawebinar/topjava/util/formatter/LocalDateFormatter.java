package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Brad on 07.09.2017.
 */
public final class LocalDateFormatter implements Formatter<LocalDate> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDateFormatter() {
    }

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {

        return StringUtils.isEmpty(text) ? null : LocalDate.parse(text);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return localDate == null ? "" : localDate.format(DATE_FORMATTER);
    }
}
