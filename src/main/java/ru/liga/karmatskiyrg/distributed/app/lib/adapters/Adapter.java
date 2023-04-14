package ru.liga.karmatskiyrg.distributed.app.lib.adapters;

public interface Adapter {
    <C> void doIt(C context);
}
