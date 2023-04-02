package ru.liga.karmatskiyrg.controller.observers;

import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.model.dicts.dates.interfaces.DDateType;
import ru.liga.karmatskiyrg.utils.observers.base.SuperSwitchAsObserver;

import java.util.List;
import java.util.function.Function;

public final class ParameterLeadAction extends SuperSwitchAsObserver<DDateType, Function<DCurrencyType, List<CurrencyRate>>> {
    private static final ParameterLeadAction singleton = new ParameterLeadAction();

    private ParameterLeadAction() {
    }

    public static ParameterLeadAction getSingleton() {
        return singleton;
    }
}
