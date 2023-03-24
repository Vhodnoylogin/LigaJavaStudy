package ru.liga.karmatskiyrg.service.loop;

import ru.liga.karmatskiyrg.service.context.RateContext;
import ru.liga.karmatskiyrg.utils.loop.Loop;

public class LoopClass extends Loop<RateContext> {
    {
        this.context = new RateContext(new LoopControl<>(this));
    }
}

