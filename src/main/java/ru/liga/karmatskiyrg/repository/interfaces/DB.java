package ru.liga.karmatskiyrg.repository.interfaces;

import java.util.List;

public interface DB<K, V> {
    void save(V rate);

    void save(List<V> rate);

    List<V> getSlice(K key);

    List<V> getAll();
}
