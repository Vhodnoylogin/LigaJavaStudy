package ru.liga.karmatskiyrg.service.currency;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.ReadCSVFile;

import java.io.InputStream;
import java.util.List;

@Slf4j
public final class CsvToCurrency {
    public static List<CurrencyRate> getCurrencyRate(InputStream in) {
        return ReadCSVFile.csvToModel(in, CurrencyRate.class);
    }
}
