package ru.liga.karmatskiyrg.dates;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class TestDates {
    final Function<String, LocalDate> correct = x -> LocalDate.parse(x, DateTimeFormatter.ofPattern("d/M/yyyy"));
    final Function<String, LocalDate> incorrect = x -> LocalDate.parse(x, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Test
    public void testConvertDate() {
        var date = "5/05/2022";
        var res = correct.apply(date);

        log.info("Date is {}", res);
        assertThat(res)
                .hasDayOfMonth(5)
                .hasMonthValue(5)
                .hasYear(2022);
    }

    @Test
    public void testPassIncorrectOnDoubleDates() {
        var date = "04/12/2017";
        var res = incorrect.apply(date);

        log.info("Date is {}", res);
        assertThat(res)
                .hasDayOfMonth(4)
                .hasMonthValue(12)
                .hasYear(2017);
    }

    @Test
    public void testFallsOnIncorrectDateTemplate() {
        var date = "4/12/2017";

        assertThatThrownBy(() -> incorrect.apply(date))
                .isInstanceOf(DateTimeParseException.class);
    }
}
