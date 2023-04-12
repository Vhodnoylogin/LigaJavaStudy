package ru.liga.karmatskiyrg.distributed.app.client.controller.telergam;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.Controller;

@Slf4j
@Controller("RateCommandController")
public class RateCommandController {
//    private static final String ERR_MSG = "Command doesn't contains %s argument";
//
//
////    private ParserCommandLine parser = RateParser.RATE_PARSER;
//
//
//    //    public SendMessage action(String commandString, TelegramRateContext context) {
////    public SendMessage action(String commandString, Long chatId) {
////    public Object action(String commandString, Long chatId) {
////        var tokens = ArgumentsParser.RATE_PARSER.getTokenFromCommandString(commandString).getLeft();
////
////        var curType = new CurrencyController().get(tokens);
////        var datePeriod = DatePeriodFabric.getDatePeriod(tokens).get(tokens);
////        var alg = new AlgorithmController().get(tokens);
////
////        var res = alg.predictToDate(curType, datePeriod);
////
//////        var msg = new SendMessage();
//////        msg.setText(res.toString());
////////        msg.setChatId(context.getUpdate().getMessage().getChatId());
//////        msg.setChatId(chatId);
//////        return msg;
////        return OutputFabric.get(tokens).get(res, chatId);
////    }
//
//    @ControllerMethod("rate")
//    public String action(String cur, String period, String alg) {
////        return "Simple rate action without output parameter";
//        var algOb = Egg.getObject(alg, OldAlgorithmController.class).get();
//        var periodOb = Egg.getObject(period, PeriodController.class).get();
//        var curOb = DCurrencyTypes.getShortNameType(cur);
//
//
//        var res = algOb.predictToDate(curOb, periodOb);
//        return res.toString();
//    }
//
////    @ControllerMethod("rate")
////    public String qweqwe(String cur, String period, String date, String alg, String output) { //--qwrwerwe gdgder
////
////    }
//
//    @ControllerMethod("rate")
//    public String sdfsdf(String cur, String period, String alg, String output) {
//
//    }
//
//    @ControllerMethod("rate")
//    public String werwer(String cur, String date, String alg, String output) {
//        var qweqwe = Egg.getObject(date, DateController.class).get(date);
//
//    }
//
////    @ControllerMethod("rate")
////    public Object action(String cur, String period, String alg, String output) {
////        if (output.equals("graph")) {
////            return 7.4;
////        }
////        return "Simple rate action with output parameter = " + output;
////    }
}
