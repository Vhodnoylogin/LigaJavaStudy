package ru.liga.karmatskiyrg.application.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.controller.loop.ParameterController;
import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCommandString;
import ru.liga.karmatskiyrg.controller.observers.initialize.Init;
import ru.liga.karmatskiyrg.model.context.loop.RateLoopContext;
import ru.liga.karmatskiyrg.model.dicts.commands.interfaces.DCommand;
import ru.liga.karmatskiyrg.model.dicts.dates.DDateTypes;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.service.telegram.bot.TelegramBot;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;

import java.util.List;

public class ApplicationBot extends TelegramBot {
    private static final String BOT_NAME = "Vhodnoylogin_tst_bot";
    private static final String BOT_TOKEN = "6130241804:AAH0icUms3upX96vuFUl8eWQtwBelCM7BsM";

//    private final TelegramRateContext context = new TelegramRateContext();

    public ApplicationBot() {
        super(BOT_NAME, BOT_TOKEN);
        Init.initDictionaries();

    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
//        String userName = getUserName(msg);

        var tokens = msg.getText();
    }

    public void initParameters(RateLoopContext context) {
        var leadParameter = ParameterLeadAction.getSingleton();

        var db = new CurrencyRepoRAM();
        db.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.csvFile));
        var predication = new PredictCurrencyRateOld(db);

        var controller = new ParameterController(context, predication);
        leadParameter.addVariant(DDateTypes.TMR, controller::getCurrencyRateTomorrow);
        leadParameter.addVariant(DDateTypes.WEK, controller::getCurrencyRateWeek);
    }

    private DCommand parseTokens(List<String> tokens) {
        if (tokens.isEmpty())
            throw new NotValidCommand("Invalid number of arguments");

        var command = IsCommandString.getSingleton().getFirstVariant(tokens.get(0));
        if (command == null)
            throw new NotValidCommand(String.format("Command %s not found", tokens.get(0)));

        return command;
    }
}
