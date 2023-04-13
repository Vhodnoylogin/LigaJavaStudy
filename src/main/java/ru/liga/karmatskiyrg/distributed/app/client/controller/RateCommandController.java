package ru.liga.karmatskiyrg.distributed.app.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DateParser;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.interfaces.DateInterval;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.Controller;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.ControllerMethod;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.aruments.ArgName;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.aruments.ArgNameService;

@Slf4j
@Controller("AnotherRateCommandController")
@RequiredArgsConstructor
public class RateCommandController {

    @ControllerMethod("test")
    public String tezd(@ArgName("cur") String cur) {
        return cur + " " + cur;
    }

    @ControllerMethod("rate")
    public String actionPeriod(@ArgName("cur") String cur, @ArgNameService("period") DateInterval period, @ArgNameService("alg") PredictCurrencyRate alg) {
        var curr = DCurrencyTypes.getShortNameType(cur);
        var res = alg.predictToDate(curr, period);
        return res.toString();
    }

    @ControllerMethod("rate")
    public String actionDate(@ArgName("cur") String cur, @ArgName("date") String date, @ArgNameService("alg") PredictCurrencyRate alg) {
        var curr = DCurrencyTypes.getShortNameType(cur);
        var localDate = DateParser.toDate(date);
        var res = alg.predictToDate(curr, localDate);
        return res.toString();
    }
}
