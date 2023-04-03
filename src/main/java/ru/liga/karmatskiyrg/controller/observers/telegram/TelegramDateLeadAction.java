package ru.liga.karmatskiyrg.controller.observers.telegram;

import ru.liga.karmatskiyrg.model.dicts.dates.interfaces.DDateType;
import ru.liga.karmatskiyrg.utils.dates.DateInterval;
import ru.liga.karmatskiyrg.utils.observers.base.SuperSwitchAsObserver;

import java.util.function.Supplier;

public final class TelegramDateLeadAction extends SuperSwitchAsObserver<DDateType, Supplier<DateInterval>> {
    private static final TelegramDateLeadAction singleton = new TelegramDateLeadAction();

    private TelegramDateLeadAction() {
    }

    public static TelegramDateLeadAction getSingleton() {
        return singleton;
    }
}
