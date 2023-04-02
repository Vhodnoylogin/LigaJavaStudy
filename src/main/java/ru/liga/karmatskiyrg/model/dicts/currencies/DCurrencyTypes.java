package ru.liga.karmatskiyrg.model.dicts.currencies;

import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;

import java.util.HashMap;
import java.util.Map;

public enum DCurrencyTypes implements DCurrencyType {
    EUR("Евро"),
    USD("Доллар США"),
    TRY("Турецкая лира");
    private final static Map<String, DCurrencyType> lib = new HashMap<>() {{
        put(EUR.longName, EUR);
        put(USD.longName, USD);
        put(TRY.longName, TRY);
    }};
    private final String longName;

    DCurrencyTypes(String s) {
        this.longName = s.toLowerCase();
    }

    public static DCurrencyType getType(String name) {
        return lib.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public String getLongName() {
        return this.longName;
    }


}
