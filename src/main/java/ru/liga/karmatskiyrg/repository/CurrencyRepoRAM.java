package ru.liga.karmatskiyrg.repository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.repository.interfaces.CurrencyTable;

import java.util.*;
import java.util.function.Consumer;

@Slf4j
public class CurrencyRepoRAM implements CurrencyTable {
    private final Map<DCurrencyType, List<CurrencyRate>> data = new HashMap<>();

    @Override
    public void save(@NonNull CurrencyRate rate) {
        var isCurrencyString = IsCurrencyString.getSingleton();

        var type = isCurrencyString.getFirstVariant(rate.getName());

        if (type == null) {
            log.debug(String.format("Type %s not found", rate.getName()));
            return;
        }

        var res = this.data.getOrDefault(type, new ArrayList<>());
        if (res.isEmpty()) {
            log.debug(String.format("Add new type = %s", type));
            this.data.put(type, res);
        }

        res.add(rate);
    }

    @Override
    public void save(List<CurrencyRate> rate) {
        Consumer<CurrencyRate> logInputVar = x -> log.debug(String.valueOf(x));
        Consumer<CurrencyRate> logTypeOfInputRow =
                x -> log.debug(String.valueOf(
                        IsCurrencyString.getSingleton().getFirstVariant(x.getName())
                ));

        rate.stream()
                .filter(x -> IsCurrencyString.getSingleton().isVariant(x.getName()))
                .peek(logInputVar)
                .peek(logTypeOfInputRow)
                .forEach(this::save);
    }

    @Override
    public List<CurrencyRate> getSlice(DCurrencyType cur) {
        return Collections.unmodifiableList(
                this.data.getOrDefault(cur, new ArrayList<>())
        );
    }

    @Override
    public List<CurrencyRate> getAll() {
        return this.data.values().stream()
                .flatMap(List::stream)
                .toList();
    }
}
