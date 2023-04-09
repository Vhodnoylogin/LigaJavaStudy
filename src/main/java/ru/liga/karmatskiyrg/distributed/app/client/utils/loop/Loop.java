package ru.liga.karmatskiyrg.distributed.app.client.utils.loop;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
@Setter
public abstract class Loop<T extends LoopContext> {
    protected Consumer<T> action;
    protected Consumer<T> initAction;
    @Setter(AccessLevel.PROTECTED)
    private T context;
    @Setter(AccessLevel.PROTECTED)
    private Supplier<Boolean> continueLoopFunc = () -> true;

    @Getter(AccessLevel.PROTECTED)
    private Loop.LoopControl<T> control;

    public void run() {
//        log.debug("Start init");
        this.initAction.accept(this.context);
        log.debug("End init");

        while (this.continueLoopFunc.get()) {
            this.action.accept(this.context);
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    public static class LoopControl<T extends LoopContext> {
        private final Loop<T> loop;

        public void exitLoop() {
//            log.debug("Look {}", this.loop.continueLoopFunc);
            this.loop.setContinueLoopFunc(() -> false);
//            log.debug("Look: {}", this.loop.continueLoopFunc);
        }
    }
}
