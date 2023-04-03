package ru.liga.karmatskiyrg.controller.observers.dicts;

import ru.liga.karmatskiyrg.model.dicts.dates.interfaces.DDateType;
import ru.liga.karmatskiyrg.utils.observers.base.IfExistsGetAsObserver;

public final class IsPeriodString extends IfExistsGetAsObserver<String, DDateType> {
    private static final IsPeriodString singleton = new IsPeriodString();

    private IsPeriodString() {
    }

    public static IsPeriodString getSingleton() {
        return singleton;
    }
}
