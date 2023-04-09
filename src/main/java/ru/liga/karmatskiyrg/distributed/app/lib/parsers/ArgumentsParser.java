package ru.liga.karmatskiyrg.distributed.app.lib.parsers;

import org.apache.commons.lang3.tuple.Pair;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.interfaces.CommandParser;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArgumentsParser implements CommandParser {
//    public static final CommandParser ARGUMENTS_PARSER = new ArgumentsParser();


    //    private static final Pattern pattern = Pattern.compile("-(\\w+)\\s(\\S+)"); // не понимаю почему, но шаблон работает
    private static final Pattern pattern = Pattern.compile("--(\\w+)\\s(\\S+)"); // не понимаю почему, но шаблон работает

    @Override
    public Map<String, String> parseCommand(String commandLine) {
        var matcher = pattern.matcher(commandLine);

        return Stream.generate(() -> 1)
                .takeWhile(n -> matcher.find())
                .map(n -> Pair.of(
                        matcher.group(1),
                        matcher.group(2)
                ))
//                .map(p -> Pair.of(
//                        DArgumentTypes.getType(p.getLeft()),
//                        p.getRight()
//                ))
                .filter(p -> p.getLeft() != null)
                .filter(p -> p.getRight() != null)
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
    }
}
