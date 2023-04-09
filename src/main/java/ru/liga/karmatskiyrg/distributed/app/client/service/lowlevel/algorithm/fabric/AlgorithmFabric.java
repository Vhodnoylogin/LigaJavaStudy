package ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.algorithm.fabric;

import ru.liga.karmatskiyrg.distributed.app.client.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.algorithms.DAlgorithms;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.algorithms.interfaces.DAlgorithm;
import ru.liga.karmatskiyrg.distributed.app.client.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.distributed.app.client.utils.csv.CsvFileLayout;

public class AlgorithmFabric {
    private static final String MESSAGE_NOT_FOUND = "No such algorithm registered = %s";

    public static PredictCurrencyRate get(DAlgorithm algType) {
        var repo = new CurrencyRepoRAM();
        repo.save(CsvToCurrency.getCurrencyRate(CsvFileLayout.getCsvFile()));
        if (DAlgorithms.OLD == algType) {
            return new PredictCurrencyRateOld(repo);
        } else {
            throw new NotValidCommand(MESSAGE_NOT_FOUND.formatted(algType));
        }
    }
}
