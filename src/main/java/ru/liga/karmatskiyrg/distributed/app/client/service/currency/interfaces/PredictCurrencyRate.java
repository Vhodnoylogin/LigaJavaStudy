package ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces;

import ru.liga.karmatskiyrg.distributed.app.client.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.interfaces.DateInterval;

import java.time.LocalDate;
import java.util.List;

public interface PredictCurrencyRate {
    List<CurrencyRate> predictToDate(DCurrencyType type, LocalDate date);

    List<CurrencyRate> predictToDate(DCurrencyType type, DateInterval period);
}
