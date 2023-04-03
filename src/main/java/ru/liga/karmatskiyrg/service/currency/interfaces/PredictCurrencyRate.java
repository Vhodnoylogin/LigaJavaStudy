package ru.liga.karmatskiyrg.service.currency.interfaces;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.utils.dates.DateInterval;

import java.time.LocalDate;
import java.util.List;

public interface PredictCurrencyRate {
    List<CurrencyRate> predictToDate(DCurrencyType type, LocalDate date);

    List<CurrencyRate> predictToDate(DCurrencyType type, DateInterval period);
}
