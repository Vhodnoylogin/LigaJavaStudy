package ru.liga.karmatskiyrg.controller;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.service.interfaces.CurrencyPredict;

import java.time.LocalDate;
import java.util.List;

public class ParameterController {

    private final CurrencyPredict prediction;

    public ParameterController(CurrencyPredict prediction) {
        this.prediction = prediction;
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
