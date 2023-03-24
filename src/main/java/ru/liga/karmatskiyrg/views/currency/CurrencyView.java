package ru.liga.karmatskiyrg.views.currency;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.util.List;

@Slf4j
@Value
public class CurrencyView implements View {
    List<CurrencyRate> results;


    @Override
    public void show() {
        if (results == null) {
            log.info("Result is empty");
            return;
        }

        log.info(String.valueOf(results.size()));
        results.forEach(x -> log.info(String.valueOf(x)));
    }
}
