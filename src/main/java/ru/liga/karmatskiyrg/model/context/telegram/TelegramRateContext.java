package ru.liga.karmatskiyrg.model.context.telegram;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.views.interfaces.View;

@Setter
@Getter
@RequiredArgsConstructor
public class TelegramRateContext {
    private View view = View.EMPTY_VIEW;
    //    private List<Token> tokens;
    private final TelegramLongPollingCommandBot bot;
    private Update update;
}
