package ru.liga.karmatskiyrg.controller.observers.dicts;

import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.utils.observers.base.IfExistsGetAsObserver;

public final class IsCurrencyString extends IfExistsGetAsObserver<String, DCurrencyType> {
    private static final IsCurrencyString singleton = new IsCurrencyString();

    private IsCurrencyString() {
    }

    public static IsCurrencyString getSingleton() {
        return singleton;
    }
}
