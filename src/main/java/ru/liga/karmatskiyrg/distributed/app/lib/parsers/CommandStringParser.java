package ru.liga.karmatskiyrg.distributed.app.lib.parsers;

import ru.liga.karmatskiyrg.distributed.app.lib.parsers.exceptions.CantParseString;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.interfaces.CommandParser;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandStringParser implements CommandParser {
//    public static final CommandParser SIMPLE_COMMAND_PARSER = new CommandStringParser();

    private static final Pattern pattern = Pattern.compile("\\b[^-\\s]\\S*\\b");

    @Override
    public Map<String, String> parseCommand(String commandLine) {
        var matcher = pattern.matcher(commandLine);
        if (!matcher.find()) {
            throw new CantParseString(commandLine);
        }

        var command = matcher.group();
        var argsString = commandLine.substring(matcher.end()).trim();

        return Collections.singletonMap(command, argsString);
    }
}
