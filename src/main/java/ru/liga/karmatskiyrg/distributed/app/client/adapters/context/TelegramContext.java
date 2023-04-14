package ru.liga.karmatskiyrg.distributed.app.client.adapters.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;

@Getter
@RequiredArgsConstructor
public class TelegramContext {
    private final TelegramLongPollingCommandBot bot;
    private final Long chatId;
}
