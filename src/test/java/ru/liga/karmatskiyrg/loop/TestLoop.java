package ru.liga.karmatskiyrg.loop;

import ru.liga.karmatskiyrg.utils.loop.Context;
import ru.liga.karmatskiyrg.utils.loop.Loop;

public class TestLoop extends Loop<Context> {
    {
        this.context = () -> new Loop.LoopControl<>(this);
    }
}
