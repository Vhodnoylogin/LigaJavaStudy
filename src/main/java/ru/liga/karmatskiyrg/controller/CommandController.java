package ru.liga.karmatskiyrg.controller;

import ru.liga.karmatskiyrg.controller.base.Controller;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.views.basic.ExitView;
import ru.liga.karmatskiyrg.views.currency.CurrencyView;


public class CommandController extends Controller<RateContext> {
    public CommandController(RateContext context) {
        this.context = context;
    }

    public void rate() {
        var func = ParameterLeadAction.getSingleton().getFirstVariant(context.getParameter());
        var res = func.apply(context.getCurrencyType());
        this.context.setView(new CurrencyView(res));
    }

    public void exit() {
        this.context.setView(new ExitView());
        this.context
                .getControl()
                .exitLoop();
    }
}
