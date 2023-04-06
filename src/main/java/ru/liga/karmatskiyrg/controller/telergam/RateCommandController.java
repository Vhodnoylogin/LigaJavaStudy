package ru.liga.karmatskiyrg.controller.telergam;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.controller.telergam.output.fabric.OutputFabric;
import ru.liga.karmatskiyrg.service.lowlevel.algorithm.AlgorithmController;
import ru.liga.karmatskiyrg.service.lowlevel.currency.CurrencyController;
import ru.liga.karmatskiyrg.service.lowlevel.period.fabric.DatePeriodFabric;
import ru.liga.karmatskiyrg.service.parsers.level2.RateParser;

@Slf4j
public class RateCommandController {
    private static final String ERR_MSG = "Command doesn't contains %s argument";


    //    public SendMessage action(String commandString, TelegramRateContext context) {
//    public SendMessage action(String commandString, Long chatId) {
    public Object action(String commandString, Long chatId) {
        var tokens = RateParser.RATE_PARSER.getTokenFromCommandString(commandString).getLeft();

        var curType = new CurrencyController().get(tokens);
        var datePeriod = DatePeriodFabric.getDatePeriod(tokens).get(tokens);
        var alg = new AlgorithmController().get(tokens);

        var res = alg.predictToDate(curType, datePeriod);

//        var msg = new SendMessage();
//        msg.setText(res.toString());
////        msg.setChatId(context.getUpdate().getMessage().getChatId());
//        msg.setChatId(chatId);
//        return msg;
        return OutputFabric.get(tokens).get(res, chatId);
    }

//    private static List<CurrencyRate> predictWithInputParams(Map<DArgumentType, String> tokenMap) {
//        if (tokenMap.containsKey(DArgumentTypes.DATE) && tokenMap.containsKey(DArgumentTypes.PERIOD)) {
//            throw new NotValidCommand("Contains exclusive arguments");
//        }
//
//        DateInterval interval;
//        if (tokenMap.containsKey(DArgumentTypes.DATE)) {
//            var dateString = tokenMap.get(DArgumentTypes.DATE);
//            var date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//            interval = DateInterval.of(date, date);
//        } else if (tokenMap.containsKey(DArgumentTypes.PERIOD)) {
//            var periodString = tokenMap.get(DArgumentTypes.PERIOD);
//            var period = IsPeriodString.getSingleton().getFirstVariant(periodString);
//            if (period == null) throw new NotValidCommand("Wrong period parameter = " + periodString);
//            var intervalGen = TelegramDateLeadAction.getSingleton().getFirstVariant(period);
//            if (intervalGen == null) throw new NotValidCommand("Wrong period parameter = " + periodString);
//            interval = intervalGen.get();
//        } else {
//            throw new NotValidCommand(ERR_MSG.formatted("DATE/PERIOD"));
//        }
//
//        if (!tokenMap.containsKey(DArgumentTypes.CUR)) {
//            throw new NotValidCommand(ERR_MSG.formatted("CURRENCY"));
//        }
//        var currencyType = IsCurrencyString.getSingleton().getFirstVariant(tokenMap.get(DArgumentTypes.CUR));
//        if (currencyType == null)
//            throw new NotValidCommand("Wrong currency parameter = " + tokenMap.get(DArgumentTypes.CUR));
//
//        if (!tokenMap.containsKey(DArgumentTypes.ALG)) {
//            throw new NotValidCommand(ERR_MSG.formatted("ALGORITHM"));
//        }
//        var algType = IsAlgorithmString.getSingleton().getFirstVariant(tokenMap.get(DArgumentTypes.ALG));
//        if (algType == null)
//            throw new NotValidCommand("Wrong algorithm parameter = " + tokenMap.get(DArgumentTypes.ALG));
//        var alg = TelegramAlgorithmLeadAction.getSingleton().getFirstVariant(algType);
//        if (alg == null) throw new NotValidCommand("Wrong algorithm parameter = " + tokenMap.get(DArgumentTypes.ALG));
//
//
////        log.info("{}", currencyType);
////        log.info("{}", interval);
//
//        return alg.predictToDate(currencyType, interval);
//    }
//
//    public SendMessage action(List<Token> tokens, Update update) {
//        var tokenMap = parse(tokens);
//
//        var res = predictWithInputParams(tokenMap);
//        var send = new SendMessage();
//        send.setChatId(update.getMessage().getChatId());
//        send.setText(res.toString());
//
////        if(!tokenMap.containsKey(DArgumentTypes.OUT)){
////
////        }
//
//        return send;
//    }
//
//    private Map<DArgumentType, String> parse(List<Token> tokens) {
//        return tokens.stream()
//                .skip(1)
//                .map(x -> Pair.of(
//                        IsArgumentString.getSingleton().getFirstVariant(x.getLeft())
//                        , x.getRight()
//                ))
//                .filter(x -> x.getLeft() != null)
//                .filter(x -> x.getRight() != null)
//                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
//    }


}
