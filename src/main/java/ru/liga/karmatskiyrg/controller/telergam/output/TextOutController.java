package ru.liga.karmatskiyrg.controller.telergam.output;

import ru.liga.karmatskiyrg.controller.telergam.output.basic.OutBasicController;
import ru.liga.karmatskiyrg.controller.telergam.output.interfaces.OutController;
import ru.liga.karmatskiyrg.model.context.telegram.TelegramRateContext;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.views.interfaces.View;
import ru.liga.karmatskiyrg.views.telegram.TextView;

import java.util.List;
import java.util.Map;

public class TextOutController extends OutBasicController implements OutController {

    public TextOutController(TelegramRateContext context, List<CurrencyRate> list) {
        super(context, list);
    }

    @Override
    public View get(Map<DArgumentType, String> map) {
        return new TextView(
                this.context.getBot(),
                this.context.getUpdate().getMessage().getChatId(),
                this.list.toString()
        );
    }
}
