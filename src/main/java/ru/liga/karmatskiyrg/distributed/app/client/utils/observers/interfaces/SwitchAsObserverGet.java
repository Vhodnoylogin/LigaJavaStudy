package ru.liga.karmatskiyrg.distributed.app.client.utils.observers.interfaces;

import java.util.List;

public interface SwitchAsObserverGet<K, R> {
    R getFirstVariant(K key);

    List<R> getAllVariants(K key);

    boolean isVariant(K key);
}
