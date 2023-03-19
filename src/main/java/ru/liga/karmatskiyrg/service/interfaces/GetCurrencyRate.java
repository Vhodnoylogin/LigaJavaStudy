package ru.liga.karmatskiyrg.service.interfaces;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface GetCurrencyRate {
    List<CurrencyRate> getCurrencyRate(InputStream in) throws IOException;
}
