package ru.liga.karmatskiyrg.distributed.app.client.utils.observers.interfaces;

import java.util.function.Function;

public interface SwitchAsObserverPut<K, T> {
    boolean addVariant(Object globalVariantId, Function<K, Boolean> keyFunc, T variant);

}
