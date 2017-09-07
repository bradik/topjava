package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Brad on 07.09.2017.
 */
public final class LocalTimeFormatter implements Formatter<LocalTime> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public LocalTimeFormatter() {
    }

    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {

        return StringUtils.isEmpty(text) ? null : LocalTime.parse(text);
    }

    @Override
    public String print(LocalTime localDate, Locale locale) {
        return localDate == null ? "" : localDate.format(DATE_FORMATTER);
    }

}
