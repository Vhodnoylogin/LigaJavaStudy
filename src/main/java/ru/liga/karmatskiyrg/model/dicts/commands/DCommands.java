package ru.liga.karmatskiyrg.model.dicts.commands;

import lombok.NonNull;
import ru.liga.karmatskiyrg.model.dicts.commands.interfaces.DCommand;

import java.util.HashMap;
import java.util.Map;

public enum DCommands implements DCommand {
    RATE("rate"),
    EXIT("exit");
    private final static Map<String, DCommand> lib = new HashMap<>() {{
        put(RATE.commandName, RATE);
        put(EXIT.commandName, EXIT);
    }};
    private final String commandName;

    DCommands(@NonNull String s) {
        this.commandName = s.toLowerCase();
    }

    public static DCommand getType(@NonNull String name) {
        return lib.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public String getLongName() {
        return this.commandName;
    }
}
