package ru.liga.karmatskiyrg.distributed.app.client.utils.dates;

import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.interfaces.DateInterval;

import java.time.LocalDate;

public class TomorrowPeriod extends DatePeriod implements DateInterval {
    public TomorrowPeriod() {
        super(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(1)
        );
    }
}
