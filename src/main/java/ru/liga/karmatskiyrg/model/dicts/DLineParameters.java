package ru.liga.karmatskiyrg.model.dicts;

import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineParameter;

import java.util.HashMap;
import java.util.Map;

public enum DLineParameters implements DLineParameter {
    TMR("tomorrow"),
    WEK("week");
    private final static Map<String, DLineParameter> lib = new HashMap<>() {{
        put(TMR.name, TMR);
        put(WEK.name, WEK);
    }};
    private final String name;

    DLineParameters(String s) {
        this.name = s;
    }

    public static DLineParameter getType(String name) {
        return lib.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public String getLongName() {
        return this.name;
    }
}
