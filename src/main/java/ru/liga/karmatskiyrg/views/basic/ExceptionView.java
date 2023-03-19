package ru.liga.karmatskiyrg.views.basic;

import ru.liga.karmatskiyrg.views.interfaces.View;

public class ExceptionView implements View {

    protected final Exception e;

    public ExceptionView(Exception e) {
        this.e = e;
    }

    @Override
    public void show() {
        e.printStackTrace();
    }
}
