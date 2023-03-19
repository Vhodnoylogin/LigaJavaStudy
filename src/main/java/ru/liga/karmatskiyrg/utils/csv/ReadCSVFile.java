package ru.liga.karmatskiyrg.utils.csv;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class ReadCSVFile {
    protected static final char SEPARATOR = ';';

    public static <T> List<T> csvToModel(InputStream in, Function<String[], T> map) {
        try (var r = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            return new CSVReaderBuilder(r)
                    .withSkipLines(1)
                    .withCSVParser(new CSVParserBuilder().withSeparator(SEPARATOR).build())
                    .build()
                    .readAll().stream()
                    .peek(x -> log.debug("Print raw input", x))
                    .map(map)
                    .peek(x -> log.debug("Print converted input", x))
                    .toList();
        } catch (IOException | CsvException e) {
            log.error("Error on parsing file", e);
            return Collections.emptyList();
        }
    }

    public static <T> List<T> csvToModel(InputStream in, Class<T> tClass) {
        try (var r = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            return new CsvToBeanBuilder<T>(r)
                    .withSeparator(SEPARATOR)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(tClass)
                    .build()
                    .parse();
        } catch (IOException e) {
            log.error("Error on parsing file", e);
            return Collections.emptyList();
        }
    }
}
