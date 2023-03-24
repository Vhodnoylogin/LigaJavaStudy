package ru.liga.karmatskiyrg;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.CommandController;
import ru.liga.karmatskiyrg.controller.ParameterController;
import ru.liga.karmatskiyrg.controller.observers.CommandLeadAction;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.service.initialize.Init;
import ru.liga.karmatskiyrg.service.interfaces.CurrencyPredict;
import ru.liga.karmatskiyrg.service.loop.LoopClass;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.parse.ParseCommandLine;

import java.util.Scanner;

@Slf4j
public class Run {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        var loop = new LoopClass();
        loop.setInitAction(context -> {
            Init.initDicts();
            initCommands(context);
            initParameters(context);
        });
        loop.setAction(Run::context);
        loop.run();
    }

    private static void context(RateContext context) {
        var text = scanner.nextLine();
        var tokens = ParseCommandLine.parseCommand(text);

        var command = DLineCommands.getType(tokens.get(0));

        var action = CommandLeadAction.getSingleton().getFirstVariant(command);
        if (action != null) action.run();
        context.getView().show();
    }

    private static void initCommands(RateContext context) {

        var leadCommand = CommandLeadAction.getSingleton();
        var controller = new CommandController(context);

        leadCommand.addVariant(DLineCommands.EXIT, controller::exit);
        leadCommand.addVariant(DLineCommands.RATE, controller::rate);
    }

    private static void initParameters(RateContext context) {
        var leadParameter = ParameterLeadAction.getSingleton();

        var db = new CurrencyRepoRAM();
        db.save(new CsvToCurrency().getCurrencyRate(CsvFileLayout.csvFile));
        CurrencyPredict predication = new PredictCurrencyRate(db);

        var controller = new ParameterController(context, predication);

        leadParameter.addVariant(DLineParameters.TMR, controller::getCurrencyRateTomorrow);
        leadParameter.addVariant(DLineParameters.WEK, controller::getCurrencyRateWeek);
    }
}
