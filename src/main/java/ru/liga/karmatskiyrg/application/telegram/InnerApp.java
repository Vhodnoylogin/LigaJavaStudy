package ru.liga.karmatskiyrg.application.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCommandString;
import ru.liga.karmatskiyrg.controller.observers.initialize.Init;
import ru.liga.karmatskiyrg.controller.observers.telegram.TelegramAlgorithmLeadAction;
import ru.liga.karmatskiyrg.controller.observers.telegram.TelegramCommandLeadAction;
import ru.liga.karmatskiyrg.controller.observers.telegram.TelegramDateLeadAction;
import ru.liga.karmatskiyrg.controller.telergam.ExitCommandController;
import ru.liga.karmatskiyrg.controller.telergam.RateCommandController;
import ru.liga.karmatskiyrg.model.dicts.algorithms.DAlgorithms;
import ru.liga.karmatskiyrg.model.dicts.commands.DCommands;
import ru.liga.karmatskiyrg.model.dicts.dates.DDateAnotherTypes;
import ru.liga.karmatskiyrg.model.dicts.dates.DDateTypes;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.utils.dates.DateInterval;
import ru.liga.karmatskiyrg.utils.parse.ParserCommandLine;

import java.time.LocalDate;

@Slf4j
public class InnerApp {
    public SendMessage content(Update update) {
        var text = update.getMessage().getText();
        var tokens = ParserCommandLine.TOKEN_PARSER.parseCommand(text);

        var command = IsCommandString.getSingleton().getFirstVariant(tokens.get(0).getValue());
        var action = TelegramCommandLeadAction.getSingleton().getFirstVariant(command);

        if (action == null)
            throw new NotValidCommand("Action is null = " + command);

        return action.apply(tokens, update);
    }

    public void initCommands() {
        Init.initDictionaries();

        var leadCommand = TelegramCommandLeadAction.getSingleton();
        leadCommand.addVariant(DCommands.RATE, new RateCommandController()::action);
        leadCommand.addVariant(DCommands.EXIT, new ExitCommandController()::action);


        var leadDate = TelegramDateLeadAction.getSingleton();
        leadDate.addVariant(DDateTypes.WEK,
                () -> DateInterval.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(7))
        );
        leadDate.addVariant(DDateTypes.TMR,
                () -> DateInterval.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(1))
        );
        leadDate.addVariant(DDateAnotherTypes.MTH,
                () -> DateInterval.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(31))
        );


        var db = new CurrencyRepoRAM();
        db.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.CSV_FILE));
        var leadAlgorithm = TelegramAlgorithmLeadAction.getSingleton();
        leadAlgorithm.addVariant(DAlgorithms.OLD,
                new PredictCurrencyRateOld(db)
        );
    }

}
