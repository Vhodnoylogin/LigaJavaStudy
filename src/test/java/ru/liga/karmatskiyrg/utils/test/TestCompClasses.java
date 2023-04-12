package ru.liga.karmatskiyrg.utils.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.WeekController;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.interfaces.PeriodController;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestCompClasses {

    @Test
    void testClassAndInterface() {
        var cl = WeekController.class;
        var in = PeriodController.class;

        var res = in.isAssignableFrom(cl);
        log.debug("{}", res);

        assertThat(res)
                .isTrue();
    }

    @Test
    void testStringAndString() {
        var cl = String.class;
        var in = String.class;

        var res = in.isAssignableFrom(cl);
        log.debug("{}", res);

        assertThat(res)
                .isTrue();
    }
}
