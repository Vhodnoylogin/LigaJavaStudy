package ru.liga.karmatskiyrg.dates;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.test.SuperTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Slf4j
public class TestDates extends SuperTest {
    Function<String, LocalDate> convert = x -> LocalDate.parse(x, DateTimeFormatter.ofPattern("d/M/yyyy"));
    Function<String, LocalDate> incorrect = x -> LocalDate.parse(x, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Test
    public void test1() {
        var date = "5/05/2022";
        var res = convert.apply(date);
        log.info("date", res);
    }

    @Test
    public void test2() {
        var date = "04/12/2017";
        var res = convert.apply(date);
        log.info("date", res);
    }

    //    @Test(expected = DateTimeParseException.class)
    @Test
    public void test3() {
        var date = "4/12/2017";
        var res = incorrect.apply(date);
        log.info("date", res);
    }
}
