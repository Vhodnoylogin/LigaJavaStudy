package ru.liga.karmatskiyrg.model.context.telegram;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.liga.karmatskiyrg.views.interfaces.View;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public final class TelegramRateContext {
    private View view = View.EMPTY_VIEW;
    private List<String> tokens;
}
