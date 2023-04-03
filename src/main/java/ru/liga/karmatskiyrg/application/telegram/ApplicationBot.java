package ru.liga.karmatskiyrg.application.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.karmatskiyrg.service.telegram.bot.TelegramBot;

public class ApplicationBot extends TelegramBot {
    private static final String BOT_NAME = "Vhodnoylogin_tst_bot";
    private static final String BOT_TOKEN = "6130241804:AAH0icUms3upX96vuFUl8eWQtwBelCM7BsM";

    private final InnerApp app;

    public ApplicationBot() {
        super(BOT_NAME, BOT_TOKEN);
        app = new InnerApp();
        app.initCommands();
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        var send = app.content(update);
        try {
            execute(send);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
