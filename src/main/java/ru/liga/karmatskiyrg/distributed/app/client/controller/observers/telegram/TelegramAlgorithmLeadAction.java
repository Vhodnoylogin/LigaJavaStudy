package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.telegram;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.algorithms.interfaces.DAlgorithm;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base.SuperSwitchAsObserver;

public final class TelegramAlgorithmLeadAction extends SuperSwitchAsObserver<DAlgorithm, PredictCurrencyRate> {
    private static final TelegramAlgorithmLeadAction singleton = new TelegramAlgorithmLeadAction();

    private TelegramAlgorithmLeadAction() {
    }

    public static TelegramAlgorithmLeadAction getSingleton() {
        return singleton;
    }
}
