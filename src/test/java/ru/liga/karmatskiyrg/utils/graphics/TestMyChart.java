package ru.liga.karmatskiyrg.utils.graphics;


import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.distributed.app.client.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DatePeriod;
import ru.liga.karmatskiyrg.distributed.app.client.utils.graphics.RateChart;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class TestMyChart {
    protected static PredictCurrencyRate predict;

    @BeforeAll
    public static void init() {
        var repo = new CurrencyRepoRAM();
        repo.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.getCsvFile()));
        predict = new PredictCurrencyRateOld(repo);
    }

    @Test
    public void testMyChart() {
        var period = DatePeriod.of(
                LocalDate.now().plusDays(1),
//                LocalDate.now().plusDays(100)
                LocalDate.now().plusDays(30)
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
            ChartUtils.saveChartAsPNG(output, res, 360, 360);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
