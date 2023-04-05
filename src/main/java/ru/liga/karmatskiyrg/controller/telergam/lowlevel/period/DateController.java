package ru.liga.karmatskiyrg.controller.telergam.lowlevel.period;

import ru.liga.karmatskiyrg.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.controller.telergam.lowlevel.period.interfaces.DatePeriodController;
import ru.liga.karmatskiyrg.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.utils.dates.DateInterval;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DateController implements DatePeriodController {
    private static final String MESSAGE_KEY = "No -DATE arguments among %s";

    @Override
    public DateInterval get(Map<DArgumentType, String> map) {
        if (!map.containsKey(DArgumentTypes.DATE)) {
            throw new NotValidCommand(MESSAGE_KEY.formatted(map.keySet()));
        }
        var date = LocalDate.parse(
                map.get(DArgumentTypes.DATE),
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
        );
        return DateInterval.of(date, date);
    }

}
