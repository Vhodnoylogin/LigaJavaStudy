package ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.interfaces.ISuperDict;

@FunctionalInterface
public interface DCurrencyType extends ISuperDict {
    default String getShortName() {
        return "";
    }

}
