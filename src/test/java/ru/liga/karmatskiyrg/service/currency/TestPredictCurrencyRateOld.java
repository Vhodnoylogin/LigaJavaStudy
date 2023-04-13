package ru.liga.karmatskiyrg.service.currency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.distributed.app.client.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DatePeriod;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestPredictCurrencyRateOld {
    protected static PredictCurrencyRate predict;

    @BeforeAll
    public static void init() {
        var repo = new CurrencyRepoRAM();
        repo.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.getCsvFile()));
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


    @Test
    public void testPredictPeriod() {
        var period = DatePeriod.of(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(100)
        );

        var res = predict.predictToDate(
                DCurrencyTypes.TRY,
                period
        );

        log.info("Resulting list = {}", res);
        assertThat(res)
                .isNotNull()
                .hasSizeGreaterThan(0);
    }
}
