package ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.interfaces;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.interfaces.DArgumentType;

import java.util.Map;

public interface LowLevelController<T> {
    T get(Map<DArgumentType, String> map);
}
