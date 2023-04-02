package ru.liga.karmatskiyrg.controller;

import ru.liga.karmatskiyrg.controller.base.Controller;
import ru.liga.karmatskiyrg.model.context.loop.RateLoopContext;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.service.currency.interfaces.CurrencyPredict;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class ParameterController extends Controller<RateLoopContext> {
    private final CurrencyPredict prediction;

    private final Predicate<CurrencyRate> notPastDates = x -> LocalDate.now().isBefore(x.getDate());

    public ParameterController(RateLoopContext context, CurrencyPredict prediction) {
        this.prediction = prediction;
        this.context = context;
    }

    public List<CurrencyRate> getCurrencyRateTomorrow(DCurrencyType type) {
        return this.prediction
                .predictToDate(type, LocalDate.now().plusDays(1))
                .stream()
                .limit(1)
                .filter(notPastDates)
                .toList();
    }

    public List<CurrencyRate> getCurrencyRateWeek(DCurrencyType type) {
        return this.prediction.predictToDate(type, LocalDate.now().plusDays(7))
                .stream()
                .filter(notPastDates)
                .toList();
    }
}
