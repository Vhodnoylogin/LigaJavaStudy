package ru.liga.karmatskiyrg.service.currency;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.repository.interfaces.CurrencyTable;
import ru.liga.karmatskiyrg.service.interfaces.CurrencyPredict;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class PredictCurrencyRate implements CurrencyPredict {
    private final Integer PREDICT_LEVEL = 7;
    private final Comparator<CurrencyRate> dateComparator = (x, y) -> {
        if (x.getDate().isAfter(y.getDate())) return -1;
        if (x.getDate().isBefore(y.getDate())) return 1;
        return 0;
    };
    private final CurrencyTable repo;

    public PredictCurrencyRate(CurrencyTable repo) {
        this.repo = repo;
    }


    private List<CurrencyRate> initPredictList(DCurrencyType type) {
        Predicate<CurrencyRate> notFutureDates = x -> !x.getDate().isAfter(LocalDate.now());
        return this.repo.getSlice(type).stream()
                .filter(notFutureDates)
                .sorted(dateComparator)
                .limit(PREDICT_LEVEL)
                .peek(x -> log.debug(String.valueOf(x)))
                .collect(Collectors.toList());
    }

    private CurrencyRate predictNext(List<CurrencyRate> currencyRateList) {
        var rate = currencyRateList.stream()
                .limit(PREDICT_LEVEL)
                .mapToDouble(x -> x.getRate() * x.getNominal())
                .sum();
        var nominal = currencyRateList.stream()
                .limit(PREDICT_LEVEL)
                .mapToInt(CurrencyRate::getNominal)
                .sum();

        var prev = currencyRateList.stream()
                .limit(1)
                .toList().get(0);

        return new CurrencyRate(
                prev.getNominal(),
                prev.getDate().plusDays(1),
                rate / nominal,
                prev.getName()
        );
    }

    public List<CurrencyRate> predictToDate(DCurrencyType type, LocalDate date) {
        var list = initPredictList(type);
        if (list.isEmpty()) return List.of();

        while (list.get(0).getDate().isBefore(date)) {
            list.add(0, this.predictNext(list));
        }
        return list.stream()
                .sorted(dateComparator)
                .limit(PREDICT_LEVEL)
                .toList();
    }
}
