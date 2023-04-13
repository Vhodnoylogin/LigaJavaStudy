package ru.liga.karmatskiyrg.distributed.app.client.adapters.context;

import lombok.Data;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;

@Data
public class TelegramContext {
    private final TelegramLongPollingCommandBot bot;
    private final Long chatId;
}
