package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.telegram;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.dates.interfaces.DDateType;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DateInterval;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base.SuperSwitchAsObserver;

import java.util.function.Supplier;

public final class TelegramDateLeadAction extends SuperSwitchAsObserver<DDateType, Supplier<DateInterval>> {
    private static final TelegramDateLeadAction singleton = new TelegramDateLeadAction();

    private TelegramDateLeadAction() {
    }

    public static TelegramDateLeadAction getSingleton() {
        return singleton;
    }
}
