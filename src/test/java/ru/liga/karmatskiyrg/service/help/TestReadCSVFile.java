package ru.liga.karmatskiyrg.service.help;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import ru.liga.karmatskiyrg.test.SuperTest;
import ru.liga.karmatskiyrg.utils.csv.ReadCSVFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;

import static org.junit.Assert.assertArrayEquals;

@Slf4j
public class TestReadCSVFile extends SuperTest {

    public static Model[] standartModel = {new Model(), new Model()};

    protected static InputStream in;

    @BeforeAll
    public static void initClass() {
        standartModel[0].setV1(1);
        standartModel[0].setV2(LocalDate.parse("2023-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        standartModel[1].setV1(3);
        standartModel[1].setV2(LocalDate.parse("2023-01-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Before
    public void initTest() {
        in = new ByteArrayInputStream(("""
                field1;date
                1;2023-01-01
                3;2023-01-02
                 """).getBytes()
        );
    }

    @Test
    public void test1() {
        var res = ReadCSVFile.csvToModel(in, Model.class);

        log.info("res", res);
        assertArrayEquals(res.toArray(), standartModel);
    }

    @Test
    public void test2() {
        Function<String[], Model> func = x -> {
            var m = new Model();
            m.v1 = Integer.parseInt(x[0]);
            m.v2 = LocalDate.parse(x[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return m;
        };

        var res = ReadCSVFile.csvToModel(in, func);

        log.info("res", res);
        assertArrayEquals(res.toArray(), standartModel);
    }

    @Test
    public void test3() {
        in = new ByteArrayInputStream(("""
                field1;date
                 """).getBytes()
        );

        var res = ReadCSVFile.csvToModel(in, Model.class);
        log.info("res", res);
    }

    @Test
    public void test4() {
        in = new ByteArrayInputStream(("""
                """).getBytes()
        );

        var res = ReadCSVFile.csvToModel(in, Model.class);
        log.info("res", res);
    }


    public static class Model {
        @CsvBindByName(column = "date")
        @CsvDate("yyyy-MM-dd")
        protected LocalDate v2;
        @CsvBindByName(column = "field3")
        protected String v3;
        @CsvBindByName(column = "field1")
        protected Integer v1;

        @Override
        public String toString() {
            return "Model{" +
                    "v1=" + v1 +
                    ", v2=" + v2 +
                    ", v3='" + v3 + '\'' +
                    '}';
        }

        public void setV1(Integer v1) {
            this.v1 = v1;
        }

        public void setV2(LocalDate v2) {
            this.v2 = v2;
        }

        public void setV3(String v3) {
            this.v3 = v3;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Model model)) return false;

            if (!Objects.equals(v2, model.v2)) return false;
            if (!Objects.equals(v3, model.v3)) return false;
            return Objects.equals(v1, model.v1);
        }

        @Override
        public int hashCode() {
            int result = v2 != null ? v2.hashCode() : 0;
            result = 31 * result + (v3 != null ? v3.hashCode() : 0);
            result = 31 * result + (v1 != null ? v1.hashCode() : 0);
            return result;
        }
    }
}
