package ru.liga.karmatskiyrg.utils.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces.PredictCurrencyRate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestCompClasses {

    @Test
    void testClassAndInterface() {
        var cl = PredictCurrencyRateOld.class;
        var in = PredictCurrencyRate.class;

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
