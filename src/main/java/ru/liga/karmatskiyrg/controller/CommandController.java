package ru.liga.karmatskiyrg.controller;

import ru.liga.karmatskiyrg.utils.loop.Loop;
import ru.liga.karmatskiyrg.views.basic.EmptyView;
import ru.liga.karmatskiyrg.views.basic.ExitView;

public class CommandController {

    protected final Loop.LoopControl control;

    public CommandController(Loop.LoopControl control) {
        this.control = control;
    }

    public void rate() {
        new EmptyView().show();
    }

    public void exit() {
        new ExitView().show();
        control.exitLoop();
    }
}
