package ru.liga.karmatskiyrg.views.currency;

import org.jetbrains.annotations.NotNull;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.util.List;

public class VCurrencyToConsole implements View {
    protected final List<CurrencyRate> results;

    public VCurrencyToConsole(@NotNull List<CurrencyRate> results) {
        this.results = results;
    }

    @Override
    public void show() {
        results.forEach(System.out::println);
    }
}
