package ru.liga.karmatskiyrg.utils.graphics;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;

import java.util.List;

public class RateChart extends MyChart<CurrencyRate> {
    {
        this.axisXName = "Даты";
        this.axisYName = "Деньги";
    }

    @Override
    protected XYDataset createDataset(List<CurrencyRate> sourceData) {
//        return super.createDataset(sourceData);

        var priceSeries = new TimeSeries("Price");
        sourceData
                .forEach(x ->
                        priceSeries.addOrUpdate(
                                new Day(
                                        x.getDate().getDayOfMonth(),
                                        x.getDate().getMonthValue(),
                                        x.getDate().getYear()
                                ),
                                x.getRate()
                        )
                );

        var dataset = new TimeSeriesCollection();
        dataset.addSeries(priceSeries);
        return dataset;
    }
}
