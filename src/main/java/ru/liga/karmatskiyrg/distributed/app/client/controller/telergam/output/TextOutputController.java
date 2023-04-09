package ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.output;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.output.interfaces.OutputController;
import ru.liga.karmatskiyrg.distributed.app.client.model.currency.CurrencyRate;

import java.util.List;

public class TextOutputController implements OutputController {
    @Override
    public SendMessage get(List<CurrencyRate> rates, Long chatId) {
        var msg = new SendMessage();
        msg.setText(rates.toString());
//        msg.setChatId(context.getUpdate().getMessage().getChatId());
        msg.setChatId(chatId);
        return msg;
    }
}
