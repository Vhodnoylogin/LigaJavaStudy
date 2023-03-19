package ru.liga.karmatskiyrg.repository;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.repository.interfaces.CurrencyDB;

import java.util.*;
import java.util.function.Consumer;

@Slf4j
public class CurrencyRepoRAM implements CurrencyDB {
    protected Map<DCurrencyType, List<CurrencyRate>> data = new HashMap<>();

    @Override
    public void save(CurrencyRate rate) {
        var type = IsCurrencyString.getSingleton().getFirstVariant(rate.getName());

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
        var isCurrencyString = IsCurrencyString.getSingleton();

        Consumer<CurrencyRate> logInputVar = x -> log.debug("", x);
        Consumer<CurrencyRate> logTypeOfInputRow = x -> log.info("",
                isCurrencyString.getFirstVariant(x.getName())
        );

        //init map
        Consumer<CurrencyRate> initMapIfNeed = x -> this.data.put(
                isCurrencyString.getFirstVariant(x.getName()),
                this.data.getOrDefault(
                        isCurrencyString.getFirstVariant(x.getName()),
                        new ArrayList<>()
                )
        );
        Consumer<CurrencyRate> addNewVal = x -> this.data.get(isCurrencyString.getFirstVariant(x.getName())).add(x);

        rate.stream()
                .filter(x -> isCurrencyString.isVariant(x.getName()))
                .peek(logInputVar)
                .peek(logTypeOfInputRow)
                .peek(initMapIfNeed)
                .forEach(addNewVal);
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
