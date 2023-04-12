package ru.liga.karmatskiyrg.telegram.parsers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.CommandWithArgsParser;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.liga.karmatskiyrg.distributed.app.lib.parsers.CommandWithArgsParser.SUPER_COMMAND;

@Slf4j
public class TestCommandParser {
    @Test
    public void testCommand() {
        var commandText = "rate --cur eur --period week --alg old";

        var token = CommandWithArgsParser.COMMAND_WITH_ARGS_PARSER.parseCommand(commandText);
        log.info("{}", token);

        var map = new HashMap<String, String>() {{
            put(SUPER_COMMAND, "rate");
            put("cur", "eur");
            put("period", "week");
            put("alg", "old");
        }};
        assertThat(token)
                .isEqualTo(map);

    }
}
