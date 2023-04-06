package ru.liga.karmatskiyrg.service.lowlevel.currency;

import ru.liga.karmatskiyrg.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.service.lowlevel.interfaces.LowLevelController;

import java.util.Map;

public class CurrencyController implements LowLevelController<DCurrencyType> {
    private static final String MESSAGE_KEY = "No -CUR argument among %s";
    private static final String MESSAGE_VAL = "No such currency = %s";

    @Override
    public DCurrencyType get(Map<DArgumentType, String> map) {
        if (!map.containsKey(DArgumentTypes.CUR)) {
            throw new NotValidCommand(MESSAGE_KEY.formatted(map.keySet()));
        }
        var row = map.get(DArgumentTypes.CUR);
//        var res = DCurrencyTypes.getType(row);
        var res = DCurrencyTypes.getShortNameType(row);
        if (res == null) {
            throw new NotValidCommand(MESSAGE_VAL.formatted(row));
        }
        return res;
    }
}
