package ru.liga.karmatskiyrg.telegram.controllers.lowlevel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.service.lowlevel.algorithm.AlgorithmController;
import ru.liga.karmatskiyrg.service.lowlevel.currency.CurrencyController;
import ru.liga.karmatskiyrg.service.lowlevel.period.fabric.DatePeriodFabric;
import ru.liga.karmatskiyrg.service.parsers.level2.RateParser;
import ru.liga.karmatskiyrg.utils.dates.DateInterval;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestLowLevelControllers {
    public static Map<DArgumentType, String> tokens;

    @BeforeAll
    public static void init() {
        var commandText = "-cur eur -period week -alg old";
        tokens = RateParser.RATE_PARSER.getTokenFromCommandString(commandText).getLeft();
    }

    @Test
    public void testCurrencyController() {
        var curType = new CurrencyController().get(tokens);
        log.info("{}", curType);
        assertThat(curType)
                .isEqualTo(DCurrencyTypes.EUR);
    }

    @Test
    public void testDatePeriodController() {
        var datePeriod = DatePeriodFabric.getDatePeriod(tokens).get(tokens);

        var period = DateInterval.of(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7)
        );

        assertThat(datePeriod)
                .isEqualTo(period);

        var alg = new AlgorithmController().get(tokens);
    }

    @Test
    public void testAlgorithmController() {
        var alg = new AlgorithmController().get(tokens);
        assertThat(alg.getClass())
                .isEqualTo(PredictCurrencyRateOld.class);
    }
}
