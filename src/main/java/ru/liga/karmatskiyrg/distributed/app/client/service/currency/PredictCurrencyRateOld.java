package ru.liga.karmatskiyrg.distributed.app.client.service.currency;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.distributed.app.client.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.distributed.app.client.repository.interfaces.CurrencyTable;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DateInterval;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class PredictCurrencyRateOld implements PredictCurrencyRate {
    private static final Integer PREDICT_LEVEL = 7;
    private static final Double TOO_BIG_RATE = 100.;
    private final Comparator<CurrencyRate> dateComparator = (x, y) -> {
        if (x.getDate().isAfter(y.getDate())) return -1;
        if (x.getDate().isBefore(y.getDate())) return 1;
        return 0;
    };
    private final CurrencyTable repo;

    private List<CurrencyRate> initPredictList(@NonNull DCurrencyType type, @NonNull LocalDate date) {
        Predicate<CurrencyRate> notFutureDates = x -> x.getDate().isBefore(date);

        return this.repo.getSlice(type).stream()
                .filter(notFutureDates)
                .sorted(dateComparator)
                .limit(PREDICT_LEVEL)
//                .peek(x -> log.debug("init rate = {}", x))
                .collect(Collectors.toList());
    }


    /**
     * Предсказание на основании предыдущих значений курса по формуле средневзвешенного
     * Все курсы перемножить на номиналы, а потом разделить на сумму наминалов.
     *
     * @param currencyRateList - список, на основании которого мы предсказывам курс
     * @return - новое предсказанное значение
     */
    private CurrencyRate predictNext(List<CurrencyRate> currencyRateList) {
        var rate = currencyRateList.stream()
                .limit(PREDICT_LEVEL)
                .mapToDouble(x -> x.getRate() * x.getNominal())
                .sum();
        var nominal = currencyRateList.stream()
                .limit(PREDICT_LEVEL)
                .mapToInt(CurrencyRate::getNominal)
                .sum();

        var prev = currencyRateList.get(0);

        var newNominal = 1000;
        var newRate = (newNominal * rate) / nominal;
        while (newRate > TOO_BIG_RATE) {
            newRate = newRate / 10;
            newNominal = newNominal / 10;
        }

        return new CurrencyRate(
                newNominal,
                prev.getDate().plusDays(1),
                newRate,
                prev.getName()
        );
    }

    @Override
    public List<CurrencyRate> predictToDate(DCurrencyType type, @NonNull LocalDate date) {
//        var list = initPredictList(type, date);
//        if (list.isEmpty()) return List.of();
//
//        while (list.get(0).getDate().isBefore(date)) {
//            list.add(0, this.predictNext(list));
//        }
//
//        return list.stream()
//                .sorted(dateComparator)
//                .limit(PREDICT_LEVEL)
//                .toList();

        return predictToDate(type, DateInterval.of(date, date));
    }

    @Override
    public List<CurrencyRate> predictToDate(DCurrencyType type, @NonNull DateInterval period) {
        var list = initPredictList(type, period.getStartDate());
        if (list.isEmpty()) return List.of();

        while (!list.get(0).getDate().isAfter(period.getEndDate())) {
            list.add(0, this.predictNext(list));
        }

        return list.stream()
                .sorted(dateComparator)
                .limit(period.numberOfDays())
                .toList();
    }
}
