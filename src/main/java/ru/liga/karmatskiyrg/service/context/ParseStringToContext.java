package ru.liga.karmatskiyrg.service.context;

import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCommandString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsParameterString;
import ru.liga.karmatskiyrg.utils.parse.ParseCommandLine;

public class ParseStringToContext {
    public static void parseArgs(RateContext context, String commandLine) throws NotValidCommand {
        var args = ParseCommandLine.parseCommand(commandLine);

        if (args.size() != 3)
            throw new NotValidCommand("Invalid number of arguments");

        var command = IsCommandString.getSingleton().getFirstVariant(args.get(0));
        var currencyType = IsCurrencyString.getSingleton().getFirstVariant(args.get(1));
        var parameter = IsParameterString.getSingleton().getFirstVariant(args.get(2));

        if (command == null)
            throw new NotValidCommand(String.format("Command %s not found", args.get(0)));

        if (currencyType == null)
            throw new NotValidCommand(String.format("Currency %s not found", args.get(1)));

        if (parameter == null)
            throw new NotValidCommand(String.format("Parameter %s not found", args.get(2)));

        context.setCommand(command);
        context.setCurrencyType(currencyType);
        context.setParameter(parameter);

    }
}
