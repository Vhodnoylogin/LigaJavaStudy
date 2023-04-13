package ru.liga.karmatskiyrg.distributed.app.client.utils.dates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate toDate(String dateString) {
        return LocalDate.parse(dateString, FORMAT);
    }
}
