package ru.liga.karmatskiyrg.distributed.app.client.utils.graphics;

import lombok.extern.slf4j.Slf4j;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import ru.liga.karmatskiyrg.distributed.app.client.model.currency.CurrencyRate;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class RateChart extends MyChart<CurrencyRate> {
    {
        this.title = "Деньги от времени";
        this.axisXName = "Даты";
        this.axisYName = "Деньги";
    }

    @Override
    protected XYDataset createDataset(List<CurrencyRate> sourceData) {
//        return super.createDataset(sourceData);
//        Function<CurrencyRate, Day> makeDay = x -> new Day(
//                x.getDate().getDayOfMonth(),
//                x.getDate().getMonthValue(),
//                x.getDate().getYear()
//        );
        Function<CurrencyRate, Day> makeDay = x -> new Day(
                Date.from(x.getDate()
                        .atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                )
        );

        Map<String, TimeSeries> curMap = new HashMap<>();
//        Map<String, XYSeries> curMap = new HashMap<>();
        for (CurrencyRate rate : sourceData) {
            var series = curMap.get(rate.getName());
            if (series == null) {
                series = new TimeSeries(rate.getName());
//                series = new XYSeries(rate.getName());
                curMap.put(rate.getName(), series);
            }
//            series.add(rate.getDate().toEpochDay(), rate.getRate());
            series.add(
                    makeDay.apply(rate),
                    rate.getRate()
            );
        }
//        sourceData.stream()
//                .peek(x -> curMap.put(
//                        x.getName(),
////                        new TimeSeries(x.getName())
//                                new XYSeries(x.getName())
//                        )
//                )
//                .forEach(x -> curMap.get(x.getName())
//                        .add(
////                                makeDay.apply(x),
//                                x.getDate().toEpochDay(),
//                                x.getRate() * x.getNominal()
//                        )
//                );

//        curMap.values()
//                .stream()
//                .map(x -> x.)
//                .flatMap(x -> x.stream())
//                .forEach(x -> log.debug("{}", x));

        var dataset = new TimeSeriesCollection();
//        var dataset = new XYSeriesCollection();
        curMap.forEach((x, y) -> dataset.addSeries(y));
        return dataset;
    }
}
