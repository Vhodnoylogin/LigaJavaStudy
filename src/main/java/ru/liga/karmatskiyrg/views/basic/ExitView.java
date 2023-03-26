package ru.liga.karmatskiyrg.views.basic;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.views.interfaces.View;

@Slf4j
public final class ExitView implements View {
    private static final String MESSAGE = "Goodbye";

    @Override
    public void show() {
//        System.out.println(MESSAGE);
        log.info(MESSAGE);
    }
}
