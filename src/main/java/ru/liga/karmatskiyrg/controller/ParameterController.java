package ru.liga.karmatskiyrg.controller;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.service.interfaces.CurrencyPredict;

import java.util.List;

public class ParameterController {

    protected final CurrencyPredict prediction;

    public ParameterController(CurrencyPredict prediction) {
        this.prediction = prediction;
    }

    public List<CurrencyRate> getCurrencyRateTomorrow(DCurrencyType type) {
        throw new RuntimeException();
    }

    public List<CurrencyRate> getCurrencyRateWeek(DCurrencyType type) {
        throw new RuntimeException();
    }
}
