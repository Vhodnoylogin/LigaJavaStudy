package ru.liga.karmatskiyrg.application.telegram;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.telergam.RateCommandController;
import ru.liga.karmatskiyrg.model.context.telegram.TelegramRateContext;
import ru.liga.karmatskiyrg.model.dicts.commands.DCommands;
import ru.liga.karmatskiyrg.service.parsers.CommandParser;
import ru.liga.karmatskiyrg.utils.egg.CommandStringToExecutionEgg;

@Slf4j
public class CommandApplication implements CommandStringToExecutionEgg<TelegramRateContext> {
    @Override
    public void execute(String commandText, TelegramRateContext context) {
        var token = CommandParser.COMMAND_PARSER.getTokenFromCommandString(commandText);

        var command = DCommands.getType(token.getRight());
        if (DCommands.RATE == command) {
            new RateCommandController().action(token.getLeft(), context);
        } else if (DCommands.EXIT == command) {
            log.info("EXIT");
        } else {
            log.info("No such command = {}", token.getRight());
        }
    }


//    public SendMessage content(Update update) {
//        var text = update.getMessage().getText();
//        var tokens = ParserCommandLine.TOKEN_PARSER.parseCommand(text);
//
//        var command = IsCommandString.getSingleton().getFirstVariant(tokens.get(0).getValue());
//        var action = TelegramCommandLeadAction.getSingleton().getFirstVariant(command);
//
//        if (action == null)
//            throw new NotValidCommand("Action is null = " + command);
//
//        return action.apply(tokens, update);
//    }

//    public void initCommands() {
//        Init.initDictionaries();
//
//        var leadCommand = TelegramCommandLeadAction.getSingleton();
//        leadCommand.addVariant(DCommands.RATE, new RateCommandController()::action);
//        leadCommand.addVariant(DCommands.EXIT, new ExitCommandController()::action);
//
//
//        var leadDate = TelegramDateLeadAction.getSingleton();
//        leadDate.addVariant(DDateTypes.WEK,
//                () -> DateInterval.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(7))
//        );
//        leadDate.addVariant(DDateTypes.TMR,
//                () -> DateInterval.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(1))
//        );
//        leadDate.addVariant(DDateAnotherTypes.MTH,
//                () -> DateInterval.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(31))
//        );
//
//
//        var db = new CurrencyRepoRAM();
//        db.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.CSV_FILE));
//        var leadAlgorithm = TelegramAlgorithmLeadAction.getSingleton();
//        leadAlgorithm.addVariant(DAlgorithms.OLD,
//                new PredictCurrencyRateOld(db)
//        );
//    }

}
