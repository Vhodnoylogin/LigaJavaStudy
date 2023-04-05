package ru.liga.karmatskiyrg.controller.telergam.output.basic;

import lombok.RequiredArgsConstructor;
import ru.liga.karmatskiyrg.controller.telergam.output.interfaces.OutController;
import ru.liga.karmatskiyrg.model.context.telegram.TelegramRateContext;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;

import java.util.List;

@RequiredArgsConstructor
public abstract class OutBasicController implements OutController {
    protected final TelegramRateContext context;
    protected final List<CurrencyRate> list;

}
