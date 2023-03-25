package ru.liga.karmatskiyrg.views.currency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CurrencyView implements View {
    private final List<CurrencyRate> results;
    @Override
    public void show() {
        if (results == null) {
            log.info("Result is empty");
            return;
        }

        log.info("List size = {}", results.size());
        results.forEach(x -> log.info("Element = {}", x));
    }
}
