package ru.liga.karmatskiyrg.service.lowlevel.period;

import ru.liga.karmatskiyrg.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.model.dicts.dates.DDateTypes;
import ru.liga.karmatskiyrg.service.lowlevel.period.interfaces.DatePeriodController;
import ru.liga.karmatskiyrg.utils.dates.DateInterval;

import java.time.LocalDate;
import java.util.Map;

public class PeriodController implements DatePeriodController {
    private static final String MESSAGE_KEY = "No -PERIOD arguments among %s";
    private static final String MESSAGE_VAL = "No such currency = %s";

    @Override
    public DateInterval get(Map<DArgumentType, String> map) {
        if (!map.containsKey(DArgumentTypes.PERIOD)) {
            throw new NotValidCommand(MESSAGE_KEY.formatted(map.keySet()));
        }
        var row = map.get(DArgumentTypes.PERIOD);
        var res = DDateTypes.getType(row);
        if (res == null) {
            throw new NotValidCommand(MESSAGE_VAL.formatted(row));
        }

        if (DDateTypes.TMR == res) {
            var date = LocalDate.now().plusDays(1);
            return DateInterval.of(date, date);
        } else if (DDateTypes.WEK == res) {
            return DateInterval.of(
                    LocalDate.now().plusDays(1),
                    LocalDate.now().plusDays(7)
            );
        } else {
            throw new NotValidCommand(MESSAGE_VAL.formatted(row));
        }
    }

}
