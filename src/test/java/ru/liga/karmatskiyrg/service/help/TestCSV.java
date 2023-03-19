package ru.liga.karmatskiyrg.service.help;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.csv.ReadCSVFile;

import java.io.IOException;

@Slf4j
public class TestCSV {

    @Test
    public void test1() {
        try (var inputStream = CsvFileLayout.csvFile) {
            var list = ReadCSVFile.csvToModel(inputStream, CurrencyRate.class);

            log.info("Result length", list.size());
        } catch (IOException e) {
            log.error("Error on", e);
        }
    }
}
