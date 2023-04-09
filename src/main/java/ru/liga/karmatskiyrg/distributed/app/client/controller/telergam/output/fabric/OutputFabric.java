package ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.output.fabric;

import ru.liga.karmatskiyrg.distributed.app.client.controller.exceptions.NotValidCommand;
import ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.output.GraphOutputController;
import ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.output.TextOutputController;
import ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.output.interfaces.OutputController;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.interfaces.DArgumentType;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.outputs.DOutputTypes;

import java.util.Map;

public class OutputFabric {

    private static final String MESSAGE_VAL = "No such output type = %s";

    public static OutputController get(Map<DArgumentType, String> map) {
        if (!map.containsKey(DArgumentTypes.OUT)) {
            return new TextOutputController();
        }

        var row = map.get(DArgumentTypes.OUT);
        var outType = DOutputTypes.getType(row);
        if (outType == null) {
            throw new NotValidCommand(MESSAGE_VAL.formatted(row));
        }

        if (DOutputTypes.LIST == outType) {
            return new TextOutputController();
        } else if (DOutputTypes.GRAPH == outType) {
            return new GraphOutputController();
        }

        throw new NotValidCommand(MESSAGE_VAL.formatted(row));
    }
}
