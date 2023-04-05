package ru.liga.karmatskiyrg.controller.telergam.lowlevel.algorithm;

import ru.liga.karmatskiyrg.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.controller.telergam.lowlevel.algorithm.fabric.AlgorithmFabric;
import ru.liga.karmatskiyrg.controller.telergam.lowlevel.interfaces.LowLevelController;
import ru.liga.karmatskiyrg.model.dicts.algorithms.DAlgorithms;
import ru.liga.karmatskiyrg.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.service.currency.interfaces.PredictCurrencyRate;

import java.util.Map;

public class AlgorithmController implements LowLevelController<PredictCurrencyRate> {
    private static final String MESSAGE_KEY = "No -ALG argument among %s";
    private static final String MESSAGE_VAL = "No such algorithm = %s";

    @Override
    public PredictCurrencyRate get(Map<DArgumentType, String> map) {
        if (!map.containsKey(DArgumentTypes.ALG)) {
            throw new NotValidCommand(MESSAGE_KEY.formatted(map.keySet()));
        }
        var row = map.get(DArgumentTypes.ALG);
        var algType = DAlgorithms.getType(row);
        if (algType == null) {
            throw new NotValidCommand(MESSAGE_VAL.formatted(row));
        }

        return AlgorithmFabric.get(algType);
    }
}
