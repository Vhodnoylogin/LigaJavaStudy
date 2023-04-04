package ru.liga.karmatskiyrg.service.currency.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.init.InitTest;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.csv.ReadCSVFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


@Slf4j
public class TestRepo extends InitTest {
    protected static List<CurrencyRate> list;

    @BeforeAll
    static void init() {
        try (var inputStream = CsvFileLayout.CSV_FILE) {
            list = CsvToCurrency.getCurrencyRate(inputStream);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            list = Collections.emptyList();
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    void testListToDBEqualListFromDB() {
        var repo = new CurrencyRepoRAM();
        repo.save(list);

        var res = repo.getAll();

        log.info("Size of list = {}", res.size());
        assertThat(res)
                .hasSize(list.size())
                .containsAll(list);

    }

    @Test
    void testInputToDBWorkWell() {
        InputStream testFile1 = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                10;3/16/2023;39.9324;Турецкая лира
                1;12/26/2007;24.7196;Доллар США
                1;12/26/2007;24.7196;Хрень
                """).getBytes());
        InputStream testFile2 = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                10;3/15/2023;39.9324;Турецкая лира
                1;12/25/2007;24.7196;Доллар США
                1;12/25/2007;24.7196;Хрень
                """).getBytes());
        InputStream testFile3 = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                10;3/15/2023;39.9324;Турецкая лира
                1;12/25/2007;24.7196;Доллар США
                1;12/25/2007;24.7196;Хрень
                """).getBytes());
        InputStream testFile4 = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                1;12/25/2007;24.7196;Хрень
                """).getBytes());

        var repo = new CurrencyRepoRAM();
        repo.save(ReadCSVFile.csvToModel(testFile1, CurrencyRate.class));
        repo.save(ReadCSVFile.csvToModel(testFile2, CurrencyRate.class));
        repo.save(ReadCSVFile.csvToModel(testFile3, CurrencyRate.class));
        repo.save(ReadCSVFile.csvToModel(testFile4, CurrencyRate.class));

        var res = repo.getAll();

        log.info("Size of list = {}", res.size());
        assertThat(res)
                .hasSizeGreaterThan(5);
    }

    @Test
    public void testInputToDBRejectNonCurrencyType() {
        InputStream testFile = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                1;12/25/2007;24.7196;Хрень
                """).getBytes());

        var repo = new CurrencyRepoRAM();
        repo.save(ReadCSVFile.csvToModel(testFile, CurrencyRate.class));

        var res = repo.getAll();

        log.info("Size of list = {}", res.size());
        assertThat(res)
                .isEmpty();
    }

    @Test
    public void testTryToFindTypeNotInDB() {
        InputStream testFile = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                1;12/25/2007;24.7196;Хрень
                1;12/25/2007;24.7196;Доллар США
                """).getBytes());

        var repo = new CurrencyRepoRAM();
        repo.save(ReadCSVFile.csvToModel(testFile, CurrencyRate.class));

        var res = repo.getSlice(DCurrencyTypes.EUR);

        log.info("Size of list = {}", res.size());
        assertThat(res)
                .isEmpty();
    }

    @Test
    public void testGetSlice() {
        InputStream testFile = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                1;12/25/2007;24.7196;Хрень
                1;12/25/2007;24.7196;Доллар США
                """).getBytes());

        var repo = new CurrencyRepoRAM();
        repo.save(ReadCSVFile.csvToModel(testFile, CurrencyRate.class));

        var res = repo.getSlice(DCurrencyTypes.USD);

        log.info("Size of list = {}", res.size());
        assertThat(res)
                .hasSize(1);
    }
}
