package ru.liga.karmatskiyrg.utils.parser;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.utils.parse.ParserCommandLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TestParser {
    @Test
    public void testMatch() {
        String input = "-command1 value1 -command2 \"value 2\"";
        Pattern pattern = Pattern.compile("-?[a-zA-Z]+(?:\\s+(\"[^\"]+\"|[^\\s]+))?");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            var res = matcher.group();
            log.info("{}", res);
        }
    }

    @Test
    public void testMatch2() {
        String input = "-key1 val1 -key2 val2 -key3 val3";
        Pattern pattern = Pattern.compile("-(\\w+)\\s+(\\S+)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            log.info("key = {}, value = {}", key, value);
        }
    }

    @Test
    public void testTokenParser() {
        String input = "QWE bgf -command1 value1 n     -command2 value 2";
//        String input = "-key1 val1 -key2 val2";
        var res = ParserCommandLine.TOKEN_PARSER.parseCommand(input);

        log.info("{}", res);
    }

    @Test
    public void testTokenParser1() {
        String input = "QWE -command1 value1 n     -command2 value 2";
        var res = ParserCommandLine.TOKEN_PARSER.parseCommand(input);

        log.info("{}", res);
//        assertThat(res)
//                .isEqualTo(List.of(Token.));
    }

    @Test
    public void testTokenParserIrr() {
        String input = "-key1 val1 -key2 val2";
        var res = ParserCommandLine.TOKEN_PARSER.parseCommand(input);

        log.info("{}", res);
//        assertThat(res)
//                .isEqualTo(List.of());
    }
}
