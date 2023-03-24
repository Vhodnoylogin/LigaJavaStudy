package ru.liga.karmatskiyrg.service.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineCommand;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineParameter;
import ru.liga.karmatskiyrg.utils.loop.Context;
import ru.liga.karmatskiyrg.utils.loop.Loop;
import ru.liga.karmatskiyrg.views.interfaces.View;

@Setter
@Getter
@RequiredArgsConstructor
public class RateContext implements Context {
    private final Loop.LoopControl<RateContext> control;
    private DLineCommand command;
    private DLineParameter parameter;
    private DCurrencyType currencyType;
    private View view;

    @Override
    public Loop.LoopControl<? extends Context> getControl() {
        return this.control;
    }
}
