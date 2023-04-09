package ru.liga.karmatskiyrg.distributed.app.client.controller.telergam;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.Controller;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.ControllerMethod;

@Slf4j
@Controller("RateCommandController")
public class RateCommandController {
    private static final String ERR_MSG = "Command doesn't contains %s argument";


//    private ParserCommandLine parser = RateParser.RATE_PARSER;


    //    public SendMessage action(String commandString, TelegramRateContext context) {
//    public SendMessage action(String commandString, Long chatId) {
//    public Object action(String commandString, Long chatId) {
//        var tokens = ArgumentsParser.RATE_PARSER.getTokenFromCommandString(commandString).getLeft();
//
//        var curType = new CurrencyController().get(tokens);
//        var datePeriod = DatePeriodFabric.getDatePeriod(tokens).get(tokens);
//        var alg = new AlgorithmController().get(tokens);
//
//        var res = alg.predictToDate(curType, datePeriod);
//
////        var msg = new SendMessage();
////        msg.setText(res.toString());
//////        msg.setChatId(context.getUpdate().getMessage().getChatId());
////        msg.setChatId(chatId);
////        return msg;
//        return OutputFabric.get(tokens).get(res, chatId);
//    }

    @ControllerMethod("rate")
    public SendMessage action(String cur, String period, String alg) {
        EGG
    }

}
