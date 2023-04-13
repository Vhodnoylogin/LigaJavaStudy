package ru.liga.karmatskiyrg.distributed.app.lib.adapters.interfaces;

public interface Adapter<C> extends AdapterPutContext<C> {
    void doIt();
}
