package ru.liga.karmatskiyrg.distributed.app.lib.parsers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.exceptions.CantParseString;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.interfaces.CommandParser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
public class CommandWithArgsParser implements CommandParser {
    public static final CommandParser COMMAND_WITH_ARGS_PARSER = new CommandWithArgsParser();
    public static final String SUPER_COMMAND = "SUPER_COMMAND";

    private static final Pattern pattern1 = Pattern.compile("\\b[^-\\s]\\S*\\b");
    private static final Pattern pattern2 = Pattern.compile("--(\\w+)\\s(\\S+)"); // не понимаю почему, но шаблон работает

    @Override
    public Map<String, String> parseCommand(String commandLine) {
        var matcher1 = pattern1.matcher(commandLine);
        if (!matcher1.find()) {
            throw new CantParseString(commandLine);
        }

        var command = matcher1.group();
        var argsString = commandLine.substring(matcher1.end()).trim();
        var matcher2 = pattern2.matcher(argsString);

        var res = new HashMap<String, String>();
        res.put(SUPER_COMMAND, command);

        Stream.generate(() -> 1)
                .takeWhile(n -> matcher2.find())
                .map(n -> Pair.of(
                        matcher2.group(1),
                        matcher2.group(2)
                ))
                .filter(p -> p.getLeft() != null)
                .filter(p -> p.getRight() != null)
                .forEach(x -> res.put(
                        x.getLeft(),
                        x.getRight()
                ));

        return res;
    }
}
