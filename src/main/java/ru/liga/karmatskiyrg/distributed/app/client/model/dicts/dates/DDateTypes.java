package ru.liga.karmatskiyrg.distributed.app.client.model.dicts.dates;

import lombok.NonNull;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.dates.interfaces.DDateType;

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

    DDateTypes(@NonNull String s) {
        this.paramName = s.toLowerCase();
    }

    public static DDateType getType(@NonNull String name) {
        return lib.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public String getLongName() {
        return this.paramName;
    }
}
