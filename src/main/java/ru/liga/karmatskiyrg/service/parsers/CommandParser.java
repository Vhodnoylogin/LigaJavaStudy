package ru.liga.karmatskiyrg.service.parsers;

import org.apache.commons.lang3.tuple.Pair;
import ru.liga.karmatskiyrg.utils.parse.exceptions.CantParseString;
import ru.liga.karmatskiyrg.utils.parse.interfaces.CommandStringParser;

import java.util.regex.Pattern;

public class CommandParser implements CommandStringParser<String> {
    public static final CommandParser COMMAND_PARSER = new CommandParser();
    private static final Pattern pattern = Pattern.compile("\\b[^-\\s]\\S*\\b");

    @Override
    public Pair<String, String> getTokenFromCommandString(String commandLine) {
        var matcher = pattern.matcher(commandLine);
        if (!matcher.find()) throw new CantParseString(commandLine);

        var firstWord = matcher.group();
        return Pair.of(
                firstWord,
                commandLine.substring(matcher.end()).trim()
        );
    }
}
