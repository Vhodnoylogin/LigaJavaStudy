package ru.liga.karmatskiyrg;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.CommandController;
import ru.liga.karmatskiyrg.controller.ParameterController;
import ru.liga.karmatskiyrg.controller.observers.CommandLeadAction;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.service.initialize.Init;
import ru.liga.karmatskiyrg.service.interfaces.CurrencyPredict;
import ru.liga.karmatskiyrg.service.loop.LoopClass;
import ru.liga.karmatskiyrg.utils.loop.Context;
import ru.liga.karmatskiyrg.utils.parse.ParseCommandLine;

import java.util.Scanner;

@Slf4j
public class Run {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        var loop = new LoopClass();
        loop.setInitAction(x -> {
            initCommands(x);
            Init.initDicts();
        });
        loop.setAction(Run::context);
        loop.run();
    }

    private static void context(Context context) {
        var text = scanner.next();
        var tokens = ParseCommandLine.parseCommand(text);

        var command = DLineCommands.getType(tokens.get(0));

        var action = CommandLeadAction.getSingleton().getFirstVariant(command);
        if (action != null) action.run();
    }

    private static void initCommands(Context context) {

        var leadCommand = CommandLeadAction.getSingleton();
        var controller = new CommandController(context.getControl());

        leadCommand.addVariant(DLineCommands.EXIT, controller::exit);
        leadCommand.addVariant(DLineCommands.RATE, controller::rate);
    }

    private static void initParameters() {
        var leadParameter = ParameterLeadAction.getSingleton();
        var db = new CurrencyRepoRAM();
        CurrencyPredict predication = new PredictCurrencyRate(db);
        var controller = new ParameterController(predication);

        leadParameter.addVariant(DLineParameters.TMR, controller::getCurrencyRateTomorrow);
        leadParameter.addVariant(DLineParameters.WEK, controller::getCurrencyRateWeek);
    }

//    private static void initDicts() {
//        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class + "1", DCurrencyTypes::getType);
//        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class, (name) -> {
//            try {
//                return DCurrencyTypes.valueOf(name);
//            } catch (IllegalArgumentException e) {
//                return null;
//            }
//        });
//
//        IsCommandString.getSingleton().addVariant(DLineCommands.class, DLineCommands::getType);
//        IsParameterString.getSingleton().addVariant(DLineCommands.class, DLineParameters::getType);
//    }
}
