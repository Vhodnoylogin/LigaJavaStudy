package ru.liga.karmatskiyrg.controller;

import ru.liga.karmatskiyrg.controller.base.Controller;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.service.interfaces.CurrencyPredict;

import java.time.LocalDate;
import java.util.List;

public class ParameterController extends Controller<RateContext> {
    private final CurrencyPredict prediction;

    public ParameterController(RateContext context, CurrencyPredict prediction) {
        this.prediction = prediction;
        this.context = context;
    }

    public List<CurrencyRate> getCurrencyRateTomorrow(DCurrencyType type) {
        return this.prediction.predictToDate(type, LocalDate.now().plusDays(1))
                .stream()
                .limit(1)
                .toList();
    }

    public List<CurrencyRate> getCurrencyRateWeek(DCurrencyType type) {
        return this.prediction.predictToDate(type, LocalDate.now().plusDays(8));
    }
}
