package ru.liga.karmatskiyrg.controller.telergam.lowlevel.period.fabric;

import ru.liga.karmatskiyrg.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.controller.telergam.lowlevel.period.DateController;
import ru.liga.karmatskiyrg.controller.telergam.lowlevel.period.PeriodController;
import ru.liga.karmatskiyrg.controller.telergam.lowlevel.period.interfaces.DatePeriodController;
import ru.liga.karmatskiyrg.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;

import java.util.Map;

public class DatePeriodFabric {
    private static final String MESSAGE_KEY = "No -CUR arguments among %s";

    public static DatePeriodController getDatePeriod(Map<DArgumentType, String> map) {
        if (map.containsKey(DArgumentTypes.DATE)
                && map.containsKey(DArgumentTypes.PERIOD)
        ) {
            throw new NotValidCommand("Contains exclusive arguments: %s && %s".formatted(DArgumentTypes.DATE, DArgumentTypes.PERIOD));
        }
        if (map.containsKey(DArgumentTypes.DATE)) {
            return new DateController();
        } else if (map.containsKey(DArgumentTypes.PERIOD)) {
            return new PeriodController();
        } else {
            throw new NotValidCommand(MESSAGE_KEY.formatted(map.keySet()));
        }
    }
}
