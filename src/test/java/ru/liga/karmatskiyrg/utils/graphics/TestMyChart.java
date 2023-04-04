package ru.liga.karmatskiyrg.utils.graphics;


import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.init.InitTest;
import ru.liga.karmatskiyrg.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.dates.DateInterval;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class TestMyChart extends InitTest {
    protected static PredictCurrencyRate predict;

    @BeforeAll
    public static void init() {
        var repo = new CurrencyRepoRAM();
        repo.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.CSV_FILE));
        predict = new PredictCurrencyRateOld(repo);
    }

    @Test
    public void testMyChart() {
        var period = DateInterval.of(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(100)
        );

        var list = predict.predictToDate(
                DCurrencyTypes.TRY,
                period
        );
//        log.info("{}",list);

        RateChart example = new RateChart();
        var res = example.createChart(list);

        File output = new File("C:\\Users\\Radimir\\Downloads\\test.png");
        try {
            ChartUtils.saveChartAsPNG(output, res, 1000, 1000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
