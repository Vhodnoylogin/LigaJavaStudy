package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.dicts;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base.IfExistsGetAsObserver;

public final class IsCurrencyString extends IfExistsGetAsObserver<String, DCurrencyType> {
    private static final IsCurrencyString singleton = new IsCurrencyString();

    private IsCurrencyString() {
    }

    public static IsCurrencyString getSingleton() {
        return singleton;
    }
}
