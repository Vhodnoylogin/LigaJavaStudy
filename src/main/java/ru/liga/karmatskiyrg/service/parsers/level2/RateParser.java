package ru.liga.karmatskiyrg.service.parsers.level2;

import org.apache.commons.lang3.tuple.Pair;
import ru.liga.karmatskiyrg.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.utils.parse.interfaces.CommandStringParser;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RateParser implements CommandStringParser<Map<DArgumentType, String>> {
    public static final RateParser RATE_PARSER = new RateParser();
    private static final Pattern pattern = Pattern.compile("-(\\w+)\\s(\\S+)"); // не понимаю почему, но щаблон работает

    @Override
    public Pair<Map<DArgumentType, String>, String> getTokenFromCommandString(String commandLine) {
        var matcher = pattern.matcher(commandLine);

        var mapTokens = Stream.generate(() -> 1)
                .takeWhile(n -> matcher.find())
                .map(n -> Pair.of(
                        matcher.group(1),
                        matcher.group(2)
                ))
                .map(p -> Pair.of(
                        DArgumentTypes.getType(p.getLeft()),
                        p.getRight()
                ))
                .filter(p -> p.getLeft() != null)
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));


//        while (matcher.find()) {
//            var key = matcher.group(1);
//            var val = matcher.group(2);
//            listOfTokens.add(
//                    Token.of(key, val)
//            );
//        }
//
//        return Collections.unmodifiableList(listOfTokens);

        return Pair.of(mapTokens, "");
    }
}
