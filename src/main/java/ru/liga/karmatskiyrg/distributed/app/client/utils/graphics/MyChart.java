package ru.liga.karmatskiyrg.distributed.app.client.utils.graphics;

import lombok.Setter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

@Setter
public abstract class MyChart<T> {
    protected String title;
    protected String axisXName;
    protected String axisYName;

    protected XYDataset createDataset(List<T> sourceData) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] data = {{1, 2, 3, 4, 5}, {2, 4, 6, 8, 10}};
        dataset.addSeries("Series 1", data);
        return dataset;
    }

    public JFreeChart createChart(List<T> sourceData) {
        var dataset = createDataset(sourceData);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(this.title, this.axisXName, this.axisYName, dataset);

        XYPlot plot = chart.getXYPlot();
        ((DateAxis) plot.getDomainAxis()).setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd")); // Устанавливаем формат даты
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesPaint(0, Color.BLUE);
//        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
//        renderer.setSeriesShape(0, new Ellipse2D.Double(-2, -2, 4, 4));
//        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);
        chart.getLegend().setVisible(true);

        return chart;
    }
}
