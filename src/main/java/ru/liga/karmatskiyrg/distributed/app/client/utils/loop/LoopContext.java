package ru.liga.karmatskiyrg.distributed.app.client.utils.loop;


public interface LoopContext {
    Loop.LoopControl<? extends LoopContext> getControl();
}
