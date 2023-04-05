package ru.liga.karmatskiyrg.views.telegram.basic;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.views.interfaces.View;

@Slf4j
@RequiredArgsConstructor
public class ExceptionView implements View {
    private final @NonNull Exception e;

    @Override
    public void show() {
//        e.printStackTrace();
        log.error("Become exception", e);
    }
}
