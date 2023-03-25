package ru.liga.karmatskiyrg.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.CommandController;
import ru.liga.karmatskiyrg.controller.ParameterController;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.controller.observers.CommandLeadAction;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.context.ParseStringToContext;
import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.views.basic.ExceptionView;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class Application {
    private final Scanner scanner;

    public void context(RateContext context) {
        var text = scanner.hasNextLine() ? scanner.nextLine() : "";
//        var tokens = ParseCommandLine.parseCommand(text);

        try {
            ParseStringToContext.parseArgs(context, text);
//            log.info(String.valueOf(context.getCommand()));


//        var command = DLineCommands.getType(tokens.get(0));
            var command = context.getCommand();

            var action = CommandLeadAction.getSingleton().getFirstVariant(command);
            if (action != null) action.run();
        } catch (NotValidCommand e) {
            context.setView(new ExceptionView(e));
        }

        context.getView().show();
    }

    public void initCommands(RateContext context) {
        var leadCommand = CommandLeadAction.getSingleton();

        var controller = new CommandController(context);
        leadCommand.addVariant(DLineCommands.EXIT, controller::exit);
        leadCommand.addVariant(DLineCommands.RATE, controller::rate);
    }

    public void initParameters(RateContext context) {
        var leadParameter = ParameterLeadAction.getSingleton();

        var db = new CurrencyRepoRAM();
        db.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.csvFile));
        var predication = new PredictCurrencyRate(db);

        var controller = new ParameterController(context, predication);
        leadParameter.addVariant(DLineParameters.TMR, controller::getCurrencyRateTomorrow);
        leadParameter.addVariant(DLineParameters.WEK, controller::getCurrencyRateWeek);
    }

//    public DLineCommand parseCommandLine(List<String> tokens){
//        if(tokens.isEmpty())
//            throw new NotValidCommand("Invalid number of arguments");
//
//        var command = IsCommandString.getSingleton().getFirstVariant(tokens.get(0));
//
//    }
}
