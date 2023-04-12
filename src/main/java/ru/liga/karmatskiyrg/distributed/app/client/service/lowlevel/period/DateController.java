package ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period;

import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DateInterval;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateController {
    private static final String MESSAGE_KEY = "No -DATE arguments among %s";

    public static DateInterval toDateInterval(String dateString) {
        var date = LocalDate.parse(
                dateString,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
        );
        return DateInterval.of(date, date);
    }

    public DateInterval get(String dateString) {
        return toDateInterval(dateString);
    }

}
