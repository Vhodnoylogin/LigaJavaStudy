package ru.liga.karmatskiyrg.distributed.app.lib.router.interfaces;

import ru.liga.karmatskiyrg.distributed.app.lib.adapters.interfaces.Adapter;

public interface Router<C> {
    Adapter execute(String commandString, C context);
}
