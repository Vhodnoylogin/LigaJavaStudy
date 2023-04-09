package ru.liga.karmatskiyrg.distributed.app.client.utils.csv;

import java.io.InputStream;

public class CsvFileLayout {
//    public static final InputStream CSV_FILE = CsvFileLayout.class.getResourceAsStream("/source/dollar_evro_lira.csv");
//    public static final InputStream CSV_FILE = CsvFileLayout.class.getResourceAsStream("/source/curRate.csv");

    public static InputStream getCsvFile() {
        return CsvFileLayout.class.getResourceAsStream("/source/dollar_evro_lira.csv");
    }
}
