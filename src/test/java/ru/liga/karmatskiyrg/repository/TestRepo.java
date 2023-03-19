package ru.liga.karmatskiyrg.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;
import ru.liga.karmatskiyrg.test.SuperTest;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.csv.ReadCSVFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
public class TestRepo extends SuperTest {
    protected static List<CurrencyRate> list;


    @BeforeAll
    static void init() throws IOException {
        try (var inputStream = CsvFileLayout.csvFile) {
            list = ReadCSVFile.csvToModel(inputStream, CurrencyRate.class);
        }
    }

    @Test
    void testListToDBEqualListFromDB() {
        var repo = new CurrencyRepoRAM();

        repo.save(list);
        var res = repo.getAll();

        assertEquals(list.size(), res.size());
        assertEquals(new HashSet<>(list), new HashSet<>(res));
    }

    @Test
    void testnputToDBWorkWell() {
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

        log.info("All pass good", res);
    }

    @Test
    public void test3() {
        InputStream testFile = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                1;12/25/2007;24.7196;Хрень
                """).getBytes());

        var repo = new CurrencyRepoRAM();

        repo.save(ReadCSVFile.csvToModel(testFile, CurrencyRate.class));
        var res = repo.getAll();

        log.info("All pass good", res);
        assertEquals(new LinkedList<>(), res);
    }

    @Test
    public void test4() {
        InputStream testFile = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                1;12/25/2007;24.7196;Хрень
                1;12/25/2007;24.7196;Доллар США
                """).getBytes());

        var repo = new CurrencyRepoRAM();

        repo.save(ReadCSVFile.csvToModel(testFile, CurrencyRate.class));
        var res = repo.getSlice(DCurrencyTypes.EUR);

        log.info("All pass good", res);
        assertEquals(new LinkedList<>(), res);
    }

    @Test
    public void test5() {
        InputStream testFile = new ByteArrayInputStream(("""
                nominal;data;curs;cdx
                1;12/25/2007;24.7196;Хрень
                1;12/25/2007;24.7196;Доллар США
                """).getBytes());

        var repo = new CurrencyRepoRAM();

        repo.save(ReadCSVFile.csvToModel(testFile, CurrencyRate.class));
        var res = repo.getSlice(DCurrencyTypes.USD);

        log.info("All pass good", res);
        assertEquals(repo.getAll(), res);
    }
}
