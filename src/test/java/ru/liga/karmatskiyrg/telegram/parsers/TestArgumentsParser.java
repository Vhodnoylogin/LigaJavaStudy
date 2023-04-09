package ru.liga.karmatskiyrg.telegram.parsers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.ArgumentsParser;

import java.util.HashMap;

@Slf4j
public class TestArgumentsParser {
    @Test
    public void testCommand() {
        var commandText = "-cur eur -period week -alg old";

        var tokens = ArgumentsParser.RATE_PARSER.getTokenFromCommandString(commandText);
        log.info("{}", tokens);

        var map = new HashMap<>() {{
            put(DArgumentTypes.ALG, "old");
            put(DArgumentTypes.PERIOD, "week");
            put(DArgumentTypes.CUR, "eur");
        }};
        var check = Pair.of(map, "");
        assertThat(tokens)
                .isEqualTo(check);
    }

}
