package ru.liga.karmatskiyrg.test;

import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;

public class SuperTest {
    static {
        IsCurrencyString.getSingleton().addVariant(DCurrencyTypes.class, DCurrencyTypes::getType);
    }
}
