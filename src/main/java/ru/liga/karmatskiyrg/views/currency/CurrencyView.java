package ru.liga.karmatskiyrg.views.currency;

import lombok.Value;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.util.List;

@Value
public class CurrencyView implements View {
    List<CurrencyRate> results;


    @Override
    public void show() {
        results.forEach(System.out::println);
    }
}
