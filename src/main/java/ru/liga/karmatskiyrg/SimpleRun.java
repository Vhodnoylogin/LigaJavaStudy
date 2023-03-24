package ru.liga.karmatskiyrg;

import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCommandString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsParameterString;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.context.ParseStringToContext;
import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.views.basic.EmptyView;
import ru.liga.karmatskiyrg.views.basic.ExceptionView;
import ru.liga.karmatskiyrg.views.basic.ExitView;
import ru.liga.karmatskiyrg.views.currency.CurrencyView;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.time.LocalDate;
import java.util.Scanner;

public class SimpleRun {

    private static final View EMPTY_VIEW = new EmptyView();
    private static final PredictCurrencyRate predictF = new PredictCurrencyRate(new CurrencyRepoRAM());
    private static View view = new EmptyView();

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        String text;
        var context = new RateContext(null);
        boolean exit = false;
        initCurrencyDict();

        while (!exit) {
            try {
                text = scanner.nextLine();

                ParseStringToContext.parseArgs(context, text);

                var command = context.getCommand();
                if (command == DLineCommands.EXIT) {
                    view = new ExitView();
                    exit = true;
                } else if (command == DLineCommands.RATE) {
                    var param = context.getParameter();
                    if (param == DLineParameters.TMR) {
                        var res = predictF.predictToDate(context.getCurrencyType(), LocalDate.now().plusDays(1));
                        view = new CurrencyView(res);
                    } else if (param == DLineParameters.WEK) {
                        var res = predictF.predictToDate(context.getCurrencyType(), LocalDate.now().plusDays(1));
                        view = new CurrencyView(res);
                    } else {
                        view = EMPTY_VIEW;
                    }
                } else {
                    view = EMPTY_VIEW;
                }
            } catch (NotValidCommand e) {
                view = new ExceptionView(e);
            } finally {
                view.show();
            }
        }
    }

    private static void initCurrencyDict() {
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class + "1", DCurrencyTypes::getType);
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class, (name) -> {
            try {
                return DCurrencyTypes.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        });

        IsCommandString.getSingleton().addVariant(DLineCommands.class, DLineCommands::getType);
        IsParameterString.getSingleton().addVariant(DLineCommands.class, DLineParameters::getType);
    }
}
