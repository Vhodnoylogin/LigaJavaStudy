package ru.liga.karmatskiyrg.distributed.app.client.controller.telergam;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.karmatskiyrg.distributed.app.client.model.context.telegram.TelegramRateContext;

public class ExitCommandController {

    public SendMessage action(String commandString, TelegramRateContext context) {
        var msg = new SendMessage();
        return msg;
    }
}
