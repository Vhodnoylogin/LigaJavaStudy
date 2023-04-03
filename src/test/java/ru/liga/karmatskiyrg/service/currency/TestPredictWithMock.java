package ru.liga.karmatskiyrg.service.currency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.controller.observers.initialize.Init;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.interfaces.PredictCurrencyRate;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class TestPredictWithMock {
    protected static PredictCurrencyRate predict;
    @BeforeAll
    public static void init() {
        Init.initDictionaries();

        var repo = mock(CurrencyRepoRAM.class);
        var fake = new ArrayList<CurrencyRate>() {{
            add(new CurrencyRate(1, LocalDate.now().plusDays(-2), 15d, DCurrencyTypes.EUR.getLongName()));
            add(new CurrencyRate(1, LocalDate.now().plusDays(-1), 13d, DCurrencyTypes.EUR.getLongName()));
        }};
        when(repo.getSlice(DCurrencyTypes.EUR))
                .thenReturn(fake);
        predict = new PredictCurrencyRateOld(repo);
    }

    @Test
    public void testPredictEUR() {
        var res = predict.predictToDate(
                DCurrencyTypes.EUR,
                LocalDate.now().plusDays(2)
        );
        log.info("Resulting list = {}", res);
        assertThat(res)
                .isNotNull()
                .hasSizeGreaterThan(0);
    }
}
