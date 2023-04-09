package ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period;

import ru.liga.karmatskiyrg.distributed.app.client.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.dates.DDateTypes;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.interfaces.DatePeriodController;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DateInterval;

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
