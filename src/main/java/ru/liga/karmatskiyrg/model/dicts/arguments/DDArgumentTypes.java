package ru.liga.karmatskiyrg.model.dicts.arguments;

import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.model.dicts.dates.DDateAnotherTypes;
import ru.liga.karmatskiyrg.model.dicts.dates.interfaces.DDateType;

public enum DDArgumentTypes implements DArgumentType {
    PERIOD("period"),
    DATE("date"),
    ALG("alg"),
    OUT("output");
    private final String longName;

    DDArgumentTypes(String s) {
        this.longName = s.toLowerCase();
    }

    public static DDateType getType(String name) {
        for (DDateAnotherTypes type : DDateAnotherTypes.values()) {
            if (type.getLongName().equals(name.toLowerCase()))
                return type;
        }
        return null;
    }

    @Override
    public String getLongName() {
        return this.longName;
    }
}
