package ru.liga.karmatskiyrg.utils.loop;

public interface Context {
    Loop.LoopControl<? extends Context> getControl();
}
