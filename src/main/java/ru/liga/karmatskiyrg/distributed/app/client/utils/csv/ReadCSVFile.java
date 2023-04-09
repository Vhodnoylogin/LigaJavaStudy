package ru.liga.karmatskiyrg.distributed.app.client.utils.csv;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.distributed.app.client.utils.csv.exceptions.CantConvertCSV;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class ReadCSVFile {
    protected static final char SEPARATOR = ';';

    public static <T> List<T> csvToModel(InputStream in, Function<String[], T> map) {
        try (var reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            return new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(new CSVParserBuilder().withSeparator(SEPARATOR).build())
                    .build()
                    .readAll().stream()
//                    .peek(x -> log.debug("Print raw input = {}", (Object[]) x))
                    .map(map)
//                    .peek(x -> log.debug("Print converted input =  {}", x))
                    .toList();
        } catch (IOException | CsvException e) {
            log.error("Error on converting CSV", e);
//            return Collections.emptyList();
            throw new CantConvertCSV(e);
        }
    }

    public static <T> List<T> csvToModel(InputStream in, Class<T> tClass) {
        try (var reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            return new CsvToBeanBuilder<T>(reader)
                    .withSeparator(SEPARATOR)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(tClass)
                    .build()
                    .parse();
        } catch (Exception e) {
            log.error("Error on converting CSV", e);
//            return Collections.emptyList();
            throw new CantConvertCSV(e);
        }
    }
}
