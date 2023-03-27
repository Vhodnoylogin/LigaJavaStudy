package ru.liga.karmatskiyrg;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineCommand;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineParameter;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.parse.ParseCommandLine;
import ru.liga.karmatskiyrg.views.basic.ExceptionView;
import ru.liga.karmatskiyrg.views.currency.CurrencyView;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.time.LocalDate;
import java.util.Scanner;

@Slf4j
public class SimpleRun {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        boolean exit = false;

        var db = new CurrencyRepoRAM();
        db.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.csvFile));
        PredictCurrencyRate predictF = new PredictCurrencyRate(db);

        View view;
        while (!exit) {
            try {
                var tokens = ParseCommandLine.parseCommand(scanner.nextLine());

                if (tokens.size() != 3)
                    throw new NotValidCommand("Invalid number of arguments");

                DLineCommand command;
                try {
                    command = DLineCommands.getType(tokens.get(0));
                } catch (Exception e) {
                    command = null;
                }

                DCurrencyType currencyType;
                try {
                    currencyType = DCurrencyTypes.valueOf(tokens.get(1));
                } catch (Exception e) {
                    currencyType = null;
                }

                DLineParameter parameter;
                try {
                    parameter = DLineParameters.getType(tokens.get(2));
                } catch (Exception e) {
                    parameter = null;
                }

                if (command == null)
                    throw new NotValidCommand(String.format("Command %s not found", tokens.get(0)));
                if (currencyType == null)
                    throw new NotValidCommand(String.format("Currency %s not found", tokens.get(1)));
                if (parameter == null)
                    throw new NotValidCommand(String.format("Parameter %s not found", tokens.get(2)));

                if (command == DLineCommands.EXIT) {
                    view = View.EXIT_VIEW;
                    exit = true;
                } else if (command == DLineCommands.RATE) {
                    if (parameter == DLineParameters.TMR) {
                        var res = predictF.predictToDate(currencyType, LocalDate.now().plusDays(1))
                                .stream().limit(1).toList();
                        log.debug("Predict rates is {}", res);
                        view = new CurrencyView(res);
                    } else if (parameter == DLineParameters.WEK) {
                        var res = predictF.predictToDate(currencyType, LocalDate.now().plusDays(7));
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
