package ru.liga.karmatskiyrg.telegram.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.RateCommandController;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.distributed.app.client.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.distributed.app.client.utils.csv.CsvFileLayout;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DateInterval;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestCommandController {
    @Test
    public void testRateController() {
        var commandText = "-cur eur -period week -alg old";
        var res = new RateCommandController().action(commandText, 1L);
        var text = ((SendMessage) res).getText();
        log.info("{}", text);

        var repo = new CurrencyRepoRAM();
        repo.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.getCsvFile()));
        var predict = new PredictCurrencyRateOld(repo);
        var period = DateInterval.of(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7)
        );
        var check = predict.predictToDate(DCurrencyTypes.EUR, period);

        assertThat(text)
                .isEqualTo(check.toString());
    }
}
