package ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.output.interfaces;

import ru.liga.karmatskiyrg.distributed.app.client.model.currency.CurrencyRate;

import java.util.List;

public interface OutputController {
    //    SendMessage get(List<CurrencyRate> rates, Long chatId);
    Object get(List<CurrencyRate> rates, Long chatId);
}
