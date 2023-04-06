package ru.liga.karmatskiyrg.views.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import ru.liga.karmatskiyrg.views.telegram.basic.TelegramView;

public class TextView extends TelegramView {
    private final Object msg;

    public TextView(TelegramLongPollingCommandBot bot, Object msg) {
        super(bot);
        this.msg = msg;
    }

    @Override
    protected Object prepareMsg() {
        return this.msg;
    }
}
