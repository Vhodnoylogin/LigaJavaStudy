package ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base;

import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.interfaces.SwitchAsObserverGet;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.interfaces.SwitchAsObserverPut;

import java.util.*;
import java.util.function.Function;

public abstract class SuperSwitchAsObserver<K, T> implements SwitchAsObserverGet<K, T>, SwitchAsObserverPut<K, T> {
    private final List<Object> orderOfVariants = new ArrayList<>();
    private final Map<Object, Function<K, Boolean>> checkMap = new HashMap<>();
    private final Map<Object, T> variantMap = new HashMap<>();

    @Override
    public boolean addVariant(Object globalVariantId, Function<K, Boolean> keyFunc, T variant) {
        return UtilityForObservers.addVariant(
                this.orderOfVariants,
                this.checkMap,
                this.variantMap,
                globalVariantId,
                keyFunc,
                variant
        );
    }


    public boolean addVariant(Object globalVariantId, K key, T variant) {
        return UtilityForObservers.addVariant(
                this.orderOfVariants,
                this.checkMap,
                this.variantMap,
                globalVariantId,
                (x) -> Objects.equals(x, key),
                variant
        );
    }

    public boolean addVariant(K key, T variant) {
        return UtilityForObservers.addVariant(
                this.orderOfVariants,
                this.checkMap,
                this.variantMap,
                key,
                (x) -> Objects.equals(x, key),
                variant
        );
    }

    @Override
    public T getFirstVariant(K key) {
        var res = getAllVariants(key);
        if (res.isEmpty()) return null;
        return res.get(0);
    }

    @Override
    public List<T> getAllVariants(K key) {
        return UtilityForObservers.getAllVariants(
                this.orderOfVariants,
                this.checkMap,
                this.variantMap,
                key
        );
    }

    @Override
    public boolean isVariant(K key) {
        return UtilityForObservers.isVariant(orderOfVariants, checkMap, key);
    }
}
