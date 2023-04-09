package ru.liga.karmatskiyrg.distributed.app.client.repository.interfaces;

import ru.liga.karmatskiyrg.distributed.app.client.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces.DCurrencyType;

public interface CurrencyTable extends Table<DCurrencyType, CurrencyRate> {
}
