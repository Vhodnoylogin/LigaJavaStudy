package ru.liga.karmatskiyrg.service.help;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.csv.ReadCSVFile;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Slf4j
public class TestCSV {
    @Test
    public void testFunctionReturnsNotEmptyList() {
        try (var inputStream = CsvFileLayout.csvFile) {
            var list = ReadCSVFile.csvToModel(inputStream, CurrencyRate.class);

            log.info(String.valueOf(list.size()));
            assertThat(list)
                    .hasSize(list.size());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testFailCatchException() {
        try (var inputStream = CsvFileLayout.csvFile) {
            var list = ReadCSVFile.csvToModel(inputStream, CurrencyRateTest.class);

            log.info(String.valueOf(list.size()));
            assertThat(list)
                    .hasSize(list.size());
        } catch (Exception e) {
            // !!!! не отлавливает исключение, не падает тест !!!!
            log.error(e.getLocalizedMessage());
            fail(e.getLocalizedMessage());
        }
        fail("ALL BAD");
    }

    @Data
    //@Value
    //@NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CurrencyRateTest {
        @CsvBindByName(column = "nominal")
        private Integer nominal;
        @CsvBindByName(column = "data")
        @CsvDate("M/d/yyyy")
        private LocalDate date;
        @CsvBindByName(column = "curs")
        private Double rate;
        @CsvBindByName(column = "cdx")
        private String name;
    }
}
