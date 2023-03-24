package ru.liga.karmatskiyrg.utils.loop;


import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
@Setter
public abstract class Loop<T extends Context> {

    @Setter(AccessLevel.NONE)
    protected T context;
    protected Consumer<T> action;
    protected Consumer<T> initAction;
    @Setter(AccessLevel.NONE)
    private boolean isEnd = false;

    public void run() {
        log.debug("Start init");
        this.initAction.accept(this.context);
        log.debug("End init");

        while (!this.isEnd) {
            this.action.accept(this.context);
        }
    }


    public static class LoopControl<T extends Context> {
        private final Loop<T> loop;

        public LoopControl(Loop<T> loop) {
            this.loop = loop;
        }

        public void exitLoop() {
            this.loop.isEnd = true;
        }
    }
}
