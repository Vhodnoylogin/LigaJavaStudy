package ru.liga.karmatskiyrg.distributed.app.client.repository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.distributed.app.client.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.distributed.app.client.repository.interfaces.CurrencyTable;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.distributed.app.client.utils.csv.CsvFileLayout;

import java.util.*;
import java.util.function.Consumer;

@Slf4j
public class CurrencyRepoRAM implements CurrencyTable {
    private final Map<DCurrencyType, List<CurrencyRate>> data = new HashMap<>();

    public CurrencyRepoRAM() {
        this.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.getCsvFile()));
    }

    @Override
    public void save(@NonNull CurrencyRate rate) {
//        var isCurrencyString = IsCurrencyString.getSingleton();

//        var type = isCurrencyString.getFirstVariant(rate.getName());
        var type = DCurrencyTypes.getType(rate.getName());

        if (type == null) {
            log.debug("Type {} not found", rate.getName());
            return;
        }

        var res = this.data.getOrDefault(type, new ArrayList<>());
        if (res.isEmpty()) {
            log.debug("Add new type = {}", type);
            this.data.put(type, res);
        }

        res.add(rate);
    }

    @Override
    public void save(List<CurrencyRate> rate) {
        Consumer<CurrencyRate> logInputVar =
                x -> log.debug("Value is {}. Type of value is {}",
                        x,
                        x.getName()
                );
        logInputVar = x -> {
        };

        rate.stream()
//                .filter(x -> IsCurrencyString.getSingleton().isVariant(x.getName()))
                .peek(logInputVar)
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
