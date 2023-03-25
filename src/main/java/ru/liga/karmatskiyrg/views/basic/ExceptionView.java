package ru.liga.karmatskiyrg.views.basic;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.views.interfaces.View;

@Slf4j
public class ExceptionView implements View {
    private final @NonNull Exception e;

    public ExceptionView(@NonNull Exception e) {
        this.e = e;
    }

    @Override
    public void show() {
//        e.printStackTrace();
//        log.debug(String.valueOf(e));
    }
}
