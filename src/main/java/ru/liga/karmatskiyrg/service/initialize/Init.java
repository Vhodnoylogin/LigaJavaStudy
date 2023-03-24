package ru.liga.karmatskiyrg.service.initialize;

import ru.liga.karmatskiyrg.controller.observers.dicts.IsCommandString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsParameterString;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;

public class Init {
    public static void initDicts() {
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class + "1", DCurrencyTypes::getType);
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class, (name) -> {
            try {
                return DCurrencyTypes.valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        });

        IsCommandString.getSingleton().addVariant(DLineCommands.class, DLineCommands::getType);
        IsParameterString.getSingleton().addVariant(DLineCommands.class, DLineParameters::getType);
    }
}
