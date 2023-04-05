package ru.liga.karmatskiyrg.controller.telergam;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.karmatskiyrg.controller.interfaces.Controller;
import ru.liga.karmatskiyrg.model.context.telegram.TelegramRateContext;

public class ExitCommandController implements Controller<TelegramRateContext> {
    @Override
    public void action(String commandString, TelegramRateContext context) {
        var msg = new SendMessage();

    }
}
