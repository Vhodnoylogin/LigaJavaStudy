package ru.liga.karmatskiyrg.controller.initialize;

import ru.liga.karmatskiyrg.controller.observers.dicts.IsCommandString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsParameterString;
import ru.liga.karmatskiyrg.model.dicts.commands.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.dates.DDateTypes;

public class Init {
    public static void initDictionaries() {
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class + "1", DCurrencyTypes::getType);
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class, name -> {
            try {
                return DCurrencyTypes.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        });

        IsCommandString.getSingleton().addVariant(DLineCommands.class, DLineCommands::getType);
        IsParameterString.getSingleton().addVariant(DLineCommands.class, DDateTypes::getType);
    }
}
