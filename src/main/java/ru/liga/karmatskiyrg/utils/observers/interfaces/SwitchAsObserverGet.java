package ru.liga.karmatskiyrg.utils.observers.interfaces;

import java.util.List;

public interface SwitchAsObserverGet<K, R> {
    R getFirstVariant(K key);

    List<R> getAllVariants(K key);

    boolean isVariant(K key);
}
