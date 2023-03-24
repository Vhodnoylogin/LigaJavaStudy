package ru.liga.karmatskiyrg.controller;

import lombok.AllArgsConstructor;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.views.basic.ExitView;
import ru.liga.karmatskiyrg.views.currency.CurrencyView;

@AllArgsConstructor
public class CommandController {

    private final RateContext context;


    public void rate() {
        var func = ParameterLeadAction.getSingleton().getFirstVariant(context.getParameter());
        var res = func.apply(context.getCurrencyType());
        new CurrencyView(res).show();
    }

    public void exit() {
        new ExitView().show();
        this.context
                .getControl()
                .exitLoop();
    }
}
