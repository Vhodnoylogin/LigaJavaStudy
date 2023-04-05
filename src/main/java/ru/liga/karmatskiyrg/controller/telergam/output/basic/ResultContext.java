package ru.liga.karmatskiyrg.controller.telergam.output.basic;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import ru.liga.karmatskiyrg.model.context.telegram.TelegramRateContext;

public class ResultContext extends TelegramRateContext {
    public ResultContext(TelegramLongPollingCommandBot bot) {
        super(bot);
    }
}
