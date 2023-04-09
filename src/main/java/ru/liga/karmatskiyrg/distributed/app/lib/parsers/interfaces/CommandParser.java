package ru.liga.karmatskiyrg.distributed.app.lib.parsers.interfaces;

import java.util.Map;

public interface CommandParser {
    Map<String, String> parseCommand(String commandLine);
}
