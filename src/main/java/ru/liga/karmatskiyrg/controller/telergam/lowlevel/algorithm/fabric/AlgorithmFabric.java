package ru.liga.karmatskiyrg.controller.telergam.lowlevel.algorithm.fabric;

import ru.liga.karmatskiyrg.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.model.dicts.algorithms.DAlgorithms;
import ru.liga.karmatskiyrg.model.dicts.algorithms.interfaces.DAlgorithm;
import ru.liga.karmatskiyrg.repository.CurrencyRepoRAM;
import ru.liga.karmatskiyrg.service.currency.CsvToCurrency;
import ru.liga.karmatskiyrg.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.service.currency.interfaces.PredictCurrencyRate;
import ru.liga.karmatskiyrg.utils.csv.CsvFileLayout;

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
