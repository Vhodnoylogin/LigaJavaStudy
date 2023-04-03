package ru.liga.karmatskiyrg.model.dicts.dates;

import lombok.NonNull;
import ru.liga.karmatskiyrg.model.dicts.dates.interfaces.DDateType;

public enum DDateAnotherTypes implements DDateType {
    MTH("month");
    private final String paramName;

    DDateAnotherTypes(@NonNull String s) {
        this.paramName = s.toLowerCase();
    }

    public static DDateType getType(@NonNull String name) {
        for (var type : DDateAnotherTypes.values()) {
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
