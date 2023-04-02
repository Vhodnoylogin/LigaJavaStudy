package ru.liga.karmatskiyrg.controller.base;

import ru.liga.karmatskiyrg.utils.loop.LoopContext;


public class Controller<T extends LoopContext> {
    protected T context;
}
