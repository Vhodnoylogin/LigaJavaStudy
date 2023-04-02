package ru.liga.karmatskiyrg.model.dicts.dates;

import ru.liga.karmatskiyrg.model.dicts.dates.interfaces.DDateType;

public enum DDateAnotherTypes implements DDateType {
    MTH("month");
    private final String paramName;

    DDateAnotherTypes(String s) {
        this.paramName = s.toLowerCase();
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
        return this.paramName;
    }
}
