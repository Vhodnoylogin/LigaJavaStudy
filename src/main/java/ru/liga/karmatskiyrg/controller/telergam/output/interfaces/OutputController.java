package ru.liga.karmatskiyrg.controller.telergam.output.interfaces;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;

import java.util.List;

public interface OutputController {
    //    SendMessage get(List<CurrencyRate> rates, Long chatId);
    Object get(List<CurrencyRate> rates, Long chatId);
}
