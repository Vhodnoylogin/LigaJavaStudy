package ru.liga.karmatskiyrg.views.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.karmatskiyrg.views.telegram.basic.TelegramView;

public class TextView extends TelegramView {
    private final SendMessage msg;

    public TextView(TelegramLongPollingCommandBot bot, SendMessage msg) {
        super(bot);
        this.msg = msg;
    }

    @Override
    protected SendMessage prepareMsg() {
        return this.msg;
    }
}
