package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.dicts;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.commands.interfaces.DCommand;
import ru.liga.karmatskiyrg.distributed.app.lib.observers.base.IfExistsGetAsObserver;

public final class IsCommandString extends IfExistsGetAsObserver<String, DCommand> {
    private static final IsCommandString singleton = new IsCommandString();

    private IsCommandString() {
    }

    public static IsCommandString getSingleton() {
        return singleton;
    }
}
