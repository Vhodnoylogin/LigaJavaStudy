package ru.liga.karmatskiyrg.views.basic;

import ru.liga.karmatskiyrg.views.interfaces.View;

public class EmptyView implements View {
    @Override
    public void show() {
        System.out.println("Empty request");
    }
}
