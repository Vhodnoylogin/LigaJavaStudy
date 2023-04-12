package ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period;

import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.interfaces.PeriodController;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DateInterval;

import java.time.LocalDate;

public class WeekController implements PeriodController {
    private static final String MESSAGE_KEY = "No -PERIOD arguments among %s";
    private static final String MESSAGE_VAL = "No such currency = %s";


    public DateInterval get() {
        return DateInterval.of(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7)
        );
    }

}
