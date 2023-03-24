package ru.liga.karmatskiyrg.service.help;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.utils.csv.ReadCSVFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;


@Slf4j
public class TestReadCSVFile {

    public static Model[] standartModel = new Model[2];

    protected static InputStream in;

    @BeforeAll
    public static void initClass() {
        standartModel[0] = Model.builder()
                .fieldInteger(1)
                .fieldDate(LocalDate.parse("2023-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();

        standartModel[1] = Model.builder()
                .fieldInteger(3)
                .fieldDate(LocalDate.parse("2023-01-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
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

        log.info(res.toString());
//        assertThat(res.toArray())
//                .hasSameElementsAs(standartModel);
    }

    @Test
    public void testCsvToModelWithFunction() {
        Function<String[], Model> func = x -> Model.builder()
                .fieldInteger(Integer.parseInt(x[0]))
                .fieldDate(LocalDate.parse(x[1], DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();

        var res = ReadCSVFile.csvToModel(in, func);

        log.info(res.toString());
//        assertThat(res.toArray())
//                .hasSameElementsAs(standartModel);
    }

    @Test
    public void testInputIsOnlyHeaders() {
        in = new ByteArrayInputStream(("""
                field1;date
                 """).getBytes()
        );

        var res = ReadCSVFile.csvToModel(in, Model.class);
        log.info(res.toString());
//        assertThat(res.toArray())
//                .hasSameElementsAs()
//                .containsAll(standartModel);
    }

    @Test
    public void testEmptyInput() {
        in = new ByteArrayInputStream(("""
                """).getBytes()
        );

        var res = ReadCSVFile.csvToModel(in, Model.class);
        log.info(res.toString());
    }


    @Data
//    @Value
    @Builder
//    @Getter
//    @Setter
    @AllArgsConstructor
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
