package ru.liga.karmatskiyrg.distributed.app.client.views.telegram.basic;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
public class ExceptionView extends TelegramView {
    private final @NonNull Exception e;
    private final Long chatId;

    public ExceptionView(TelegramLongPollingCommandBot bot, @NonNull Exception e, Long chatId) {
        super(bot);
        this.e = e;
        this.chatId = chatId;
    }


    @Override
    protected SendMessage prepareMsg() {
        var msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(this.e.getMessage());
        return msg;
    }

}
