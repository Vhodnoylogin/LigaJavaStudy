package ru.liga.karmatskiyrg.utils.observers.base;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

class UtilityForObservers {
    public static <K, T> T getFirstVariant(
            List<Object> orderOfVariants,
            Map<Object, Function<K, Boolean>> checkMap,
            Map<Object, T> variantMap,
            K key
    ) {
        var res = getAllVariants(orderOfVariants, checkMap, variantMap, key);
        if (res.isEmpty()) return null;
        return res.get(0);
    }

    public static <K, T> boolean addVariant(
            List<Object> orderOfVariants,
            Map<Object, Function<K, Boolean>> checkMap,
            Map<Object, T> variantMap,
            Object globalVariantId, Function<K, Boolean> keyFunc, T variant
    ) {
        if (checkMap.containsKey(globalVariantId)) return false;

        orderOfVariants.add(globalVariantId);
        checkMap.put(globalVariantId, keyFunc);
        variantMap.put(globalVariantId, variant);

        return true;
    }

    public static <K> boolean isVariant(
            List<Object> orderOfVariants,
            Map<Object, Function<K, Boolean>> checkMap,
            K key
    ) {
        return filterVariants(orderOfVariants, checkMap, key).findAny().isPresent();
    }

    protected static <K> Stream<Object> filterVariants(
            List<Object> orderOfVariants,
            Map<Object, Function<K, Boolean>> checkMap,
            K key
    ) {
        return orderOfVariants.stream()
                .filter(x -> checkMap.get(x).apply(key));
    }

    public static <K, T> List<T> getAllVariants(
            List<Object> orderOfVariants,
            Map<Object, Function<K, Boolean>> checkMap,
            Map<Object, T> variantMap,
            K key
    ) {
        return filterVariants(orderOfVariants, checkMap, key)
                .map(variantMap::get)
                .toList();
    }
}
