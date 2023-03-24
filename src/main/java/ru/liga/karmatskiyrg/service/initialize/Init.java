package ru.liga.karmatskiyrg.service.initialize;

import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;

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
    }
}
