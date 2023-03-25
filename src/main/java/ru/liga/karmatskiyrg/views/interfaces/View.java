package ru.liga.karmatskiyrg.views.interfaces;

import ru.liga.karmatskiyrg.views.basic.EmptyView;
import ru.liga.karmatskiyrg.views.basic.ExitView;

public interface View {
    View EXIT_VIEW = new ExitView();
    View EMPTY_VIEW = new EmptyView();

    void show();
}
