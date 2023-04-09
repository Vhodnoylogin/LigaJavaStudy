package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.dicts;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.algorithms.interfaces.DAlgorithm;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base.IfExistsGetAsObserver;

public class IsAlgorithmString extends IfExistsGetAsObserver<String, DAlgorithm> {
    private static final IsAlgorithmString singleton = new IsAlgorithmString();

    private IsAlgorithmString() {
    }

    public static IsAlgorithmString getSingleton() {
        return singleton;
    }
}
