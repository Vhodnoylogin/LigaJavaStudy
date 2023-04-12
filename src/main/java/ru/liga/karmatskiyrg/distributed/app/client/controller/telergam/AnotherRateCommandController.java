package ru.liga.karmatskiyrg.distributed.app.client.controller.telergam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.algorithm.interfaces.AlgorithmController;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.DateController;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.interfaces.PeriodController;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.Controller;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.ControllerMethod;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.aruments.ArgName;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.aruments.ArgNameController;

@Slf4j
@Controller("AnotherRateCommandController")
@RequiredArgsConstructor
public class AnotherRateCommandController {

    @ControllerMethod("test")
    public String tezd(@ArgName("cur") String cur) {
        return cur + " " + cur;
    }

    @ControllerMethod("rate")
    public String actionPeriod(@ArgName("cur") String cur, @ArgNameController("period") PeriodController period, @ArgNameController("alg") AlgorithmController alg) {
        var curr = DCurrencyTypes.getShortNameType(cur);
        var res = alg.get().predictToDate(curr, period.get());
        return res.toString();
    }

    @ControllerMethod("rate")
    public String actionDate(@ArgName("cur") String cur, @ArgName("date") String date, @ArgNameController("alg") AlgorithmController alg) {
        var curr = DCurrencyTypes.getShortNameType(cur);
        var localDate = DateController.toDateInterval(date);
        var res = alg.get().predictToDate(curr, localDate);
        return res.toString();
    }
}
