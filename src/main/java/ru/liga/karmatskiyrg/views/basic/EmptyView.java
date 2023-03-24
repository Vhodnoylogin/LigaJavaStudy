package ru.liga.karmatskiyrg.views.basic;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.views.interfaces.View;

@Slf4j
public class EmptyView implements View {
    @Override
    public void show() {
        System.out.println("Empty request");
    }
}
