package ru.liga.karmatskiyrg.application.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.model.context.telegram.TelegramRateContext;

@Slf4j
public class ApplicationBot extends TelegramLongPollingCommandBot {
    private static final String BOT_NAME = "Vhodnoylogin_tst_bot";
    private static final String BOT_TOKEN = "6130241804:AAH0icUms3upX96vuFUl8eWQtwBelCM7BsM";

    private final CommandApplication router;
//    private final TelegramRateContext context;

    public ApplicationBot() {
        super();
//        super(BOT_NAME, BOT_TOKEN);
        router = new CommandApplication();
//        context = new TelegramRateContext(this);
//        app.initCommands();
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
//        this.context.setUpdate(update);
        var context = new TelegramRateContext(this, update);
        var text = update.getMessage().getText();
        log.debug("{}", text);
        router.execute(text, context);
    }

}
