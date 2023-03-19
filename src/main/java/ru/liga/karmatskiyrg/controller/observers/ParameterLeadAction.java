package ru.liga.karmatskiyrg.controller.observers;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineParameter;
import ru.liga.karmatskiyrg.utils.observers.base.SuperSwitchAsObserver;

import java.util.List;
import java.util.function.Function;

public final class ParameterLeadAction extends SuperSwitchAsObserver<DLineParameter, Function<DCurrencyType, List<CurrencyRate>>> {
    private static final ParameterLeadAction singleton = new ParameterLeadAction();

    private ParameterLeadAction() {
    }

    public static ParameterLeadAction getSingleton() {
        return singleton;
    }
}
