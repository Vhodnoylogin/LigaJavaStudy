package ru.liga.karmatskiyrg.controller.loop;

import ru.liga.karmatskiyrg.controller.base.Controller;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsPeriodString;
import ru.liga.karmatskiyrg.model.context.loop.RateLoopContext;
import ru.liga.karmatskiyrg.views.currency.CurrencyView;
import ru.liga.karmatskiyrg.views.interfaces.View;


public class CommandController extends Controller<RateLoopContext> {
    public CommandController(RateLoopContext context) {
        this.context = context;
    }

    public void rate() {
        var tokens = this.context.getTokens();

        if (tokens.size() < 3)
            throw new NotValidCommand("Invalid number of arguments");

        var currencyType = IsCurrencyString.getSingleton().getFirstVariant(tokens.get(1));
        var parameter = IsPeriodString.getSingleton().getFirstVariant(tokens.get(2));


        if (currencyType == null)
            throw new NotValidCommand(String.format("Currency %s not found", tokens.get(1)));
        if (parameter == null)
            throw new NotValidCommand(String.format("Parameter %s not found", tokens.get(2)));

        var func = ParameterLeadAction.getSingleton().getFirstVariant(parameter);
        var res = func.apply(currencyType);

        this.context.setView(new CurrencyView(res));
    }

    public void exit() {
        this.context.setView(View.EXIT_VIEW);
        this.context
                .getControl()
                .exitLoop();
    }
}
