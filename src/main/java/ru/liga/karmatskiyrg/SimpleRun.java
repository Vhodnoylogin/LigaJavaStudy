package ru.liga.karmatskiyrg;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.context.ParseStringToContext;
import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.service.initialize.Init;
import ru.liga.karmatskiyrg.views.basic.EmptyView;
import ru.liga.karmatskiyrg.views.basic.ExceptionView;
import ru.liga.karmatskiyrg.views.basic.ExitView;
import ru.liga.karmatskiyrg.views.currency.CurrencyView;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.time.LocalDate;
import java.util.Scanner;

@Slf4j
public class SimpleRun {

    private static final View EMPTY_VIEW = new EmptyView();
    private static final PredictCurrencyRate predictF = new PredictCurrencyRate(new CurrencyRepoRAM());

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        String text;
        var context = new RateContext(null);
        boolean exit = false;
        Init.initDicts();

        View view;
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
                        log.debug(String.valueOf(res));
                        view = new CurrencyView(res);
                    } else if (param == DLineParameters.WEK) {
                        var res = predictF.predictToDate(context.getCurrencyType(), LocalDate.now().plusDays(1));
                        log.debug(String.valueOf(res));
                        view = new CurrencyView(res);
                    } else {
                        view = EMPTY_VIEW;
                    }
                } else {
                    view = EMPTY_VIEW;
                }
            } catch (NotValidCommand e) {
                view = new ExceptionView(e);
            }
            view.show();
        }
    }
}
