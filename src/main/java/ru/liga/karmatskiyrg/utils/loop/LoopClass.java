package ru.liga.karmatskiyrg.utils.loop;

public class LoopClass extends Loop<Context> {
    {
        this.context = () -> new Loop.LoopControl<>(this);
    }
}

