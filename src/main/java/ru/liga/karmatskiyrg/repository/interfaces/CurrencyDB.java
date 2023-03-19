package ru.liga.karmatskiyrg.repository.interfaces;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;

public interface CurrencyDB extends DB<DCurrencyType, CurrencyRate> {
}
