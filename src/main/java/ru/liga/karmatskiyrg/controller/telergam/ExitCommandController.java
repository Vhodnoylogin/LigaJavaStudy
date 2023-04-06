package ru.liga.karmatskiyrg.controller.telergam;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.karmatskiyrg.model.context.telegram.TelegramRateContext;

public class ExitCommandController {

    public SendMessage action(String commandString, TelegramRateContext context) {
        var msg = new SendMessage();
        return msg;
    }
}
