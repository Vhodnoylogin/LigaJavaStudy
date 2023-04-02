package ru.liga.karmatskiyrg.controller.observers.dicts;

import ru.liga.karmatskiyrg.model.dicts.dates.interfaces.DDateType;
import ru.liga.karmatskiyrg.utils.observers.base.IfExistsGetAsObserver;

public final class IsParameterString extends IfExistsGetAsObserver<String, DDateType> {
    private static final IsParameterString singleton = new IsParameterString();

    private IsParameterString() {
    }

    public static IsParameterString getSingleton() {
        return singleton;
    }
}
