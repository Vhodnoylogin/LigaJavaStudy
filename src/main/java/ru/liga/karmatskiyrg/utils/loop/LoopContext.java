package ru.liga.karmatskiyrg.utils.loop;


public interface LoopContext {
    Loop.LoopControl<? extends LoopContext> getControl();
}
