package ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.algorithm;

import ru.liga.karmatskiyrg.distributed.app.client.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.algorithm.interfaces.AlgorithmController;
import ru.liga.karmatskiyrg.distributed.app.client.utils.csv.CsvFileLayout;

public class OldAlgorithmController implements AlgorithmController {
    private static final String MESSAGE_KEY = "No -ALG argument among %s";
    private static final String MESSAGE_VAL = "No such algorithm = %s";

    public PredictCurrencyRate get() {
        var repo = new CurrencyRepoRAM();
        repo.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.getCsvFile()));
        return new PredictCurrencyRateOld(repo);
    }
}
