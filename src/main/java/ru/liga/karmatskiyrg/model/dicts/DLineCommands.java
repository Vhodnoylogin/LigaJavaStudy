package ru.liga.karmatskiyrg.model.dicts;

import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineCommand;

import java.util.HashMap;
import java.util.Map;

public enum DLineCommands implements DLineCommand {
    RATE("rate"),
    EXIT("exit");
    private final static Map<String, DLineCommand> lib = new HashMap<>() {{
        put(RATE.name, RATE);
        put(EXIT.name, EXIT);
    }};
    private final String name;

    DLineCommands(String s) {
        this.name = s;
    }

    public static DLineCommand getType(String name) {
        return lib.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public String getLongName() {
        return this.name;
    }
}
