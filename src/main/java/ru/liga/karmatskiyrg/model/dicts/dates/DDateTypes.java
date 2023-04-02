package ru.liga.karmatskiyrg.model.dicts.dates;

import ru.liga.karmatskiyrg.model.dicts.dates.interfaces.DDateType;

import java.util.HashMap;
import java.util.Map;

public enum DDateTypes implements DDateType {
    TMR("tomorrow"),
    WEK("week");
    private final static Map<String, DDateType> lib = new HashMap<>() {{
        put(TMR.paramName, TMR);
        put(WEK.paramName, WEK);
    }};
    private final String paramName;

    DDateTypes(String s) {
        this.paramName = s.toLowerCase();
    }

    public static DDateType getType(String name) {
        return lib.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public String getLongName() {
        return this.paramName;
    }
}
