package ru.liga.karmatskiyrg.repository.interfaces;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;

public interface CurrencyTable extends Table<DCurrencyType, CurrencyRate> {
}
