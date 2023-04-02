package ru.liga.karmatskiyrg.model.context.loop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.liga.karmatskiyrg.utils.loop.Loop;
import ru.liga.karmatskiyrg.utils.loop.LoopContext;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public final class RateLoopContext implements LoopContext {
    private final Loop.LoopControl<RateLoopContext> control;
    //    private DLineCommand command;
//    private DLineParameter parameter;
//    private DCurrencyType currencyType;
    private View view = View.EMPTY_VIEW;
    private List<String> tokens;

    @Override
    public Loop.LoopControl<? extends LoopContext> getControl() {
        return this.control;
    }
}
