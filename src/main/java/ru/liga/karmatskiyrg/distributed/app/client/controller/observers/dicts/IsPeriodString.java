package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.dicts;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.dates.interfaces.DDateType;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base.IfExistsGetAsObserver;

public final class IsPeriodString extends IfExistsGetAsObserver<String, DDateType> {
    private static final IsPeriodString singleton = new IsPeriodString();

    private IsPeriodString() {
    }

    public static IsPeriodString getSingleton() {
        return singleton;
    }
}
