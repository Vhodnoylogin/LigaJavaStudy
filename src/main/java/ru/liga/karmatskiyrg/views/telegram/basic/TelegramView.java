package ru.liga.karmatskiyrg.views.telegram.basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.karmatskiyrg.views.interfaces.View;

@Slf4j
@RequiredArgsConstructor
public abstract class TelegramView implements View {
    private final TelegramLongPollingCommandBot bot;
    private final Long chatId;

    protected abstract SendMessage prepareMsg();

    protected Long getChatId() {
        return this.chatId;
    }


    @Override
    public void show() {
        try {

            this.bot.execute(this.prepareMsg());
        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
            log.error("Send message exception", e);
        }
    }
}
