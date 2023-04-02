package ru.liga.karmatskiyrg.service.loop;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.model.context.loop.RateLoopContext;
import ru.liga.karmatskiyrg.utils.loop.Loop;

@Slf4j
public final class LoopClass extends Loop<RateLoopContext> {
    {
        var control = new Loop.LoopControl<>(this);
        var context = new RateLoopContext(control);

        this.setControl(control);
        this.setContext(context);
    }
}

