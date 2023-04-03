package ru.liga.karmatskiyrg.controller.observers.telegram;

import ru.liga.karmatskiyrg.model.dicts.algorithms.interfaces.DAlgorithm;
import ru.liga.karmatskiyrg.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.utils.observers.base.SuperSwitchAsObserver;

public final class TelegramAlgorithmLeadAction extends SuperSwitchAsObserver<DAlgorithm, PredictCurrencyRate> {
    private static final TelegramAlgorithmLeadAction singleton = new TelegramAlgorithmLeadAction();

    private TelegramAlgorithmLeadAction() {
    }

    public static TelegramAlgorithmLeadAction getSingleton() {
        return singleton;
    }
}
