package ru.liga.karmatskiyrg;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.liga.karmatskiyrg.application.telegram.ApplicationBot;

public class TelegramApplication {

    public static void main(String[] args) {
        try {
            var botsApi = new TelegramBotsApi(DefaultBotSession.class);
            var bot = new ApplicationBot();
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            System.exit(0);
        }
    }
}
