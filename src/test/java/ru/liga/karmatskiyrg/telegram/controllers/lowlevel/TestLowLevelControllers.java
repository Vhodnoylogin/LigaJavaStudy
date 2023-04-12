package ru.liga.karmatskiyrg.telegram.controllers.lowlevel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.ArgumentsParser;

import java.util.Map;

@Slf4j
public class TestLowLevelControllers {
    public static Map<String, String> tokens;

    @BeforeAll
    public static void init() {
        var commandText = "-cur eur -period week -alg old";
        tokens = ArgumentsParser.ARGUMENTS_PARSER.parseCommand(commandText);
    }

//    @Test
//    public void testCurrencyController() {
//        var curType = new CurrencyController().get(tokens);
//        log.info("{}", curType);
//        assertThat(curType)
//                .isEqualTo(DCurrencyTypes.EUR);
//    }
//
//    @Test
//    public void testDatePeriodController() {
//        var datePeriod = DatePeriodFabric.getDatePeriod(tokens).get(tokens);
//
//        var period = DateInterval.of(
//                LocalDate.now().plusDays(1),
//                LocalDate.now().plusDays(7)
//        );
//
//        assertThat(datePeriod)
//                .isEqualTo(period);
//
//        var alg = new AlgorithmController().get(tokens);
//    }
//
//    @Test
//    public void testAlgorithmController() {
//        var alg = new AlgorithmController().get(tokens);
//        assertThat(alg.getClass())
//                .isEqualTo(PredictCurrencyRateOld.class);
//    }
}
