package ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period;

import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.interfaces.PeriodController;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DateInterval;

import java.time.LocalDate;

public class TomorrowController implements PeriodController {

    public DateInterval get() {
        return DateInterval.of(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(1)
        );
    }

}
