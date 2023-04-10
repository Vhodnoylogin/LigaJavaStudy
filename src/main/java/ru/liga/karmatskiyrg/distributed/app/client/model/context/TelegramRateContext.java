package ru.liga.karmatskiyrg.distributed.app.client.model.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.distributed.app.client.views.interfaces.View;

@Setter
@Getter
@RequiredArgsConstructor
public class TelegramRateContext {
    private View view = View.EMPTY_VIEW;
    //    private List<Token> tokens;
    private final TelegramLongPollingCommandBot bot;
    private final Update update;
}
