package ru.liga.karmatskiyrg.model.dicts.currencies;

import lombok.NonNull;
import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;

public enum DCurrencyAnotherTypes implements DCurrencyType {
    BGN("Болгарский лев"),
    AMD("Армянский драм");
    private final String longName;
    private final String exactlyName;

    DCurrencyAnotherTypes(@NonNull String s) {
        this.longName = s.toLowerCase();
        this.exactlyName = this.name().toLowerCase();
    }

    public static DCurrencyType getType(@NonNull String name) {
        for (var type : DCurrencyAnotherTypes.values()) {
            if (type.getLongName().equals(name.toLowerCase()))
                return type;
        }
        return null;
    }

    public static DCurrencyType getExactlyNameType(@NonNull String name) {
        for (var type : DCurrencyAnotherTypes.values()) {
            if (type.getShortName().equals(name.toLowerCase()))
                return type;
        }
        return null;
    }

    @Override
    public String getLongName() {
        return this.longName;
    }


    @Override
    public String getShortName() {
        return this.exactlyName;
    }
}
