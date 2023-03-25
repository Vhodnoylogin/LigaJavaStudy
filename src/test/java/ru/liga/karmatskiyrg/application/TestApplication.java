package ru.liga.karmatskiyrg.application;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.controller.initialize.Init;
import ru.liga.karmatskiyrg.service.loop.LoopClass;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class TestApplication {
    @Test
    public void testRunEURWeek() {
        var scanner = new Scanner(new ByteArrayInputStream("""
                RATE EUR week
                EXIT EUR week
                """.getBytes(StandardCharsets.UTF_8)));

        var loop = new LoopClass();
        var app = new Application(scanner);
        loop.setInitAction(context -> {
            Init.initDictionaries();
            app.initCommands(context);
            app.initParameters(context);
        });
        loop.setAction(app::context);
        loop.run();

//        assertThatThrownBy(loop::run)
//                .doesNotThrowAnyException();
    }

    @Test
    public void testRunUSDTomorrow() {
        var scanner = new Scanner(new ByteArrayInputStream("""
                RATE uSd ToMoRRow
                EXIT EUR week
                """.getBytes(StandardCharsets.UTF_8)));

        var loop = new LoopClass();
        var app = new Application(scanner);
        loop.setInitAction(context -> {
            Init.initDictionaries();
            app.initCommands(context);
            app.initParameters(context);
        });
        loop.setAction(app::context);
        loop.run();

//        assertThatThrownBy(loop::run)
//                .doesNotThrowAnyException();
    }

    @Test
    public void testRunUnknownCommand() {
        var scanner = new Scanner(new ByteArrayInputStream("""
                QWERT EUR week
                EXIT EUR week
                """.getBytes(StandardCharsets.UTF_8)));

        var loop = new LoopClass();
        var app = new Application(scanner);
        loop.setInitAction(context -> {
            Init.initDictionaries();
            app.initCommands(context);
            app.initParameters(context);
        });
        loop.setAction(app::context);
        loop.run();

//        assertThatThrownBy(loop::run)
//                .doesNotThrowAnyException();
    }

    @Test
    public void testRunUnknownParameter() {
        var scanner = new Scanner(new ByteArrayInputStream("""
                QWERT EUR yesterday
                EXIT EUR week
                """.getBytes(StandardCharsets.UTF_8)));

        var loop = new LoopClass();
        var app = new Application(scanner);
        loop.setInitAction(context -> {
            Init.initDictionaries();
            app.initCommands(context);
            app.initParameters(context);
        });
        loop.setAction(app::context);
        loop.run();

//        assertThatThrownBy(loop::run)
//                .doesNotThrowAnyException();
    }

    @Test
    @Disabled("Disabled until CustomerService is up!")
    public void testComplexRun() {
        var scanner = new Scanner(new ByteArrayInputStream("""
                RATE TRY week
                RATE EUR tomorrow
                RATE EUR yesterday
                RATE QER week
                QWER EUR week
                EXIT EUR week
                """.getBytes(StandardCharsets.UTF_8)));

        var loop = new LoopClass();
        var app = new Application(scanner);
        loop.setInitAction(context -> {
            Init.initDictionaries();
            app.initCommands(context);
            app.initParameters(context);
        });
        loop.setAction(app::context);
        loop.run();

//        assertThatThrownBy(loop::run)
//                .doesNotThrowAnyException();
    }
}
