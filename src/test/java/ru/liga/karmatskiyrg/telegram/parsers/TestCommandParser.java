package ru.liga.karmatskiyrg.telegram.parsers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.commands.DCommands;
import ru.liga.karmatskiyrg.distributed.app.client.service.parsers.CommandParser;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestCommandParser {
    @Test
    public void testCommand() {
        var commandText = "rate -cur eur -period week -alg old";

        var token = CommandParser.COMMAND_PARSER.getTokenFromCommandString(commandText);
        log.info("{}", token);

        var check = Pair.of(DCommands.RATE, "-cur eur -period week -alg old");
        assertThat(token)
                .isEqualTo(check);

    }
}
