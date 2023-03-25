package ru.liga.karmatskiyrg.service.currency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.controller.initialize.Init;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.interfaces.CurrencyPredict;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@Slf4j
//@RunWith(PowerMockRunner.class)
public class TestPredictWithMock {
    protected static CurrencyPredict predict;

    @BeforeAll
    public static void init() {
        Init.initDictionaries();
//        var repo = new CurrencyRepoRAM();
        var repo = mock(CurrencyRepoRAM.class);
        repo.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.csvFile));
        predict = new PredictCurrencyRate(repo);
    }

    @Test
    public void testPredictEUR() {
        var res = predict.predictToDate(
                DCurrencyTypes.EUR,
                LocalDate.now().plusDays(2)
        );
        log.info(res.toString());
        assertThat(res)
                .isNotNull()
                .hasSizeGreaterThan(0);
    }

    @Test
    public void testPredictTRY() {
        var res = predict.predictToDate(
                DCurrencyTypes.TRY,
                LocalDate.now().plusDays(-1)
        );
        log.info(res.toString());
        assertThat(res)
                .isNotNull()
                .hasSizeGreaterThan(0);
    }

    @Test
    public void testNonCurrencyType() {
        var res = predict.predictToDate(
                () -> "null",
                LocalDate.now()
        );
        log.info(res.toString());
        assertThat(res)
                .isNotNull()
                .hasSize(0);
    }
}
