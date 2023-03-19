package ru.liga.karmatskiyrg.service.interfaces;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyPredict {
    List<CurrencyRate> predictToDate(DCurrencyType type, LocalDate date);
}
