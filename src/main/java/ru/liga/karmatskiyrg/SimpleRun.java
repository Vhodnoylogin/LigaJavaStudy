package ru.liga.karmatskiyrg;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.controller.initialize.Init;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.context.ParseStringToContext;
import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.views.basic.ExceptionView;
import ru.liga.karmatskiyrg.views.currency.CurrencyView;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.time.LocalDate;
import java.util.Scanner;

@Slf4j
public class SimpleRun {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        String text;
        var context = new RateContext(null);
        boolean exit = false;
        Init.initDictionaries();

        var db = new CurrencyRepoRAM();
        db.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.csvFile));
        PredictCurrencyRate predictF = new PredictCurrencyRate(db);

        View view;
        while (!exit) {
            try {
                text = scanner.nextLine();

                ParseStringToContext.parseArgs(context, text);

                var command = context.getCommand();
                if (command == DLineCommands.EXIT) {
                    view = View.EXIT_VIEW;
                    exit = true;
                } else if (command == DLineCommands.RATE) {
                    var param = context.getParameter();
                    if (param == DLineParameters.TMR) {
                        var res = predictF.predictToDate(context.getCurrencyType(), LocalDate.now().plusDays(1));
                        log.debug("Predict rates is {}", res);
                        view = new CurrencyView(res);
                    } else if (param == DLineParameters.WEK) {
                        var res = predictF.predictToDate(context.getCurrencyType(), LocalDate.now().plusDays(7));
                        log.debug("Predict rates is {}", res);
                        view = new CurrencyView(res);
                    } else {
                        view = View.EMPTY_VIEW;
                    }
                } else {
                    view = View.EMPTY_VIEW;
                }
            } catch (NotValidCommand e) {
                view = new ExceptionView(e);
            }
            view.show();
        }
    }
}
