package ru.liga.karmatskiyrg.telegram.parsers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.ArgumentsParser;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestArgumentsParser {
    @Test
    public void testCommand() {
        var commandText = "--cur eur --period week --alg old";

        var tokens = ArgumentsParser.ARGUMENTS_PARSER.parseCommand(commandText);
        log.info("{}", tokens);

        var map = new HashMap<>() {{
            put("alg", "old");
            put("period", "week");
            put("cur", "eur");
        }};
        assertThat(tokens)
                .isEqualTo(map);
    }

}
