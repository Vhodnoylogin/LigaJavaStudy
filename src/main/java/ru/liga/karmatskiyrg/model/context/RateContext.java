package ru.liga.karmatskiyrg.model.context;

import ru.liga.karmatskiyrg.utils.loop.Context;
import ru.liga.karmatskiyrg.utils.loop.Loop;

public class RateContext implements Context {

    private final Loop.LoopControl<RateContext> control;

    public RateContext(Loop.LoopControl<RateContext> control) {
        this.control = control;
    }

    @Override
    public Loop.LoopControl<? extends Context> getControl() {
        return null;
    }
}
