package ru.liga.karmatskiyrg.controller.observers.dicts;

import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.utils.observers.base.IfExistsGetAsObserver;

public class IsArgumentString extends IfExistsGetAsObserver<String, DArgumentType> {
    private static final IsArgumentString singleton = new IsArgumentString();

    private IsArgumentString() {
    }

    public static IsArgumentString getSingleton() {
        return singleton;
    }
}
