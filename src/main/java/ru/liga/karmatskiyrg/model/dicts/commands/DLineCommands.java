package ru.liga.karmatskiyrg.model.dicts.commands;

import ru.liga.karmatskiyrg.model.dicts.commands.interfaces.DLineCommand;

import java.util.HashMap;
import java.util.Map;

public enum DLineCommands implements DLineCommand {
    RATE("rate"),
    EXIT("exit");
    private final static Map<String, DLineCommand> lib = new HashMap<>() {{
        put(RATE.commandName, RATE);
        put(EXIT.commandName, EXIT);
    }};
    private final String commandName;

    DLineCommands(String s) {
        this.commandName = s.toLowerCase();
    }

    public static DLineCommand getType(String name) {
        return lib.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public String getLongName() {
        return this.commandName;
    }
}
