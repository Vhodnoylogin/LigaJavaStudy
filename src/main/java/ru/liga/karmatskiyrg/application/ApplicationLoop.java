package ru.liga.karmatskiyrg.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.CommandController;
import ru.liga.karmatskiyrg.controller.ParameterController;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.controller.observers.CommandLeadAction;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCommandString;
import ru.liga.karmatskiyrg.model.context.loop.RateLoopContext;
import ru.liga.karmatskiyrg.model.dicts.commands.DLineCommands;
import ru.liga.karmatskiyrg.model.dicts.commands.interfaces.DLineCommand;
import ru.liga.karmatskiyrg.model.dicts.dates.DDateTypes;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.parse.SimpleParserCommandLine;
import ru.liga.karmatskiyrg.views.basic.ExceptionView;

import java.util.List;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ApplicationLoop {
    public static void read(RateLoopContext context, Scanner scanner) {
        var text = scanner.hasNextLine() ? scanner.nextLine() : "";
        var tokens = SimpleParserCommandLine.SIMPLE_COMMAND_PARSER.parseCommand(text);
        context.setTokens(tokens);
    }

    public void context(RateLoopContext context) {
        try {
            var command = parseTokens(context.getTokens());
            var action = CommandLeadAction.getSingleton().getFirstVariant(command);

            if (action != null) action.run();
        } catch (Exception e) {
            context.setView(new ExceptionView(e));
        }

        context.getView().show();
    }

    public void initCommands(RateLoopContext context) {
        var leadCommand = CommandLeadAction.getSingleton();

        var controller = new CommandController(context);
        leadCommand.addVariant(DLineCommands.EXIT, controller::exit);
        leadCommand.addVariant(DLineCommands.RATE, controller::rate);
    }

    private DLineCommand parseTokens(List<String> tokens) {
        if (tokens.isEmpty())
            throw new NotValidCommand("Invalid number of arguments");

        var command = IsCommandString.getSingleton().getFirstVariant(tokens.get(0));
        if (command == null)
            throw new NotValidCommand(String.format("Command %s not found", tokens.get(0)));

        return command;
    }

    public void initParameters(RateLoopContext context) {
        var leadParameter = ParameterLeadAction.getSingleton();

        var db = new CurrencyRepoRAM();
        db.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.csvFile));
        var predication = new PredictCurrencyRate(db);

        var controller = new ParameterController(context, predication);
        leadParameter.addVariant(DDateTypes.TMR, controller::getCurrencyRateTomorrow);
        leadParameter.addVariant(DDateTypes.WEK, controller::getCurrencyRateWeek);
    }
}
