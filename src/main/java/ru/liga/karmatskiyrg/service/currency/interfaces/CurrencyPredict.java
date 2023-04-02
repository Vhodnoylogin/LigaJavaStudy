package ru.liga.karmatskiyrg.service.currency.interfaces;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface CurrencyPredict {
    List<CurrencyRate> predictToDate(DCurrencyType type, LocalDate date);

    List<CurrencyRate> predictToDate(DCurrencyType type, Period period);
}
