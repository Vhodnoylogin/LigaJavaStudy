package ru.liga.karmatskiyrg.distributed.app.client.controller.observers.initialize;

import ru.liga.karmatskiyrg.distributed.app.client.controller.observers.dicts.*;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.algorithms.DAlgorithms;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.DArgumentTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.commands.DCommands;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyAnotherTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.dates.DDateAnotherTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.dates.DDateTypes;

public class Init {
    public static void initDictionaries() {
        // Инициализация поиска по написному имени
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class + "1", DCurrencyTypes::getType);
        IsCurrencyString.getSingleton().addVariant(DCurrencyAnotherTypes.class + "1", DCurrencyAnotherTypes::getType);
        // Инициализация поиска по трехбуквенному имени
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class, DCurrencyTypes::getShortNameType);
        IsCurrencyString.getSingleton().addVariant(DCurrencyAnotherTypes.class, DCurrencyAnotherTypes::getExactlyNameType);

        // Инициализация поиска типа даты
        IsPeriodString.getSingleton().addVariant(DDateTypes.class, DDateTypes::getType);
        IsPeriodString.getSingleton().addVariant(DDateAnotherTypes.class, DDateAnotherTypes::getType);

        // Инициализация поиска команды
        IsCommandString.getSingleton().addVariant(DCommands.class, DCommands::getType);

        // Инициализация поиска алгоритмов
        IsAlgorithmString.getSingleton().addVariant(DAlgorithms.class, DAlgorithms::getType);

        // Инициализация поиска аргументов
        IsArgumentString.getSingleton().addVariant(DArgumentTypes.class, DArgumentTypes::getType);
    }
}
