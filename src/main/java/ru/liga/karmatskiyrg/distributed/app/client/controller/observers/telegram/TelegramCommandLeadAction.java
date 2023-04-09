package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.commands.interfaces.DCommand;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base.SuperSwitchAsObserver;
import ru.liga.karmatskiyrg.distributed.app.client.utils.parse.tokens.Token;

import java.util.List;
import java.util.function.BiFunction;

public final class TelegramCommandLeadAction extends SuperSwitchAsObserver<DCommand, BiFunction<List<Token>, Update, SendMessage>> {
    private static final TelegramCommandLeadAction singleton = new TelegramCommandLeadAction();

    private TelegramCommandLeadAction() {
    }

    public static TelegramCommandLeadAction getSingleton() {
        return singleton;
    }
}
