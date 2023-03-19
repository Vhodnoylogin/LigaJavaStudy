package ru.liga.karmatskiyrg.service.currency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.test.SuperTest;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;

import java.time.LocalDate;

@Slf4j
public class TestPredictCurrencyRate extends SuperTest {


    protected static PredictCurrencyRate predict;

    @BeforeAll
    public static void init() {
        var repo = new CurrencyRepoRAM();
        repo.save(new CsvToCurrency().getCurrencyRate(CsvFileLayout.csvFile));
        predict = new PredictCurrencyRate(repo);
    }

    @Test
    public void test1() {
        var res = predict.predictToDate(DCurrencyTypes.EUR, LocalDate.now().plusDays(2));
        log.info("res", res);
    }

    @Test
    public void test2() {
        var res = predict.predictToDate(DCurrencyTypes.TRY, LocalDate.now().plusDays(-1));
        log.info("res", res);
    }

    @Test
    public void test3() {
        DCurrencyType qql = () -> "null";

        var res = predict.predictToDate(qql, LocalDate.now());
        log.info("res", res);
    }
}
