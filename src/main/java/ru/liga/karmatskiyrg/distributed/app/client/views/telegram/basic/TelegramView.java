package ru.liga.karmatskiyrg.distributed.app.client.views.telegram.basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.karmatskiyrg.distributed.app.client.views.interfaces.View;

@Slf4j
@RequiredArgsConstructor
public abstract class TelegramView implements View {
    private final TelegramLongPollingCommandBot bot;

    protected abstract Object prepareMsg();

    @Override
    public void show() {
        try {
            var message = prepareMsg();
            // отправляем сообщение в зависимости от его типа
            if (message instanceof SendMessage text) {
                bot.execute(text);
            } else if (message instanceof SendPhoto photo) {
                bot.execute(photo);
            }
        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
            log.error("Send message exception", e);
        }
    }
}
