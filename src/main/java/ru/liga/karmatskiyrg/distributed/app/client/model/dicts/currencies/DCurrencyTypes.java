package ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies;

import lombok.NonNull;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces.DCurrencyType;

public enum DCurrencyTypes implements DCurrencyType {
    EUR("Евро"),
    USD("Доллар США"),
    TRY("Турецкая лира");
    private final String longName;
    private final String shortName;

    DCurrencyTypes(@NonNull String s) {
        this.longName = s.toLowerCase();
        this.shortName = this.name().toLowerCase();
    }


    @Override
    public String getLongName() {
        return this.longName;
    }

    public static DCurrencyType getType(@NonNull String name) {
        for (var type : DCurrencyTypes.values()) {
            if (type.getLongName().equals(name.toLowerCase()))
                return type;
        }
        return null;
    }

    public static DCurrencyType getShortNameType(@NonNull String name) {
        for (var type : DCurrencyTypes.values()) {
            if (type.getShortName().equals(name.toLowerCase()))
                return type;
        }
        return null;
    }

    @Override
    public String getShortName() {
        return this.shortName;
    }
}
