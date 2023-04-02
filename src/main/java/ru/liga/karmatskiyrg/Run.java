package ru.liga.karmatskiyrg;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.application.ApplicationLoop;
import ru.liga.karmatskiyrg.controller.initialize.Init;
import ru.liga.karmatskiyrg.service.loop.LoopClass;

import java.util.Scanner;

@Slf4j
public class Run {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var loop = new LoopClass();
        var app = new ApplicationLoop();
        loop.setInitAction(context -> {
            Init.initDictionaries();
            app.initCommands(context);
            app.initParameters(context);
        });
        loop.setAction(context -> {
            ApplicationLoop.read(context, scanner);
            app.context(context);
        });
        loop.run();
    }
}
