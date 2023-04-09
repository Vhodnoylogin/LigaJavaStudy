package ru.liga.karmatskiyrg.distributed.app.client.service.currency;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.distributed.app.client.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.utils.csv.ReadCSVFile;

import java.io.InputStream;
import java.util.List;

@Slf4j
public final class CsvToCurrency {
    public static List<CurrencyRate> getCurrencyRate(InputStream in) {
        return ReadCSVFile.csvToModel(in, CurrencyRate.class);
    }
}
