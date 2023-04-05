package ru.liga.karmatskiyrg.utils.graphics;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RateChart extends MyChart<CurrencyRate> {
    {
        this.title = "Деньги от времени";
        this.axisXName = "Даты";
        this.axisYName = "Деньги";
    }

    @Override
    protected XYDataset createDataset(List<CurrencyRate> sourceData) {
//        return super.createDataset(sourceData);
        Function<CurrencyRate, Day> makeDay = x -> new Day(
                x.getDate().getDayOfMonth(),
                x.getDate().getMonthValue(),
                x.getDate().getYear()
        );

        Map<String, TimeSeries> curMap = new HashMap<>();
        sourceData.stream()
                .peek(x -> curMap.put(
                        x.getName(),
                        new TimeSeries(x.getName()))
                )
                .forEach(
                        x -> curMap.get(x.getName()).addOrUpdate(
                                makeDay.apply(x),
                                x.getRate() * x.getNominal()
                        )
                );

        var dataset = new TimeSeriesCollection();
        curMap.forEach((x, y) -> dataset.addSeries(y));
        return dataset;
    }
}
