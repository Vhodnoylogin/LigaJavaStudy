package ru.liga.karmatskiyrg.distributed.app.client.views.interfaces;

import ru.liga.karmatskiyrg.distributed.app.client.views.basic.EmptyView;
import ru.liga.karmatskiyrg.distributed.app.client.views.basic.ExitView;

public interface View {
    View EXIT_VIEW = new ExitView();
    View EMPTY_VIEW = new EmptyView();

    void show();
}
