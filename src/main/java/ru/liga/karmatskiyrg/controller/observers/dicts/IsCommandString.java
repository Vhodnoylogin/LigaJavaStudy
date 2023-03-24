package ru.liga.karmatskiyrg.controller.observers.dicts;

import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineCommand;
import ru.liga.karmatskiyrg.utils.observers.base.IfExistsGetAsObserver;

public final class IsCommandString extends IfExistsGetAsObserver<String, DLineCommand> {
    private static final IsCommandString singleton = new IsCommandString();

    private IsCommandString() {
    }

    public static IsCommandString getSingleton() {
        return singleton;
    }
}
