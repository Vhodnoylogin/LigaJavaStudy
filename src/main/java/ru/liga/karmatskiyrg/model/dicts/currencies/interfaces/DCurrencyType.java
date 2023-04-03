package ru.liga.karmatskiyrg.model.dicts.currencies.interfaces;

import ru.liga.karmatskiyrg.model.dicts.interfaces.ISuperDict;

@FunctionalInterface
public interface DCurrencyType extends ISuperDict {
    default String getShortName() {
        return "";
    }

}
