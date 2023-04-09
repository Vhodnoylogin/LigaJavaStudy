package ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.fabric;

import ru.liga.karmatskiyrg.distributed.app.client.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.DateController;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.PeriodController;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.interfaces.DatePeriodController;

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
