package ru.liga.karmatskiyrg;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.CommandController;
import ru.liga.karmatskiyrg.controller.ParameterController;
import ru.liga.karmatskiyrg.controller.observers.CommandLeadAction;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.DLineParameters;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.service.interfaces.CurrencyPredict;
import ru.liga.karmatskiyrg.utils.loop.Context;
import ru.liga.karmatskiyrg.utils.loop.LoopClass;
import ru.liga.karmatskiyrg.utils.parse.ParseCommandLine;

import java.util.Scanner;

@Slf4j
public class Run {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        var loop = new LoopClass();
        loop.setInitAction(x -> {
            initCommands(x);
            initCurrencyDict();
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

        var isLineCommand = CommandLeadAction.getSingleton();
        var controller = new CommandController(context.getControl());

        isLineCommand.addVariant(DLineCommands.EXIT, controller::exit);
        isLineCommand.addVariant(DLineCommands.RATE, controller::rate);
    }

    private static void initParameters() {
        var isLineCommand = ParameterLeadAction.getSingleton();
        var db = new CurrencyRepoRAM();
        CurrencyPredict predication = new PredictCurrencyRate(db);
        var controller = new ParameterController(predication);

        isLineCommand.addVariant(DLineParameters.TMR, controller::getCurrencyRateTomorrow);
        isLineCommand.addVariant(DLineParameters.WEK, controller::getCurrencyRateWeek);
    }

    private static void initCurrencyDict() {
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class + "1", DCurrencyTypes::getType);
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class, (name) -> {
            try {
                return DCurrencyTypes.valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        });
    }
}
