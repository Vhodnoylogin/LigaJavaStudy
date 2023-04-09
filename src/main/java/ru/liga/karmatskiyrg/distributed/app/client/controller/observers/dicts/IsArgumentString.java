package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.dicts;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base.IfExistsGetAsObserver;

public class IsArgumentString extends IfExistsGetAsObserver<String, DArgumentType> {
    private static final IsArgumentString singleton = new IsArgumentString();

    private IsArgumentString() {
    }

    public static IsArgumentString getSingleton() {
        return singleton;
    }
}
