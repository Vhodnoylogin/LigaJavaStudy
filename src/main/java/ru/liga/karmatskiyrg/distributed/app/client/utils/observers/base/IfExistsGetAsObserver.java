package ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base;

import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.interfaces.SwitchAsObserverGet;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.interfaces.SwitchAsObserverPut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class IfExistsGetAsObserver<K, T> implements SwitchAsObserverPut<K, Function<K, T>>, SwitchAsObserverGet<K, T> {
    private final List<Object> orderOfVariants = new ArrayList<>();
    private final Map<Object, Function<K, Boolean>> checkMap = new HashMap<>();
    private final Map<Object, Function<K, T>> variantMap = new HashMap<>();

    @Override
    public boolean addVariant(Object globalVariantId, Function<K, Boolean> keyFunc, Function<K, T> variant) {
        return UtilityForObservers.addVariant(
                this.orderOfVariants,
                this.checkMap,
                this.variantMap,
                globalVariantId,
                (x) -> variant.apply(x) != null,
                variant
        );
    }

    public boolean addVariant(Object globalVariantId, Function<K, T> variant) {
        return UtilityForObservers.addVariant(
                this.orderOfVariants,
                this.checkMap,
                this.variantMap,
                globalVariantId,
                (x) -> variant.apply(x) != null,
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
                ).stream()
                .map(x -> x.apply(key))
                .toList();
    }

    @Override
    public boolean isVariant(K key) {
        return UtilityForObservers.isVariant(orderOfVariants, checkMap, key);
    }
}
