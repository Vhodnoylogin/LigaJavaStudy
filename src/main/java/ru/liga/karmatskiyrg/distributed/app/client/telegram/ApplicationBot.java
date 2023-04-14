package ru.liga.karmatskiyrg.distributed.app.client.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.distributed.app.client.adapters.context.TelegramContext;
import ru.liga.karmatskiyrg.distributed.app.lib.router.Egg;
import ru.liga.karmatskiyrg.distributed.app.lib.router.interfaces.Router;

@Slf4j
public class ApplicationBot extends TelegramLongPollingCommandBot {
    private static final String BOT_NAME = "Vhodnoylogin_tst_bot";
    private static final String BOT_TOKEN = "6130241804:AAH0icUms3upX96vuFUl8eWQtwBelCM7BsM";

    private final Router router;

    public ApplicationBot() {
        super();
        router = new Egg();
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
        var text = update.getMessage().getText();
        log.debug("{}", text);
        var adapter = router.execute(text);
        adapter.doIt(new TelegramContext(this, update.getMessage().getChatId()));
    }

}
