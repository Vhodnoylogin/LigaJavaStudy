package ru.liga.karmatskiyrg.controller.observers.dicts;

import ru.liga.karmatskiyrg.model.dicts.algorithms.interfaces.DAlgorithm;
import ru.liga.karmatskiyrg.utils.observers.base.IfExistsGetAsObserver;

public class IsAlgorithmString extends IfExistsGetAsObserver<String, DAlgorithm> {
    private static final IsAlgorithmString singleton = new IsAlgorithmString();

    private IsAlgorithmString() {
    }

    public static IsAlgorithmString getSingleton() {
        return singleton;
    }
}
