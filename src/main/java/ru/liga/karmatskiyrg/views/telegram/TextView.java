package ru.liga.karmatskiyrg.views.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.karmatskiyrg.views.telegram.basic.TelegramView;

public class TextView extends TelegramView {
    private final String text;

    public TextView(TelegramLongPollingCommandBot bot, Long chatId, String text) {
        super(bot, chatId);
        this.text = text;
    }

    @Override
    protected SendMessage prepareMsg() {
        var id = this.getChatId();
        var msg = new SendMessage();
        msg.setChatId(this.getChatId());
        msg.setText(this.text);
        return msg;
    }
}
