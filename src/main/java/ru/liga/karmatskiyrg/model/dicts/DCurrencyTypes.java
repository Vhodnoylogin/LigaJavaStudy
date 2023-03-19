package ru.liga.karmatskiyrg.model.dicts;

import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;

import java.util.HashMap;
import java.util.Map;

public enum DCurrencyTypes implements DCurrencyType {
    EUR("Евро"),
    USD("Доллар США"),
    TRY("Турецкая лира");
    private final static Map<String, DCurrencyType> lib = new HashMap<>() {{
        put(EUR.name, EUR);
        put(USD.name, USD);
        put(TRY.name, TRY);
    }};
    private final String name;

    DCurrencyTypes(String s) {
        this.name = s;
    }

    public static DCurrencyType getType(String name) {
        return lib.getOrDefault(name, null);
    }

    @Override
    public String getLongName() {
        return this.name;
    }


}
