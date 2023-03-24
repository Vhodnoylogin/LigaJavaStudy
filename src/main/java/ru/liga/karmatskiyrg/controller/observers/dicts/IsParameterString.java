package ru.liga.karmatskiyrg.controller.observers.dicts;

import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineParameter;
import ru.liga.karmatskiyrg.utils.observers.base.IfExistsGetAsObserver;

public final class IsParameterString extends IfExistsGetAsObserver<String, DLineParameter> {
    private static final IsParameterString singleton = new IsParameterString();

    private IsParameterString() {
    }

    public static IsParameterString getSingleton() {
        return singleton;
    }
}
