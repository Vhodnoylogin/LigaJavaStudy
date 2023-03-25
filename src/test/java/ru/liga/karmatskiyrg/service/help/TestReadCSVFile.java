package ru.liga.karmatskiyrg.service.help;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.utils.csv.ReadCSVFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestReadCSVFile {
    public static final List<Model> standardModel = new ArrayList<>();
    public static InputStream in;

    @BeforeAll
    public static void initClass() {
        standardModel.add(Model.builder()
                .fieldInteger(1)
                .fieldDate(LocalDate.parse("2023-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build());
        standardModel.add(Model.builder()
                .fieldInteger(3)
                .fieldDate(LocalDate.parse("2023-01-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build());
    }

    @BeforeEach
    public void initTest() {
        in = new ByteArrayInputStream(("""
                field1;date
                1;2023-01-01
                3;2023-01-02
                 """).getBytes()
        );
    }

    @Test
    public void testCsvToModelWithClass() {
        var res = ReadCSVFile.csvToModel(in, Model.class);

        log.debug("Result list = {}", res);
        assertThat(res)
                .isNotEmpty()
                .hasSameElementsAs(standardModel);
    }

    @Test
    public void testCsvToModelWithFunction() {
        Function<String[], Model> func = x -> Model.builder()
                .fieldInteger(Integer.parseInt(x[0]))
                .fieldDate(LocalDate.parse(x[1], DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();

        var res = ReadCSVFile.csvToModel(in, func);

        log.debug("Result list = {}", res);
        assertThat(res)
                .hasSameElementsAs(standardModel);
    }

    @Test
    public void testInputIsOnlyHeaders() {
        in = new ByteArrayInputStream(("""
                field1;date
                 """).getBytes()
        );

        var res = ReadCSVFile.csvToModel(in, Model.class);

        log.debug("Result list = {}", res);
        assertThat(res)
                .hasSize(0);
    }

    @Test
    public void testEmptyInput() {
        in = new ByteArrayInputStream(("""
                """).getBytes()
        );

        var res = ReadCSVFile.csvToModel(in, Model.class);

        log.debug("Result list = {}", res);
        assertThat(res)
                .hasSize(0);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Model {
        @CsvBindByName(column = "date")
        @CsvDate("yyyy-MM-dd")
        protected LocalDate fieldDate;
        @CsvBindByName(column = "field3")
        protected String fieldString;
        @CsvBindByName(column = "field1")
        protected Integer fieldInteger;
    }

}
