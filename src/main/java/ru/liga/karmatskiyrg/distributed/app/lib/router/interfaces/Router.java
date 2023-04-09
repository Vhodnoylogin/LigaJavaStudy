package ru.liga.karmatskiyrg.distributed.app.lib.router.interfaces;

import ru.liga.karmatskiyrg.distributed.app.lib.adapter.Adapter;

public interface Router {
    Adapter execute(String commandString);
}
