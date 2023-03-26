package ru.liga.karmatskiyrg.model.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.liga.karmatskiyrg.utils.loop.Context;
import ru.liga.karmatskiyrg.utils.loop.Loop;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public final class RateContext implements Context {
    private final Loop.LoopControl<RateContext> control;
    //    private DLineCommand command;
//    private DLineParameter parameter;
//    private DCurrencyType currencyType;
    private View view = View.EMPTY_VIEW;
    private List<String> tokens;
    @Override
    public Loop.LoopControl<? extends Context> getControl() {
        return this.control;
    }
}
