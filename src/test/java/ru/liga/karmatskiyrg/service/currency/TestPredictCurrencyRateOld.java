package ru.liga.karmatskiyrg.service.currency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.init.InitTest;
import ru.liga.karmatskiyrg.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestPredictCurrencyRateOld extends InitTest {
    protected static PredictCurrencyRate predict;

    @BeforeAll
    public static void init() {
        var repo = new CurrencyRepoRAM();
        repo.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.csvFile));
        predict = new PredictCurrencyRateOld(repo);
    }

    @Test
    public void testPredictEUR() {
        var res = predict.predictToDate(
                DCurrencyTypes.EUR,
                LocalDate.now().plusDays(2)
        );

        log.info("Resulting list = {}", res);
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

        log.info("Resulting list = {}", res);
        assertThat(res)
                .isNotNull()
                .hasSizeGreaterThan(0);
    }

    @Test
    public void testNonCurrencyType() {
        DCurrencyType type = () -> "null";

        var res = predict.predictToDate(
                type,
                LocalDate.now()
        );

        log.info("Resulting list = {}", res);
        assertThat(res)
                .isNotNull()
                .hasSize(0);
    }
}
