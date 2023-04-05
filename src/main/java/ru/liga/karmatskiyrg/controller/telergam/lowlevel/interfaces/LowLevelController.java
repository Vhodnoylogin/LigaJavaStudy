package ru.liga.karmatskiyrg.controller.telergam.lowlevel.interfaces;

import ru.liga.karmatskiyrg.model.dicts.arguments.interfaces.DArgumentType;

import java.util.Map;

public interface LowLevelController<T> {
    T get(Map<DArgumentType, String> map);
}
