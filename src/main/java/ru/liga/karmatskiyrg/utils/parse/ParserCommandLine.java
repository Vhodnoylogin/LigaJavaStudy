package ru.liga.karmatskiyrg.utils.parse;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.utils.parse.interfaces.CommandParser;
import ru.liga.karmatskiyrg.utils.parse.tokens.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public class ParserCommandLine implements CommandParser<Token> {
    public static final ParserCommandLine TOKEN_PARSER = new ParserCommandLine();

    private static final Pattern pattern1 = Pattern.compile("\\b[^-\\s]\\S*\\b");
    //    private static final Pattern pattern2 = Pattern.compile("-\\w+\\s+\\S+"); // не понимаю почему, но щаблон работает
    private static final Pattern pattern2 = Pattern.compile("-(\\w+)\\s(\\S+)"); // не понимаю почему, но щаблон работает

    @Override
    public List<Token> parseCommand(String commandLine) {
        var listOfTokens = new ArrayList<Token>();

        var matcher = pattern1.matcher(commandLine);
        if (!matcher.find()) return List.of();
        var firstWord = matcher.group();
        listOfTokens.add(
                Token.of(firstWord, firstWord)
        );

        commandLine = commandLine.substring(matcher.end()).trim();
        matcher = pattern2.matcher(commandLine);
        while (matcher.find()) {
            var key = matcher.group(1);
            var val = matcher.group(2);
            listOfTokens.add(
                    Token.of(key, val)
            );
        }

        return Collections.unmodifiableList(listOfTokens);
    }
}
